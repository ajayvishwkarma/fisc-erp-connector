package com.atlassian.fisc.erp.connector.config;

import com.atlassian.fisc.erp.connector.util.CommonUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Configuration
@ConfigurationProperties(prefix = "fisc-erp-connector")
@Setter
@Slf4j
@java.lang.SuppressWarnings("unused")
public class FiscErpConnectorConfig {
  //  TODO split bi-directional mapping
  private Map<String, String> currency;
  private Map<String, String> country;
  private Map<String, String> bankAccountRef;

  private Map<String, String> paymentMethod;

  private Map<String, String> nsPaymentMethod;

  public Map<String, String> getCurrency() {
    return requireNonNull(currency, "Currency lookup data not available");
  }
  public Map<String, String> getReceiptMethod() {
    return requireNonNull(bankAccountRef, "bankAccountRef lookup data not available");
  }

  public Map<String, String> getCountry() {
    return requireNonNull(country, "Country lookup data not available");
  }

  public Map<String, String> getPaymentMethod() {
    return requireNonNull(paymentMethod, "PaymentMethod lookup data not available");
  }

  public Map<String, String> getNsPaymentMethod() {
    return requireNonNull(nsPaymentMethod, "PaymentMethod lookup data not available");
  }

  @PostConstruct
  public void postConstruct() {
    if (null != paymentMethod) {
      nsPaymentMethod = CommonUtil.invertMap(paymentMethod);
      log.info("Reverse mapping computed for payment method");
    }
  }
}
