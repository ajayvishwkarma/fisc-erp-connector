package com.atlassian.fisc.erp.connector;

import com.atlassian.micros.security.config.EnableMicrosConfigurableVoter;
import com.atlassian.micros.security.config.EnableMicrosServerSecurity;
import com.atlassian.slauth.config.EnableSLAuthSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableSLAuthSecurity
@EnableMicrosServerSecurity
@EnableMicrosConfigurableVoter
@EnableCaching
@SpringBootApplication(
    scanBasePackages = {
      "com.atlassian.fisc.erp.connector.*",
      "com.atlassian.finsys.micros.error",
      "com.atlassian.finsys.micros.metrics",
      "com.atlassian.finsys.micros.logging",
      "com.atlassian.finsys.micros.connector.httpclient"
    })
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }
}
