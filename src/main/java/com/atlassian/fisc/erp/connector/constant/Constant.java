package com.atlassian.fisc.erp.connector.constant;

import com.atlassian.fisc.erp.connector.buildppln.JacocoCoverageExcludeGenerated;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Constant {
  @JacocoCoverageExcludeGenerated
  private Constant() {
    throw new IllegalStateException("Utility class");
  }

  public static final String CACHE_NAME_SPOKE_ITEM_MAP = "spokeItemMap-cache";

  public static final String CFLU_87 = "87";

  public static final String HTTP_SLAUTH_HEADER = "X-SLAuth-Egress";
  public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";
  public static final String SOURCE_SYSTEM_HAMS = "HAMS";
  public static final String TRANSACTION_SOURCE_SYSTEM_HAMS = "HAMS_INTEGRATION";
  public static final String INVOICE_FORM_TYPE = "HAMS_INV";
  public static final String CREDITMEMO_FORM_TYPE = "HAMS_CM";
  public static final String ATLASSIAN_AU_BU_USD_BUSINESS_UNIT_NAME = "ATLASSIAN_AU_BU_USD";

  public static final String INV_TERM_IMMEDIATE = "IMMEDIATE";

  public static final String ITEM_DEFAULT = "Cloud (monthly):CFLU";
  public static final String SPOKE_SYSTEM_DEFAULT = "15195|87";
  public static final String STRIPE_NL = "Stripe NL";
  public static final String STRIPE_AU = "Stripe AU";
  public static final String STRIPE_CA = "Stripe CA";

  public static final String USD = "3";//TODO :REPLACE 3 TO USD
  public static final String JPY = "5";//TODO :REPLACE 5 TO JPY
  public static final String AUD = "1";//TODO :REPLACE 1 TO AUD
  public static final String PAID_CC = "7";//TODO: REPLACE 9 TO PAIDCC
  public static final String PAID_PAYPAL = "9";//TODO: REPLACE 9 TO PAIDPAYPAL
  public static final String USD_NAME = "USD";
  public static final String JPY_NAME = "JPY";
  public static final String AUD_NAME = "AUD";
  public static final String PAID_CC_NAME = "PAIDCC";
  public static final String PAID_PAYPAL_NAME = "PAIDPAYPAL";
  public static final String PAID_ON_TERMS="3";
  public static final String STATUS_PAID  = "PAID";
  public static final String STATUS_OPEN  = "Open";
  public static final String INVOICE_STATUS_PAID_IN_FULL = "Paid In Full";
  public static final String CREDIT_MEMO_STATUS_FULLY_APPLIED = "Fully Applied";

  public static final Set<String> PAID_CC_RECEIPT_METHODS = new HashSet<>(List.of("PTY HAMS Stripe NL-USD", "PTY HAMS Stripe AU-USD", "PTY HAMS Stripe CA-USD", "PTY HAMS Stripe US-USD", "PTY HAMS Stripe JPY"));
  public static final Set<String> PAID_PAYPAL_RECEIPT_METHODS = new HashSet<>(List.of("PTY HAMS Paypal USD", "PTY HAMS Paypal JPY"));
}
