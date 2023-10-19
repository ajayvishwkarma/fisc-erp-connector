package bamboo;

/**
 * A subset of the environments that can be used for deploying micros applications.
 *
 * @see <a href="https://hello.atlassian.net/wiki/spaces/MICROS/pages/167212857/Environments+Regions+and+Domains">Environments Regions and Domains</a>
 *     for other available environments.
 */
public enum MicrosEnvironment {
    DOMAIN_DEV("ddev"),
    APPLICATION_DEV("adev"),
    STAGING_EAST("stg-east"),
    PRODUCTION_EAST("prod-east");

    private final String name;

    MicrosEnvironment(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
