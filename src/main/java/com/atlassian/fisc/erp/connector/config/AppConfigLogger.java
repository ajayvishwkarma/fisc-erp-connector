package com.atlassian.fisc.erp.connector.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@Slf4j
public class AppConfigLogger {
  // Exception cases, that are safe to log
  protected String[] maskPropertyKeyWhiteListPatterns = {
    // safe to log though it has the word "private"
    ".version" // e.g., micros.private.micros-spring-boot.version
  };

  protected String[] maskPropertyKeyPatterns = {
    "private", // e.g., micros.rest.asap.private-key-data-uri
    "pass", // e.g., password, passphrase
    "secret", // e.g., secretKey
    "token" // e.g., ASAP_TOKEN
  };

  @Value("${spring.application.name:undefined-app-name}")
  private String appName;

  @EventListener
  public void logAllProperties(ContextRefreshedEvent event) {
    final var environment = event.getApplicationContext().getEnvironment();
    log.info("===================== {}: CONFIGURATION BEGIN =====================", appName);
    log.info("Active profiles: {}", Arrays.toString(environment.getActiveProfiles()));
    PropertySources sources = ((AbstractEnvironment) environment).getPropertySources();
    var sourceNames =
        sources.stream().map(PropertySource::getName).collect(Collectors.joining(", "));
    log.info("Property sources: {}", sourceNames);
    StreamSupport.stream(sources.spliterator(), false)
        .filter(EnumerablePropertySource.class::isInstance)
        .map(EnumerablePropertySource.class::cast)
        .map(EnumerablePropertySource::getPropertyNames)
        .flatMap(Arrays::stream)
        .distinct()
        .forEach(property -> logProperty(environment, property));
    log.info("===================== {}: CONFIGURATION END =====================", appName);
  }

  private void logProperty(Environment environment, String property) {
    var value = needsMasking(property) ? "XXXXXX" : environment.getProperty(property);
    log.info("{}: {}", property, value);
  }

  /**
   * Function to check if masking of property value is needed In certain scenarios the masking check
   * needs to be fine-tuned and hence encapsulating in a separate method
   *
   * @param property
   * @return 'true' if the property value should be masked
   */
  protected boolean needsMasking(String property) {
    // do not mask whitelisted pattern
    if (StringUtils.containsAnyIgnoreCase(property, maskPropertyKeyWhiteListPatterns)) {
      return false;
    }

    return StringUtils.containsAnyIgnoreCase(property, maskPropertyKeyPatterns);
  }
}
