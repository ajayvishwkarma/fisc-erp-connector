package bamboo;

import com.atlassian.bamboo.specs.api.builders.AtlassianModule;
import com.atlassian.bamboo.specs.api.builders.BambooOid;
import com.atlassian.bamboo.specs.api.builders.Variable;
import com.atlassian.bamboo.specs.api.builders.deployment.Deployment;
import com.atlassian.bamboo.specs.api.builders.deployment.Environment;
import com.atlassian.bamboo.specs.api.builders.deployment.ReleaseNaming;
import com.atlassian.bamboo.specs.api.builders.Variable;
import com.atlassian.bamboo.specs.api.builders.deployment.configuration.AnyPluginConfiguration;
import com.atlassian.bamboo.specs.api.builders.permission.DeploymentPermissions;
import com.atlassian.bamboo.specs.api.builders.permission.EnvironmentPermissions;
import com.atlassian.bamboo.specs.api.builders.permission.PermissionType;
import com.atlassian.bamboo.specs.api.builders.permission.Permissions;
import com.atlassian.bamboo.specs.api.builders.plan.PlanIdentifier;
import com.atlassian.bamboo.specs.api.builders.requirement.Requirement;
import com.atlassian.bamboo.specs.api.builders.task.Task;
import com.atlassian.bamboo.specs.builders.task.ArtifactDownloaderTask;
import com.atlassian.bamboo.specs.builders.task.CleanWorkingDirectoryTask;
import com.atlassian.bamboo.specs.builders.task.DownloadItem;
import com.atlassian.bamboo.specs.builders.task.InjectVariablesTask;
import com.atlassian.bamboo.specs.builders.task.MavenTask;
import com.atlassian.bamboo.specs.builders.task.ScriptTask;
import com.atlassian.bamboo.specs.builders.task.VcsCheckoutTask;
import com.atlassian.bamboo.specs.builders.trigger.AfterSuccessfulBuildPlanTrigger;
import com.atlassian.bamboo.specs.util.BambooServer;
import com.atlassian.bamboo.specs.util.Logger;
import com.atlassian.bamboo.specs.util.MapBuilder;

import com.atlassian.bamboo.specs.api.builders.AtlassianModule;
import com.atlassian.bamboo.specs.api.builders.notification.AnyNotificationRecipient;
import com.atlassian.bamboo.specs.api.builders.notification.AnyNotificationType;
import com.atlassian.bamboo.specs.api.builders.notification.Notification;
import com.atlassian.bamboo.specs.builders.notification.DeploymentFailedNotification;
import com.atlassian.bamboo.specs.builders.notification.DeploymentFinishedNotification;
import com.atlassian.bamboo.specs.api.builders.repository.VcsRepositoryIdentifier;
import com.atlassian.bamboo.specs.builders.task.CheckoutItem;
import com.atlassian.bamboo.specs.model.task.InjectVariablesScope;
import com.atlassian.bamboo.specs.model.task.ScriptTaskProperties;
import com.atlassian.bamboo.specs.api.builders.pbc.PerBuildContainerForEnvironment;
import com.atlassian.bamboo.specs.api.builders.pbc.ExtraContainer;
import com.atlassian.bamboo.specs.api.builders.pbc.ContainerSize;
import com.atlassian.bamboo.specs.api.builders.pbc.ExtraContainerSize;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static bamboo.Constants.*;
import static bamboo.PlanHelpers.isDeploymentBambooInstance;
import static bamboo.PlanHelpers.isSOXEnabled;

public class DeploymentSpec {

    private static final Logger LOG = Logger.getLogger(DeploymentSpec.class);


    /**
     * Contains all of the tasks that will be needing to be done to deploy it to a micros environment.
     */
    private static Task[] deploymentTasks(final Map<String,String> prop) {
        return new Task[] {
                new CleanWorkingDirectoryTask(),
                new VcsCheckoutTask()
                        .description("Checkout Default Repository").checkoutItems(
                        new CheckoutItem()
                                .repository(new VcsRepositoryIdentifier().name(repositoryName))),
                new ArtifactDownloaderTask()
                        .artifacts(new DownloadItem()
                        .artifact("Project-dir")
                        .path(".")),
                new ArtifactDownloaderTask()
                        .description("Download release contents")
                        .sourcePlan(new PlanIdentifier(PROJECT_KEY, PLAN_KEY))
                        .artifacts(new DownloadItem()
                        .artifact("Service descriptor")
                        .path(BUILD_OUTPUT_FOLDER)
                )
        };
    }

    //This method creates environment
    private static Environment createEnvironment(final String env, final Map<String,String> prop){

        Environment environment = new Environment(env)
                .notifications(generateNotifications(env))
                .tasks(deploymentTasks(prop));

        environment.tasks(new ScriptTask()

                .requirements(new Requirement("os").matchType(Requirement.MatchType.EQUALS).matchValue("Linux"))
                .description("Deploy to micros")
                .inlineBody("./bin/deploy-in-bamboo.sh " + prop.get("name")));

        if(isDeploymentBambooInstance() && env.equals(STG_EAST_ENVIRONMENT)) {
            environment.triggers(new AfterSuccessfulBuildPlanTrigger().triggerByMasterBranch());
        }

        if(!isDeploymentBambooInstance() && env.equals(APP_DEV_ENVIRONMENT)) {
            environment.triggers(new AfterSuccessfulBuildPlanTrigger());
        }
        
        if(!env.equalsIgnoreCase(PROD_EAST_ENVIRONMENT)) {
            environment.tasks(runIntegrationTest(env, prop));
        }

        environment.pluginConfigurations(
                new PerBuildContainerForEnvironment()
                        .image(PlanHelpers.determineImage())
                        .size(ContainerSize.REGULAR)
                        .extraContainers(new ExtraContainer()
                                .name("docker")
                                .image("docker:stable-dind")
                                .size(ExtraContainerSize.SMALL)));

        return environment;
    }


    public static Task runIntegrationTest(String env, Map<String,String> envProperties)
    {
        return new ScriptTask()
                .description("Run Integration tests in " + env + " environment")
                .interpreter(ScriptTaskProperties.Interpreter.BINSH_OR_CMDEXE)
                .inlineBody("./bin/run-integration-tests.sh " + envProperties.get("name"));
    }

    private static Deployment deploymentPlan() {
        final Deployment rootObject =  new Deployment(new PlanIdentifier(PROJECT_KEY, PLAN_KEY), DEPLOY_PLAN_NAME)
                .releaseNaming(new ReleaseNaming(RELEASE_NAME));
        for (String env: envList.keySet()) {

            if(isDeploymentBambooInstance() && (env.equals(STG_EAST_ENVIRONMENT) || env.equals(PROD_EAST_ENVIRONMENT)))
            {
                rootObject.environments(createEnvironment(env, envList.get(env)));
            }

            if(!isDeploymentBambooInstance() && (env.equals(DOMAIN_DEV_ENVIRONMENT) || env.equals(APP_DEV_ENVIRONMENT)))
            {
                rootObject.environments(createEnvironment(env, envList.get(env)));
            }
        }
        return rootObject;
    }

    /**
     * Basic permissions that will be used for the deployment project as well as each of the environments in the
     * deployment.
     */
    private static Permissions permissions() {
        return new Permissions()
                .groupPermissions(GROUP_NAME_PERMISSION_1, PermissionType.VIEW, PermissionType.BUILD)
                .groupPermissions(GROUP_NAME_PERMISSION_2, PermissionType.VIEW, PermissionType.BUILD)
                .loggedInUserPermissions(PermissionType.VIEW, PermissionType.BUILD)
                .anonymousUserPermissionView();
    }

    //This method is to set the deployment permissions
    public static DeploymentPermissions deploymentPermissions() {
        final DeploymentPermissions deploymentPermission = new DeploymentPermissions(DEPLOY_PLAN_NAME)
                .permissions(new Permissions()
                        .groupPermissions(GROUP_NAME_PERMISSION_1, PermissionType.VIEW)
                        .groupPermissions(GROUP_NAME_PERMISSION_2, PermissionType.VIEW)
                        .loggedInUserPermissions(PermissionType.VIEW)
                        .anonymousUserPermissionView());
        return deploymentPermission;
    }


    /**
     * We need to assign permissions for each environment otherwise when you view the deployment there will be no
     * environments listed in the list even though though they have been created.
     */
    private static List<EnvironmentPermissions> environmentPermissions() {
        List<EnvironmentPermissions> permissions = new ArrayList<>();

        for (String env: envList.keySet()) {

            if(isDeploymentBambooInstance() && (env.equals(STG_EAST_ENVIRONMENT) || env.equals(PROD_EAST_ENVIRONMENT)))
            {
                permissions.add(new EnvironmentPermissions(DEPLOY_PLAN_NAME)
                        .environmentName(env)
                        .permissions(permissions()));
            }
            if(!isDeploymentBambooInstance() && (env.equals(DOMAIN_DEV_ENVIRONMENT) || env.equals(APP_DEV_ENVIRONMENT)))
            {
                permissions.add(new EnvironmentPermissions(DEPLOY_PLAN_NAME)
                        .environmentName(env)
                        .permissions(permissions()));
            }
        }
        return permissions;
    }


    /**
     * Will publish this spec to the provided {@link BambooServer}.
     *
     * @param bambooServer the server to publish to
     */
    public void publishToBamboo(final BambooServer bambooServer) {
        final boolean enableDeployment = isDeploymentBambooInstance() || !isSOXEnabled();

        if (!enableDeployment) {
            LOG.info("Not a deployment instance, ignoring.");
            return;
        }

        bambooServer.publish(DeploymentSpec.deploymentPlan());
        bambooServer.publish(DeploymentSpec.deploymentPermissions());

        for (final EnvironmentPermissions environmentPermissions : DeploymentSpec.environmentPermissions()) {
            bambooServer.publish(environmentPermissions);
        }
    }

    private static Notification[] generateNotifications(String envName) {
        Notification[] notifications = new Notification[] {
                new Notification().type(new DeploymentFailedNotification())
                        .recipients(new AnyNotificationRecipient(
                        new AtlassianModule("com.atlassian.bamboo.plugins.bamboo-slack:recipient.slack"))
                        .recipientString("${bamboo.atlassian.slack.webhook.url.password}|"
                                + (isDeploymentBambooInstance() ? SLACK_CHANNEL_NAME_PROD : SLACK_CHANNEL_NAME_NON_PROD)
                                + "||")),
                new Notification().type(new DeploymentFinishedNotification())
                        .recipients(new AnyNotificationRecipient(
                        new AtlassianModule("com.atlassian.bamboo.plugins.bamboo-slack:recipient.slack"))
                        .recipientString("${bamboo.atlassian.slack.webhook.url.password}|"
                                + (isDeploymentBambooInstance() ? SLACK_CHANNEL_NAME_PROD : SLACK_CHANNEL_NAME_NON_PROD)
                                + "||")),
                new Notification()
                        .type(new AnyNotificationType(
                                new AtlassianModule("bamboo.deployments:deploymentStartedFinished")))
                        .recipients(new AnyNotificationRecipient(
                        new AtlassianModule("com.atlassian.bamboo.plugins.bamboo-slack:recipient.slack"))
                        .recipientString("${bamboo.atlassian.slack.webhook.url.password}|"
                                + (isDeploymentBambooInstance() ? SLACK_CHANNEL_NAME_PROD : SLACK_CHANNEL_NAME_NON_PROD)
                                + "||")) };

        if (!envName.equals(PROD_EAST_ENVIRONMENT)) {
            return ArrayUtils.subarray(notifications, 0, notifications.length - 1);
        } else {
            return notifications;
        }
    }

    private static AnyPluginConfiguration[] generatePluginConfiguration() {
        return new AnyPluginConfiguration[] { new AnyPluginConfiguration(
                new AtlassianModule("com.atlassian.buildeng.bamboo-isolated-docker-plugin:pbcEnvironment"))
                .configuration(new MapBuilder<String,String>().put("custom.isolated.docker.enabled", "false")
                .put("custom.isolated.docker.image", PlanHelpers.determineImage())
                .put("custom.isolated.docker.imageSize", "REGULAR")
                .put("custom.planownership.bamboo.plugin.plan.config.ownerOfBuild", PLAN_OWNER)
                .build()) };
    }
}