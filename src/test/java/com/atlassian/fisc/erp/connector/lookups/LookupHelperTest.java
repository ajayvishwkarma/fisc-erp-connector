package com.atlassian.fisc.erp.connector.lookups;

import com.atlassian.finance.enums.ReceiptMethod;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorConfig;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorLookup;
import com.atlassian.fisc.erp.connector.domain.common.Country;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.atlassian.fisc.erp.connector.util.LookupTestDataUtil;
import com.atlassian.fisc.erp.connector.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LookupHelperTest {
  @Mock private FiscErpConnectorConfig configProperties;
  @Mock private FiscErpConnectorLookup lookupProperties;

  private LookupHelper lookupHelper;

  @BeforeEach
  void setUp() {
    TestUtil.stubConfigProperties(configProperties);
    TestUtil.stubLookupProperties(lookupProperties);
    lookupHelper = new LookupHelper(configProperties, lookupProperties);
  }

  @Test
  void currency() {
    assertNotNull(configProperties.getCurrency().get("USD"));
    assertNotNull(configProperties.getCurrency().get("1"));
    assertNull(lookupHelper.currency((RecordRef) null));
    assertEquals(
        configProperties.getCurrency().get("1"),
        lookupHelper.currency(TestUtil.recordRef("1", null, null)));
    assertNull(lookupHelper.currency((String) null));
    assertEquals(configProperties.getCurrency().get("USD"), lookupHelper.currency("USD"));
  }

  @Test
  void country() {
    assertNotNull(configProperties.getCountry().get("_unitedStates"));
    assertNotNull(configProperties.getCountry().get("US"));
    assertNull(lookupHelper.currency((RecordRef) null));
    assertEquals(
        configProperties.getCountry().get("_unitedStates"),
        lookupHelper.country(Country.fromValue("_unitedStates")));
    assertNull(lookupHelper.nsCountry((String) null));
    assertEquals(configProperties.getCountry().get("US"), lookupHelper.nsCountry("US").toString());
  }

  @Test
  void bankAccountRef() {
    assertNotNull(configProperties.getReceiptMethod().get("PTY HAMS Stripe AU-USD"));
    assertNull(lookupHelper.bankAccountRef((ReceiptMethod) null));
    assertEquals(
            configProperties.getReceiptMethod().get("PTY HAMS Stripe AU-USD"),
            lookupHelper.bankAccountRef(ReceiptMethod.PTY_HAMS_STRIPE_AU_USD).getName());
    assertNull(lookupHelper.bankAccountRef((ReceiptMethod) null));
  }

  @Test
  void paymentMethod() {
    lookupHelper.paymentMethod(null);
    RecordRef rr = new RecordRef();
    lookupHelper.paymentMethod(rr);
    assertNull(rr.getInternalId());
    assertNotNull(configProperties.getPaymentMethod().get("7"));
    rr.setInternalId("7");
    lookupHelper.paymentMethod(rr);
    assertEquals(configProperties.getPaymentMethod().get("7"), rr.getInternalId());
  }

  @Test
  void nsPaymentMethod() {
    lookupHelper.nsPaymentMethod(null);
    RecordRef rr = new RecordRef();
    lookupHelper.nsPaymentMethod(rr);
    assertNull(rr.getInternalId());
    assertNotNull(configProperties.getNsPaymentMethod().get("PAIDCC"));
    rr.setInternalId("PAIDCC");
    lookupHelper.nsPaymentMethod(rr);
    assertEquals(configProperties.getNsPaymentMethod().get("PAIDCC"), rr.getInternalId());
  }

  @Test
  void item() {
    Map<String, String> bySpokeItem = LookupTestDataUtil.testDatBySpokeItem();
    String spokeItem = "14672";
    String item = bySpokeItem.get(spokeItem);
    assertNotNull(item);
    assertNull(lookupHelper.item(null));
    assertEquals(item, lookupHelper.item(spokeItem));
    assertNull(lookupHelper.spokeSystemItem(null));
    assertEquals(spokeItem, lookupHelper.spokeSystemItem(item));
  }

  @Test
  void itemOrDefault() {
    Map<String, String> bySpokeItem = LookupTestDataUtil.testDatBySpokeItem();
    String spokeItem = "14672";
    String item = bySpokeItem.get(spokeItem);
    assertNotNull(item);
    assertEquals(item, lookupHelper.itemOrDefault(spokeItem, "default"));
    assertEquals("default", lookupHelper.itemOrDefault(spokeItem + "random...", "default"));
  }
}
