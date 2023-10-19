package com.atlassian.fisc.erp.connector.lookups;

import com.atlassian.finance.enums.BankAccountRef;
import com.atlassian.finance.enums.ReceiptMethod;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorConfig;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorLookup;
import com.atlassian.fisc.erp.connector.domain.common.Country;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
@Slf4j
public class LookupHelper {
  //  @Autowired by constructor
  private final FiscErpConnectorConfig configProperties;
  private final FiscErpConnectorLookup lookupProperties;

  public String currency(RecordRef nsCurrencyRr) {
    return null != nsCurrencyRr ? currency(nsCurrencyRr.getInternalId()) : null;
  }

  public String currency(String nsCurrency) {
    return null != nsCurrency ? configProperties.getCurrency().get(nsCurrency) : null;
  }

  public BankAccountRef bankAccountRef(ReceiptMethod bankAccountRef) {
    return null != bankAccountRef
        ? BankAccountRef.valueOfLabel(
            configProperties.getReceiptMethod().get(bankAccountRef.getName()))
        : null;
  }

  public void paymentMethod(RecordRef nsPaymentMethodRr) {
    if (isNull(nsPaymentMethodRr) || isNull(nsPaymentMethodRr.getInternalId())) return;
    var paymentMethodMap = configProperties.getPaymentMethod();
    String paymentMethod =
        null != paymentMethodMap ? paymentMethodMap.get(nsPaymentMethodRr.getInternalId()) : null;
    nsPaymentMethodRr.setInternalId(paymentMethod);
  }

  public void nsPaymentMethod(RecordRef paymentMethodRr) {
    if (isNull(paymentMethodRr) || isNull(paymentMethodRr.getInternalId())) return;
    var nsPaymentMethodMap = configProperties.getNsPaymentMethod();
    String naPaymentMethod =
        null != nsPaymentMethodMap ? nsPaymentMethodMap.get(paymentMethodRr.getInternalId()) : null;
    paymentMethodRr.setInternalId(naPaymentMethod);
  }

  public void nsCurrency(RecordRef currencyRr) {
    if (isNull(currencyRr) || isNull(currencyRr.getInternalId())) return;
    String nsCurrency = configProperties.getCurrency().get(currencyRr.getInternalId());
    currencyRr.setInternalId(nsCurrency);
  }

  public String country(Country country) {
    if (null == country) return null;
    if (null == country.getValue()) return null;
    return configProperties.getCountry().get(country.getValue());
  }

  public Country nsCountry(String country) {
    if (null == country) return null;
    String nsCountry = configProperties.getCountry().get(country);
    return null != nsCountry ? Country.fromString(nsCountry) : null;
  }

  public String itemOrDefault(String spokeSystemItem, String defaultValue) {
    String value = item(spokeSystemItem);
    return null != value ? value : defaultValue;
  }

  public String spokeSystemItemOrDefault(String item, String defaultValue) {
    String value = spokeSystemItem(item);
    return null != value ? value : defaultValue;
  }

  public String item(String spokeSystemItem) {
    if (null == spokeSystemItem) return null;
    var map = lookupProperties.bySpokeSystemItem();
    return null != map ? map.get(spokeSystemItem) : null;
  }

  public String spokeSystemItem(String item) {
    if (null == item) return null;
    var map = lookupProperties.byItem();
    return null != map ? map.get(item) : null;
  }
}
