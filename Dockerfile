FROM docker.atl-paas.net/sox/micros-java-11:v1.4.10

# these labels help Security track down any vulnerabilities we may have received
# when we generated our service with Instant Micros
LABEL "instant-micros-template-name"="spring-boot"
LABEL "instant-micros-template-version"="cf1979a129b152d4dc61f739650f7768f427531a"

WORKDIR /opt/service

COPY ./target/fisc-erp-connector-*.jar /opt/service/service.jar
