package com.atlassian.fisc.erp.connector.util;

import com.atlassian.fisc.erp.connector.config.FiscErpConnectorConfig;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorLookup;
import com.atlassian.fisc.erp.connector.constant.FormatConstant;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldRef;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.atlassian.fisc.erp.connector.domain.common.RecordType;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtil {
  private static final ObjectMapper mapper =
      new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  public static RecordRef recordRef(String internalId, String externalId, RecordType type) {
    RecordRef recordRef = new RecordRef();
    recordRef.setType(type);
    recordRef.setInternalId(internalId);
    recordRef.setExternalId(externalId);
    return recordRef;
  }

  public static Calendar calDate(int year, int month, int date) {
    Calendar cal = Calendar.getInstance();
    cal.set(year, month, date);
    return cal;
  }

  @SneakyThrows
  public static long epoch(String dateTime) {
    return FormatConstant.formatHolderDateAndTime.get().parse(dateTime).toInstant().toEpochMilli();
  }

  public static long epoch(Calendar cal) {
    return epoch(FormatConstant.formatHolderDateAndTime.get().format(cal.getTime()));
  }

  @SneakyThrows
  public static <T> T deserializeResource(final String resource, final Class<T> clazz) {
    return mapper.readValue(new ClassPathResource(resource).getFile(), clazz);
  }

  public static void stubConfigProperties(FiscErpConnectorConfig configProperties) {
    Mockito.lenient()
        .when(configProperties.getCountry())
        .thenReturn(Map.of("_unitedStates", "US", "US", "_unitedStates"));

    Mockito.lenient()
        .when(configProperties.getCurrency())
        .thenReturn(Map.of("USD", "1", "1", "USD"));

    Mockito.lenient()
            .when(configProperties.getReceiptMethod())
            .thenReturn(Map.of("PTY HAMS Stripe JPY", "Pty JPY-5013","PTY HAMS Stripe AU-USD", "Pty USD-2048","PTY WIRE AU-AUD","Pty AUD-2014","PTY WIRE EUR","Pty EUR-2030","PTY HAMS Stripe CA-USD","Pty USD-7532","PTY WIRE US-USD","Pty USD-2048","PTY WIRE JPY","Pty JPY-8040","PTY HAMS Paypal USD","Pty USD-3898"));

    Mockito.lenient()
        .when(configProperties.getPaymentMethod())
        .thenReturn(Map.of("7", "PAIDCC","8", "PAID","9","PAIDPAYPAL"));

    Mockito.lenient()
        .when(configProperties.getNsPaymentMethod())
        .thenReturn(Map.of("PAIDCC", "7","PAID", "8","PAIDPAYPAL","9"));
  }

  public static void stubLookupProperties(FiscErpConnectorLookup lookupProperties) {
    Mockito.lenient()
        .when(lookupProperties.byItem())
        .thenReturn(LookupTestDataUtil.testDataByItem());

    Mockito.lenient()
        .when(lookupProperties.bySpokeSystemItem())
        .thenReturn(LookupTestDataUtil.testDatBySpokeItem());
  }

  @SneakyThrows
  public static Calendar catDateTime(String dateTime) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(FormatConstant.formatHolderDateAndTime.get().parse(dateTime));
    return calendar;
  }

  public static CustomFieldList toCustomFieldList(List<CustomFieldRef> customFields) {
    CustomFieldList customFieldList = new CustomFieldList();
    customFieldList.setCustomField(customFields.toArray(new CustomFieldRef[] {}));
    return customFieldList;
  }

  public static void removeCustomField(CustomFieldList custFieldList, String custFieldName) {
    List<CustomFieldRef> customFieldList =
        new LinkedList<>(Arrays.asList(custFieldList.getCustomField()));
    customFieldList.removeIf(custField -> custFieldName.equals(custField.getScriptId()));
    custFieldList.setCustomField(
        customFieldList.toArray(new CustomFieldRef[customFieldList.size()]));
  }
}
