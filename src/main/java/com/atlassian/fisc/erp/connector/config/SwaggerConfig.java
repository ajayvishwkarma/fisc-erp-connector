package com.atlassian.fisc.erp.connector.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
  public static final String API_GROUP_NAME = "api";

  @Value("${swagger.app.url}")
  private String swaggerUrl;

  @Bean
  OpenAPI apiDefinition() {
    return new OpenAPI()
        .info(
            new Info()
                .title("FISC ERP Connector")
                .description("ERP connector for FISC")
                .version("1.0")
                .contact(
                    new Contact()
                        .name("Commerce IT BO")
                        .url("https://atlassian.slack.com/archives/C01C04D47KR")))
        .servers(Collections.singletonList(new Server().url(this.swaggerUrl)))
        .components(
            new Components()
                .addSecuritySchemes(
                    "ASAP",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.HEADER)
                        .name("Authorization")))
        .addSecurityItem(new SecurityRequirement().addList("ASAP"));
  }

  @Bean
  GroupedOpenApi mappingApis(@Autowired OpenApiCustomiser openApiCustomiser) {
    return GroupedOpenApi.builder()
        .group(API_GROUP_NAME)
        .addOpenApiCustomiser(openApiCustomiser)
        .packagesToScan("com.atlassian.fisc.erp.connector.controller")
        .build();
  }

  @Bean
  public OpenApiCustomiser openApiCustomiser() {
    return openApi ->
        openApi
            .getComponents()
            .getSchemas()
            .values()
            .forEach(s -> s.setAdditionalProperties(false));
  }
}
