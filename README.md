# STOP - ACTION REQUIRED FOR NEW SERVICES

Please run:
```
bin/first-run
```
When you first download your new service - This script ensures your service starts off as cleanly as possible and helps you set up proper DAC starting documentation


# Welcome to FISC ERP Connector!

This project has been created via go/instant-micros using the Spring Boot template.


## How to run locally

Start with the `Nebulae` installation using:
```sh
bin/install-nebulae.sh
```

This service is configured to use the Slauth sidecar for authentication. The easiest way to run this locally is via
nebulae.
```sh
atlas nebulae start
```
This will make your service available through the `slauth-mock-sidecar`. You can ping its healthcheck via:
```sh
curl http://localhost:8080/heartbeat
```
For authenticated endpoints, you will need to configure authenticated requests via the control panel at [localhost:9090](http://localhost:9090/).

For more info, see the Slauth section below.

You can also run your service on your workstation, but connect to local or remote AWS resources like RDS, SQS, S3 and others. 
In order to do that, use nebulae to start local resources in a sandbox or provide connectivity to remote resources in ddev.

First start by telling nebulae not to start your service in the sandbox by creating a `nebulae.yml` file with the 
following contents:
```yaml
sandbox:
  default:
    plugins:
      main:
        containers:
          fisc-erp-connector:
            enabled: false
          slauth:
            enabled: false
```
Next use the below example to start a sandbox with local resources:
```shell
atlas nebulae start --export-env=envvars.env
```
Or this one to connect to remote resources in ddev:
:warning: You need to make sure you have already deployed your service to ddev for those resources to exist.
```shell
atlas nebulae start --export-env=envvars.env --all-remote
```
Finally start your service locally:
```shell
source envvars.env
mvn spring-boot:run
```

For more information on using Nebulae go to [DAC](http://go/nebulae)

## How to build and test

`mvn test`

## How to deploy
Ensure you have:
- [Docker](https://hello.atlassian.net/wiki/spaces/MICROS/pages/693843069/HOWTO+Install+Docker) installed
- [Atlas CLI](https://developer.atlassian.com/platform/atlas-cli/users/install/) installed
- the [Micros plugin](https://hello.atlassian.net/wiki/spaces/MICROS/pages/536392995/HOWTO+Log+in+to+Micros+with+Atlas+CLI#HOWTO:LogintoMicroswithAtlasCLI-InstalltheMicrosplugin) for Atlas CLI installed 
- [logged into Micros](https://hello.atlassian.net/wiki/spaces/MICROS/pages/536392995/HOWTO+Log+in+to+Micros+with+Atlas+CLI)
- [logged into our internal Docker registry](https://hello.atlassian.net/wiki/spaces/MICROS/pages/693809737/HOWTO+Login+to+the+Atlassian+Docker+Registry)
- [Nebulae](https://developer.atlassian.com/platform/nebulae/getting-started/) installed  
- Ensure you have set MEMORY_OPTS according to your instance size for each environment
- Gain [temp write permission on artifactory](https://hello.atlassian.net/wiki/spaces/RELENG/pages/852378105/HOWTO+-+Get+temporary+write+permission+on+artifactory) 
- Run `bin/deploy-manually.sh ddev`

Done! Your service is now available in [ddev](https://fisc-erp-connector.ap-southeast-2.dev.atl-paas.net/).

Note that instead of logging in manually to the Docker repo, see below on how to add credentials to Maven settings.

If you prefer to automate the build and deployment, please read the README in the bamboo-plan-template subdirectory.




## Links

- [Live service in ddev](https://fisc-erp-connector.ap-southeast-2.dev.atl-paas.net/) (with [health check](https://fisc-erp-connector.ap-southeast-2.dev.atl-paas.net//heartbeat))
- [Microscope](https://microscope.prod.atl-paas.net/services/fisc-erp-connector)

## More about Spring Boot Instant Micros

The Spring Boot template is defined at https://stash.atlassian.com/projects/MCT/repos/instant-micros-templates/browse/spring-boot/base

### Features

- Lightweight and small: few Java files and only one XML, the pom.xml (no web.xml, applicationContext.xml, etc.).
- Json logging compatible with Micros. The json structure can be customized.
- Heartbeat and deepcheck
- Debug endpoints ([details here](http://docs.spring.io/spring-boot/docs/1.3.1.RELEASE/reference/html/production-ready-endpoints.html)).
- Docker image is built on top of the supported & JVM working group recommended ps-docker-jdk-base docker image
- Renovate (https://go.attlassian.com/renovate) initial configuration
- Swagger (see the [MSB readme](https://bitbucket.org/atlassian/micros-spring-boot/annotate/master/README.md?at=master&fileviewer=file-view-default#README.md-609) to enable UI features)

It also includes a build script compatible with the Micros Deployment Plan Template, which will handle service version in this way:

- Removing any SNAPSHOT, if present.
- Using either the Bamboo build number (if present) or the Git commit short hash (fall back). This is useful to quickly iterate deploying changes without burning version numbers in the POM.xml.
- Note: this strategy is just an example. It might not please every scenario, so feel free to change it to accommodate your particular style and needs.

### Technology stack

- [Spring Boot](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) - Microservice ready framework built on Modern Spring & cloud standards
- [Micros Spring Boot](https://stash.atlassian.com/projects/MCT/repos/micros-spring-boot) - A library which provides sensible defaults for a production micro-service which runs on Micros
- [Micros Security Starters SLAuth Module](https://go.atlassian.com/spring-boot-starters) - The SLAuth support module from the Micros security starters
- [ps-docker-jdk-base](https://hello.atlassian.net/wiki/spaces/PSRV/pages/404139296/JDK+Docker+Base+Image+ps-docker-jdk-base) - The JVM Working group recommended base docker image for production services; Supported by Platform Services Tritanium squad
- The technology stack is compatible with (read: can be changed to use) Gradle or Maven,  and Java or Kotlin (you can use Spring Boot with Java/Kotlin).

## Understanding the configuration

- The service configuration file is `src/main/resources/application.yml`.
- The top section of the file applies to all the environments (ddev, adev, etc.).
- This section is followed by environment-specific subsections (one per Micros environment), each one separated by a `---` line.
- Micros Spring Boot will automatically detect the Micros environment you are deployed to and active the appropriate profiles. It activates both an environment specific profile (e.g. stg-west, prod-west)
  as well as a logical environment (e.g. dev, stg, prod) so configuration can be shared for a logical environment.
- All properties defined in the `application.yml` can also be overridden by system properties or environment variables. For more information read the [Spring Boot documentation](http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html).

## Revealer

Revealer is a cyclic package dependency checker. For more information about usage and configuration go to [go/revealer](http://go/revealer).


## IDE support

This is a normal Maven project. Just load it in your IDE. In IDEA you can run or debug the `Application` class
straight away. Micros Spring Boot will automatically use pretty printed logging when running locally, but will
activate JSON based logging when deployed to Micros.

The Spring Boot configuration processor is included in the pom and it will enable your IDE to index and
[autocomplete configuration property names](http://www.mdoninger.de/2015/05/16/completion-for-custom-properties-in-spring-boot.html).


## Setting up Docker credentials on Mac OS X

With the latest Docker for Mac (17 onwards), Docker registry credentials are stored in the OSX keychain. The `docker-maven-plugin` we use can't access that and you get a cryptic error like:

```
Execution docker-build of goal io.fabric8:docker-maven-plugin:0.19.0:build failed: JSONObject["auth"] not found
```

Instead it can retrieve the credentials from your `~/.m2/settings.xml`. To do this:

1. You should set up [Encrypted Maven credentials](https://hello.atlassian.net/wiki/spaces/RELENG/pages/137955973/HOWTO+-+Set+up+your+maven+settings+securely). This will set up a master password that you can use to encrypt your Docker credentials.
2. Get the encrypted version of your StaffID password by running `mvn --encrypt-password`
3. Create a new `server` entry in your `~/.m2/settings.xml`:

```
...
<servers>
    ... a bunch of servers e.g. atlassian-m2-repository
    <server>
        <id>docker.atl-paas.net</id>
        <username>(LDAP username, not full email)</username>
        <password>(The encrypted password from step 2)</password>
    </server>
</servers>
...
```



## New Relic integration

1. Login into New Relic via Centrify and click add new app
2. Get the license key and stash it into a environment variable `NEW_RELIC_LICENSE_KEY` in the environments you want to enable it in
3. Stash or set in your service descriptor `NEW_RELIC_APP_NAME` for environments which are not production which have New Relic enabled

## Capacity Testing

Sample locust capacity tests have been added to this respository. They can be run using PerfHammer through the [PerfView UI](https://perfview.prod.atl-paas.net/). For a more in-depth tutorial, check out the [PerfHammer docs](https://developer.atlassian.com/platform/perfhammer/).

### Setup

To setup capacity testing, follow the docs [here](https://developer.atlassian.com/platform/perfhammer/101/2-getting-setup/).

### Writing the script

The script for capacity testing is quite simple, which can be viewed with an in-depth overview [here](https://developer.atlassian.com/platform/perfhammer/101/3-your-first-script/).

### Running PerfHammer tests

_Before getting the tests on to PerfHammer, ensure that your services are deployed to the `stg-east` and `prod-east environments` in Micros (the sample test will be hitting healthcheck endpoints within those environments)._

After your capacity testing scripts are written & tested locally, the last thing to do is to get them into PerfHammer. For a straightforward tutorial on how to do this, checkout this [guide](https://developer.atlassian.com/platform/perfhammer/101/4-running-perfhammer-tests/).

- If you are creating repositories in Bitbucket Cloud or within your personal space on Stash, make sure you give PerfHammer [access](https://developer.atlassian.com/platform/perfhammer/101/4-running-perfhammer-tests/) to read your repo.




## Slauth Authentication

This service includes base configuration for authentication using the Slauth sidecar.
You can find docs for the sidecar [on DAC](https://developer.atlassian.com/platform/slauth/).

You can read about the security features [here](https://developer.atlassian.com/platform/framework/micros-spring-boot/features/security/).

**N.B.** Using Slauth via Service Proxy involves configuration that looks different than using Slauth as a service sidecar. Make sure you refer to the appropriate section in the docs for [Service Proxy usage](https://developer.atlassian.com/platform/slauth/serviceproxy/configuration/)(recommended) or for [Service Sidecar usage](https://developer.atlassian.com/platform/slauth/sidecar/configuration/).

## Quickstart
You will find the following configuration in your `application.yaml` file:
```yaml
micros:
    rest.asap.enabled: false
    default-permitted-role: access
    security:
        slauth:
            default-granted-role: access # any authenticated request can access all endpoints
        roles:
            api:
                greetings.admin: "micros-sv--@project.name@-dl-admins" # only service admins can access /api/greetings/admin
```
This means that:
* your service will require authentication on all requests to your service
* only service admins will be able to ping the `/api/greetings/admin` endpoint
    * you can manage service admins [via SSAM](https://hello.atlassian.net/wiki/spaces/MICROS/pages/169258919/Managing+team+members+with+SSAM). 

### Local development

Once your service is running in Micros, the Slauth sidecar will handle authentication of all requests. You can configure
what type of requests (e.g. ASAP, Slauthtoken, etc.) by configuring the [sidecar plugins](https://developer.atlassian.com/platform/slauth/sidecar/plugins/).

For local development, your service will expect `X-Slauth-*` headers, which indicate a request was authenticated by SLAuth.
You could mock these yourself via:
```shell script
curl localhost:8080/api/greetings/user -H X-Slauth-Subject:you -H X-Slauth-Mechanism:slauthtoken -H X-Slauth-Principal:slauth
```
or you could use the `slauth-mock-sidecar`. This is a docker image that will proxy requests to your service, injecting
Slauth headers on all requests. You can configure these headers to emulate different users via the control panel.

You can run your service with the `slauth-mock-sidecar` via nebulae by running:
```shell script
atlas nebulae start
```  
You can access the control panel at [localhost:9090](http://localhost:9090/) and can reach your service through the
`slauth-mock-sidecar` via `localhost:8080`.

You can find more docs on using the mock sidecar [on DAC](http://developer.atlassian.com/platform/slauth/sidecar/localtesting). 

## Poco

[Poco](https://developer.atlassian.com/platform/poco/) enables you to create an authorization policy for your service in a declarative way. For info, on how this works see [DAC docs](https://developer.atlassian.com/platform/poco/ingress/overview/).

### Installation

Make sure you have the Poco CLI installed following the instructions [here](https://developer.atlassian.com/platform/poco/cli/installation/).

### Authoring

Your policy is contained within the `src/main/resources/policies/service` directory. The `policy.json` file declares what type of requests should be allowed to which endpoints, and by default any other requests are denied. You can find more documentation on writing policies [here](https://developer.atlassian.com/platform/poco/policies/kinds/slauth/).

**You should update this policy to reflect that endpoints and authorization model that your service needs. The provided policy serves simply as a skeleton for you to write your own.**

### Testing

Once your policy has been written, you can write tests to assert that it behaves in the way you intend. The `tests.json` contains a few basic tests, but there will change as your policy gets updated. You can find more docs on testing your policy [here](https://developer.atlassian.com/platform/poco/ingress/testing/).

You can run tests for your policy via:

```
atlas poco bundle test -b service -t tests.json
```

### Publishing

Once your policy behaves as intended, you can publish the policy following the steps on [this page](https://developer.atlassian.com/platform/poco/ingress/publishing/).


### Monitoring

Poco provides metrics and dashboards for you to glean insights from decision logs, monitor policy updates, how long Poco takes to authorize the request, and more. You can find more on info on that [here](https://developer.atlassian.com/platform/poco/ingress/monitoring/).


### Synk & Sourceclear
**Please note that Sourceclear will be deprecated at the end of 2021.** You should try to use Synk as soon as you can.
For more details on Synk: See [this page](https://hello.atlassian.net/wiki/spaces/PRODSEC/blog/2021/07/17/1245672878/We+re+removing+SourceClear+what+happens+next+will+SHOCK+YOU)

## Interesting endpoints

- `/env`: shows the current profile, system properties, and environment variables.
- `/mappings`: shows all the URLs exposed by the service.
- `/beans`: shows the Spring context.
- `/dump`: prints a thread dump.
- `/trace`: shows details about the last 100 HTTP requests processed (headers, response code, etc.)
- Note: most of these debug endpoints are protected and will require additional credentials (see the configuration file). Different Usr/pwd values can set per environment using `micros:stash`.
