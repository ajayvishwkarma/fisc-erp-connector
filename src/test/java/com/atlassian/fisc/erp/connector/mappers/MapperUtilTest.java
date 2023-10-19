package com.atlassian.fisc.erp.connector.mappers;

import org.junit.jupiter.api.Test;

import static com.atlassian.finance.enums.ReceiptMethod.*;
import static com.atlassian.fisc.erp.connector.constant.Constant.*;
import static com.atlassian.fisc.erp.connector.mappers.MapperUtil.getReceiptMethod;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MapperUtilTest {
  @Test
  public void getReceiptMethod_nullChecks() {
    assertNull(getReceiptMethod("nonblank", null, null));
    assertNull(getReceiptMethod("", null, null));
  }

  @Test
  public void getReceiptMethod_USD() {
    assertNull(getReceiptMethod(USD, null, " "));
    assertEquals(PTY_HAMS_PAYPAL_USD, getReceiptMethod(USD, PAID_PAYPAL, " "));
    assertNull(getReceiptMethod(USD, PAID_CC, " "));
    assertEquals(
        PTY_HAMS_STRIPE_NL_USD, getReceiptMethod(USD, PAID_CC, STRIPE_NL));
    assertEquals(
        PTY_HAMS_STRIPE_NL_USD, getReceiptMethod(USD_NAME, PAID_CC_NAME, STRIPE_NL));
    assertEquals(
        PTY_HAMS_STRIPE_NL_USD, getReceiptMethod(USD, PAID_CC, STRIPE_NL));
    assertEquals(
        PTY_HAMS_STRIPE_NL_USD, getReceiptMethod(USD_NAME, PAID_CC_NAME, STRIPE_NL));
    assertEquals(
        PTY_HAMS_STRIPE_AU_USD, getReceiptMethod(USD, PAID_CC, STRIPE_AU));
    assertEquals(
        PTY_HAMS_STRIPE_AU_USD, getReceiptMethod(USD_NAME, PAID_CC_NAME, STRIPE_AU));
    assertEquals(
        PTY_HAMS_STRIPE_CA_USD, getReceiptMethod(USD, PAID_CC, STRIPE_CA));
    assertEquals(
        PTY_HAMS_STRIPE_CA_USD, getReceiptMethod(USD_NAME, PAID_CC_NAME, STRIPE_CA));
    assertEquals(
        PTY_HAMS_STRIPE_US_USD, getReceiptMethod(USD, PAID_CC, "nonblank"));
    assertEquals(
        PTY_HAMS_STRIPE_US_USD, getReceiptMethod(USD_NAME, PAID_CC_NAME, "nonblank"));
  }

  @Test
  public void getReceiptMethod_JPY() {
    assertNull(getReceiptMethod(JPY, " ", " "));
    assertNull(getReceiptMethod(JPY_NAME, " ", " "));
    assertEquals(PTY_HAMS_STRIPE_JPY, getReceiptMethod(JPY, PAID_CC, " "));
    assertEquals(PTY_HAMS_STRIPE_JPY, getReceiptMethod(JPY_NAME, PAID_CC_NAME, " "));
    assertEquals(PTY_HAMS_PAYPAL_JPY, getReceiptMethod(JPY, PAID_PAYPAL, " "));
    assertEquals(PTY_HAMS_PAYPAL_JPY, getReceiptMethod(JPY_NAME, PAID_PAYPAL_NAME, " "));
    assertEquals(PTY_WIRE_JPY, getReceiptMethod(JPY, "random", " "));
    assertEquals(PTY_WIRE_JPY, getReceiptMethod(JPY_NAME, "random", " "));
  }

  @Test
  public void getReceiptMethod_AUD() {
    assertEquals(PTY_WIRE_AU_AUD, getReceiptMethod(AUD, "random", " "));
    assertEquals(PTY_WIRE_AU_AUD, getReceiptMethod(AUD_NAME, "random", " "));
    assertNull(getReceiptMethod(AUD, PAID_CC, " "));
    assertNull(getReceiptMethod(AUD_NAME, PAID_CC_NAME, " "));
    assertNull(getReceiptMethod(AUD, PAID_PAYPAL, " "));
    assertNull(getReceiptMethod(AUD_NAME, PAID_PAYPAL_NAME, " "));
  }
}
