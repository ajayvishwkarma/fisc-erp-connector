package com.atlassian.fisc.erp.connector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.atlassian.micros.config.MicrosSecurityConstants.CUSTOM_ANONYMOUS_PATHS;

@Configuration
public class SecurityConfig {

  /**
   * This tells the security starter which paths do not require authentication, you can use ANT path
   * style wild cards.
   */
  @Bean(name = CUSTOM_ANONYMOUS_PATHS)
  public List<String> anonymousPaths() {
    return List.of(
        "/heartbeat", "/deepcheck", "/swagger-ui.html", "/swagger-ui/**", "/api-docs/**", "/api/**");
  }
}
