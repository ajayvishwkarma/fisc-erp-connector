package bamboo;

import com.google.common.collect.ImmutableSet;
import lombok.experimental.UtilityClass;

import javax.annotation.Nullable;
import java.util.Base64;
import java.util.Set;

import com.atlassian.bamboo.specs.api.context.RssRuntimeContext;

import static bamboo.Constants.DEPLOYMENT_BAMBOO_IMAGE;
import static bamboo.Constants.IT_BAMBOO_IMAGE;

/**
 * Some helper functions that are used across all of the plans.
 */
@UtilityClass
final class PlanHelpers {

    private static final String DEPLOYMENT_BAMBOO_INSTANCE_NAME = "deployment-bamboo";

    private static final String BAMBOO_SERVER_URL_SYS_PROP = "bambooServerUrl";

    /**
     * Determine if the instance we are adding the specs to is an instance that should be able to deploy
     * the service.
     */
    static boolean isDeploymentBambooInstance() {
        final String instanceName = RssRuntimeContext.getServerName().get();
        return instanceName != null && instanceName.equals(DEPLOYMENT_BAMBOO_INSTANCE_NAME);
    }

    /**
     * Get the server url for the bamboo instance, this is only useful for local development as this value should
     * be null when running on an actual bamboo instance.
     */
    @Nullable
    static String bambooServerUrl() {
        return System.getProperty(BAMBOO_SERVER_URL_SYS_PROP);
    }

    /**
     * Determine if SOX is enabled. If not we can deploy from any instance.
     */
    static boolean isSOXEnabled() {
        return false;
    }

    public static String determineImage() {
        if (isDeploymentBambooInstance()) {
            return DEPLOYMENT_BAMBOO_IMAGE;
        } else {
            return IT_BAMBOO_IMAGE;
        }
    }
}
