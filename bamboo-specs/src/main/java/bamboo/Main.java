package bamboo;

import com.atlassian.bamboo.specs.api.BambooSpec;
import com.atlassian.bamboo.specs.util.BambooServer;

import static bamboo.PlanHelpers.bambooServerUrl;

/**
 * Main method that will publish all of the actual specs.
 *
 * <p>The reason that this is needed instead of annotating each spec individually is because we require the {@link BuildAndTestSpec} to be published before
 * attempting to publish the {@link DeploymentSpec}.
 */
@BambooSpec
public class Main {
    public static void main(final String... argv) {
        // We are building this via the spec scanner so no explicit bamboo server URL is necessary here.
        @SuppressWarnings("ConstantConditions") final BambooServer bambooServer = new BambooServer(bambooServerUrl());

        final DeploymentSpec deploymentSpec = new DeploymentSpec();
        final BuildAndTestSpec buildAndTestSpec = new BuildAndTestSpec();

        buildAndTestSpec.publishToBamboo(bambooServer);
        deploymentSpec.publishToBamboo(bambooServer);
    }
}
