package bamboo;

import com.atlassian.bamboo.specs.api.builders.AtlassianModule;
import com.atlassian.bamboo.specs.api.builders.BambooKey;
import com.atlassian.bamboo.specs.api.builders.Variable;
import com.atlassian.bamboo.specs.api.builders.notification.AnyNotificationRecipient;
import com.atlassian.bamboo.specs.api.builders.notification.Notification;
import com.atlassian.bamboo.specs.api.builders.owner.PlanOwner;
import com.atlassian.bamboo.specs.api.builders.pbc.ContainerSize;
import com.atlassian.bamboo.specs.api.builders.pbc.ExtraContainer;
import com.atlassian.bamboo.specs.api.builders.pbc.ExtraContainerSize;
import com.atlassian.bamboo.specs.api.builders.pbc.PerBuildContainerForJob;
import com.atlassian.bamboo.specs.api.builders.permission.PermissionType;
import com.atlassian.bamboo.specs.api.builders.permission.Permissions;
import com.atlassian.bamboo.specs.api.builders.permission.PlanPermissions;
import com.atlassian.bamboo.specs.api.builders.plan.Job;
import com.atlassian.bamboo.specs.api.builders.plan.Plan;
import com.atlassian.bamboo.specs.api.builders.plan.PlanIdentifier;
import com.atlassian.bamboo.specs.api.builders.plan.Stage;
import com.atlassian.bamboo.specs.api.builders.plan.artifact.Artifact;
import com.atlassian.bamboo.specs.api.builders.plan.artifact.ArtifactSubscription;
import com.atlassian.bamboo.specs.api.builders.plan.branches.BranchCleanup;
import com.atlassian.bamboo.specs.api.builders.plan.branches.PlanBranchManagement;
import com.atlassian.bamboo.specs.api.builders.plan.configuration.ConcurrentBuilds;
import com.atlassian.bamboo.specs.api.builders.plan.dependencies.Dependencies;
import com.atlassian.bamboo.specs.api.builders.plan.dependencies.DependenciesConfiguration;
import com.atlassian.bamboo.specs.api.builders.repository.VcsRepositoryIdentifier;
import com.atlassian.bamboo.specs.api.builders.requirement.Requirement;
import com.atlassian.bamboo.specs.api.builders.task.Task;
import com.atlassian.bamboo.specs.builders.notification.CommittersRecipient;
import com.atlassian.bamboo.specs.builders.notification.JobFailedNotification;
import com.atlassian.bamboo.specs.builders.notification.PlanCompletedNotification;
import com.atlassian.bamboo.specs.builders.notification.PlanFailedNotification;
import com.atlassian.bamboo.specs.builders.task.*;
import com.atlassian.bamboo.specs.builders.trigger.BitbucketServerTrigger;
import com.atlassian.bamboo.specs.model.task.InjectVariablesScope;
import com.atlassian.bamboo.specs.model.task.ScriptTaskProperties;
import com.atlassian.bamboo.specs.util.BambooServer;

import static bamboo.Constants.*;
import static bamboo.PlanHelpers.determineImage;
import static bamboo.PlanHelpers.isDeploymentBambooInstance;
import static com.atlassian.bamboo.specs.model.task.TestParserTaskProperties.TestType.JUNIT;

/**
 * Plan for running tests against the repository.
 *
 * <p>Note that this is shared across all bamboo instances, with some modifications for a deployment bamboo instance.
 */
public class BuildAndTestSpec {

    private static final PerBuildContainerForJob PER_BUILD_CONTAINER = new PerBuildContainerForJob()
            .image(PlanHelpers.determineImage())
            .size(ContainerSize.REGULAR)
            .enabled(true)
            // This microservice relies on building a docker image so we need docker to be available.
            .extraContainers(new ExtraContainer()
                    .name("docker")
                    .image("docker:18.04-dind")
                    .size(ExtraContainerSize.SMALL)
            );

    private static Task checkoutCode() {
        return new VcsCheckoutTask()
                .description("Checkout Default Repository")
                .checkoutItems(new CheckoutItem().repository(
                        new VcsRepositoryIdentifier().name(repositoryName)));
    }

    /**
     * Used to run all of the tests in bamboo.
     */
    private static Task runInBamboo() {
        return new ScriptTask()
                .description("Run tests: ./bin/run-in-bamboo.sh")
                .inlineBody("./bin/run-in-bamboo.sh");
    }

    private static Task releasePrepare(){
        if (!isDeploymentBambooInstance()){
            return new MavenTask()
                    .description("Release Prepare")
                    .goal("-e -B -DskipTests -DdryRun=true -Darguments=-DskipTests release:prepare -Denforcer.skip")
                    .jdk("JDK 11")
                    .executableLabel("mvnvm");
        }
        else {
            return new MavenTask()
                    .description("Release Prepare")
                    .goal(" -e -B -DskipTests -Darguments=-DskipTests release:prepare -Denforcer.skip")
                    .jdk("JDK 11")
                    .executableLabel("mvnvm");
        }
    }

    /**
     * Used to clean up after the tests in bamboo have run.
     */
    private static Task stopInBamboo() {
        return new ScriptTask()
                .description("Cleanup bamboo environment: ./bin/stop-in-bamboo.sh")
                .inlineBody("./bin/stop-in-bamboo.sh");
    }

    /**
     * Used to run Snyk task for Vulnerability check.
     */
    private static Task runSnyk() {
        return new ScriptTask()
                .description("Snyk Docker Scanning")
                .interpreter(ScriptTaskProperties.Interpreter.BINSH_OR_CMDEXE)
                .inlineBody("bash bin/snyk-scan.sh " + repositoryName);
    }

    /**
     * Used to run sonar scan.
     */
    private static Task runSonar() {
        return new ScriptTask()
                .description("Sonar Scan")
                .interpreter(ScriptTaskProperties.Interpreter.BINSH_OR_CMDEXE)
                .location(ScriptTaskProperties.Location.FILE)
                .fileFromPath("./bin/sonar.sh");
    }

    /**
     * This injects all of the variables from {@link Constants#VARIABLES_PROPERTIES_FILE} into a bamboo variable. This is used in our
     * deployment plan where we can reference the variables by doing <pre>{bamboo.inject.variable}</pre>.
     */
    private static Task injectBambooVariables() {
        return new InjectVariablesTask()
                .description("Inject version variable")
                .namespace("inject")
                .scope(InjectVariablesScope.RESULT)
                .path(VARIABLES_PROPERTIES_FILE);
    }

    private static Task junitParser() {
        return new TestParserTask(JUNIT)
                .description("JUnit Parser")
                .resultDirectories(JUNIT_RESULTS_PATTERN);
    }

    /**
     * The actual stage that completes the testing of the application.
     */
    private static Stage buildAndTestStage() {
        return new Stage("Build and Test")
                .description("Build and Test " + SERVICE_READABLE_NAME)
                .jobs(new Job("Build and Test Job", "BUILDANDTEST")
                        .pluginConfigurations(PER_BUILD_CONTAINER)
                        .artifacts(new Artifact()
                                        .name("Project-dir")
                                        .copyPattern("**/**")
                                        .shared(true),
                                new Artifact()
                                        .name("Logs")
                                        .copyPattern("**/*.log"),
                                new Artifact()
                                        .name("Service descriptor")
                                        .location(BUILD_OUTPUT_FOLDER)
                                        .copyPattern("*.sd.yml")
                                        .shared(true)
                        )
                        .tasks(checkoutCode(),
                                downloadCentralisedScripts(),
                                releasePrepare(),
                                runInBamboo(),
                                junitParser(),
                                pushDockerImage(),
                                injectBambooVariables(),
                                runSnyk(),
                                runSonar())
                        .finalTasks(stopInBamboo())
                        .requirements(new Requirement("os").matchValue("Linux")
                                .matchType(Requirement.MatchType.EQUALS))
                );
    }

    private static ScriptTask pushDockerImage() {
        return new ScriptTask()
                .description("Package and push Docker image")
                .interpreter(ScriptTaskProperties.Interpreter.BINSH_OR_CMDEXE)
                .inlineBody("\n./bin/build-in-bamboo.sh\n");
    }

    //Method to download and unzip centralised bamboo scripts
    public static Task downloadCentralisedScripts() {
        return new ScriptTask()
                .description("Download Automation scripts")
                .interpreter(ScriptTaskProperties.Interpreter.BINSH_OR_CMDEXE)
                .inlineBody("artifactoryKey=${bamboo.packages.user.api.key.password}\nartifactoryUser=${bamboo.packages.user.username}\ncurl -u \"${artifactoryUser}:${artifactoryKey}\" https://packages.atlassian.com/maven-private-local/com/atlassian/ipaas/ipaas-bamboo-scripts/" + bambooScriptsVersion + "/ipaas-bamboo-scripts-" + bambooScriptsVersion + ".zip -o \"ipaas-bamboo-scripts.zip\"\nunzip ipaas-bamboo-scripts.zip");
    }

    private static Notification jobFailedNotifyCommitter() {
        return new Notification()
                .type(new JobFailedNotification())
                .recipients(new CommittersRecipient());
    }

    private static Notification planFailedNotifyCommitter() {
        return new Notification()
                .type(new PlanFailedNotification())
                .recipients(new CommittersRecipient());
    }


    /**
     * Builds the plan for testing the application.
     *
     * <p>Note that in a deployment environment branch builds are not automatically generated and notifications are
     * altered.
     *
     * @param isDeploymentBamboo whether the bamboo environment is a deployment environment
     * @return the build and test plan for this bamboo instance
     */
    private static Plan buildAndTestPlan(final boolean isDeploymentBamboo) {
        final Plan plan = new Plan(PROJECT, PLAN_NAME, PLAN_KEY)
                .description("Build and Test plan")
                .pluginConfigurations(new ConcurrentBuilds(),
                        new PlanOwner(PLAN_OWNER)
                )
                .enabled(true)
                .linkedRepositories(repositoryName)
                .dependencies(new Dependencies()
                        .configuration(new DependenciesConfiguration().enabledForBranches(false)))
                .triggers(new BitbucketServerTrigger())
                .forceStopHungBuilds()
                .labels("plan-templates")
                .stages(buildAndTestStage())
                .notifications(jobFailedNotifyCommitter(), planFailedNotifyCommitter(),
                        new Notification().type(new PlanCompletedNotification())
                                .recipients(new AnyNotificationRecipient(new AtlassianModule(
                                        "com.atlassian.bamboo.plugins.bamboo-slack:recipient.slack"))
                                        .recipientString(
                                                "${bamboo.atlassian.slack.webhook.url.password}|"
                                                        + (isDeploymentBamboo ? SLACK_CHANNEL_NAME_PROD
                                                        : SLACK_CHANNEL_NAME_NON_PROD)
                                                        + "||"))
                ).variables(
                        new Variable(SNYK_TOKEN, "%vault%:secret/data/builds/" + (snykAccessGroupDev) + "/" + SNYK_TOKEN)

                );

        if (!isDeploymentBamboo) {
            plan.variables(
                    new Variable(stashToken,
                            "%vault%:/secret/data/builds/" + vaultAccessGroupNonCompliant + "/" + stashToken)
                    , new Variable(confluenceToken,
                            "%vault%:/secret/data/builds/" + vaultAccessGroupNonCompliant + "/" + confluenceToken)
                    , new Variable(bambooToken,
                            "%vault%:/secret/data/builds/" + vaultAccessGroupNonCompliant + "/" + bambooToken)
            );
        } else {
            plan.variables(
                    new Variable(stashToken,
                            "%vault%:/secret/data/builds/" + vaultAccessGroup + "/" + stashToken)
                    , new Variable(confluenceToken,
                            "%vault%:/secret/data/builds/" + vaultAccessGroup + "/" + confluenceToken)
                    , new Variable(bambooToken,
                            "%vault%:/secret/data/builds/" + vaultAccessGroup + "/" + bambooToken)
            );
        }

        if (!isDeploymentBamboo) {
            plan.planBranchManagement(new PlanBranchManagement()
                    .notificationLikeParentPlan()
                    .createForVcsBranch()
                    .delete(new BranchCleanup()
                            .whenInactiveInRepositoryAfterDays(10)
                            .whenRemovedFromRepositoryAfterDays(1)
                    )
                    .notificationForCommitters());
        }
        plan.stages(releaseStage());
        return plan;
    }

    public static Stage releaseStage() {

        Stage releaseStage = new Stage("Release Stage").description("Release the version to artifactory")
                .jobs(releaseJob());
        return releaseStage;
    }

    private static Job releaseJob() {
        Job releaseJob = new Job("Release Job",
                new BambooKey("RELEASE"))
                .description("Release " + SERVICE_READABLE_NAME + " project")
                .pluginConfigurations(
                        new PerBuildContainerForJob()
                                .enabled(true)
                                .image(determineImage())
                                .size("REGULAR")
                )
                .artifacts(new Artifact()
                        .name("Logs")
                        .copyPattern("**/*.log"))
                .artifactSubscriptions(new ArtifactSubscription()
                        .artifact("Project-dir"));
        releaseJob.tasks(new ArtifactDownloaderTask()
                        .artifacts(new DownloadItem()
                                .artifact("Project-dir")
                                .path(".")),
                releaseMavenTask());

        if(isDeploymentBambooInstance()) {
            if (enableReleaseNotes) {
                releaseJob.tasks(ReleaseNotes.updateReleaseNotes());
            }
        }
        return releaseJob;
    }

    private static Task releaseMavenTask() {
        return new MavenTask()
                .description("Release version to artifactory")
                .goal("-f pom.xml -B jar:jar deploy:deploy -DskipTests=true")
                .jdk("JDK 11")
                .executableLabel("mvnvm");
    }

    private static PlanPermissions buildAndTestPlanPermissions() {
        return new PlanPermissions(new PlanIdentifier(PROJECT_KEY, PLAN_KEY))
                .permissions(new Permissions()
                        .groupPermissions(GROUP_NAME_PERMISSION_1, PermissionType.VIEW, PermissionType.BUILD)
                        .groupPermissions(GROUP_NAME_PERMISSION_2, PermissionType.VIEW, PermissionType.BUILD)
                        .loggedInUserPermissions(PermissionType.VIEW)
                        .anonymousUserPermissionView()
                );
    }

    /**
     * Will publish this spec to the provided {@link BambooServer}.
     *
     * @param bambooServer the server to publish to
     */
    public void publishToBamboo(final BambooServer bambooServer) {
        final boolean isDeploymentBamboo = isDeploymentBambooInstance();

        bambooServer.publish(BuildAndTestSpec.buildAndTestPlan(isDeploymentBamboo));
        bambooServer.publish(BuildAndTestSpec.buildAndTestPlanPermissions());
    }
}