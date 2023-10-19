package com.atlassian.fisc.erp.connector.mappers;

import com.atlassian.finance.enums.ReceiptMethod;
import com.atlassian.fisc.erp.connector.buildppln.JacocoCoverageExcludeGenerated;
import org.apache.commons.lang3.StringUtils;

import static com.atlassian.fisc.erp.connector.constant.Constant.*;

public class MapperUtil {
  @JacocoCoverageExcludeGenerated
  private MapperUtil() {
    throw new IllegalStateException("Utility class");
  }

  public static ReceiptMethod getReceiptMethod(String currency, String paymentMethod, String stripePaymentGwy) {
    if (StringUtils.isBlank(currency)) {
      return null;
    }

    switch (currency) {
      case (USD):
      case (USD_NAME): {
        return getUSDReceiptMethod(paymentMethod, stripePaymentGwy);
      }

      case (JPY):
      case (JPY_NAME): {
        return getJPYReceiptMethod(paymentMethod, stripePaymentGwy);
      }

      case (AUD_NAME):
      case (AUD): {
        return getAUDReceiptMethod(paymentMethod, stripePaymentGwy);
      }


      default:
        break;
    }
    return null;
  }

  private static ReceiptMethod getUSDReceiptMethod(String paymentMethod, String stripePaymentGwy){
    if (PAID_PAYPAL.equals(paymentMethod) || PAID_PAYPAL_NAME.equals(paymentMethod)) {
      return ReceiptMethod.PTY_HAMS_PAYPAL_USD;
    }
    if (PAID_CC.equals(paymentMethod) || PAID_CC_NAME.equals(paymentMethod)) {
      if (StringUtils.isBlank(stripePaymentGwy)) return null;
      switch (stripePaymentGwy) {
        case STRIPE_NL:
          return ReceiptMethod.PTY_HAMS_STRIPE_NL_USD;
        case STRIPE_AU:
          return ReceiptMethod.PTY_HAMS_STRIPE_AU_USD;
        case STRIPE_CA:
          return ReceiptMethod.PTY_HAMS_STRIPE_CA_USD;
        default:
          return ReceiptMethod.PTY_HAMS_STRIPE_US_USD;
      }
    }
    return null;
  }

  private static ReceiptMethod getJPYReceiptMethod(String paymentMethod, String stripePaymentGwy){
    if (StringUtils.isBlank(paymentMethod)) return null;
    switch (paymentMethod) {
      case PAID_CC:
      case PAID_CC_NAME:
        return ReceiptMethod.PTY_HAMS_STRIPE_JPY;
      case PAID_PAYPAL:
      case PAID_PAYPAL_NAME:
        return ReceiptMethod.PTY_HAMS_PAYPAL_JPY;
      default:
        return ReceiptMethod.PTY_WIRE_JPY;
    }
  }

  private static ReceiptMethod getAUDReceiptMethod(String paymentMethod, String stripePaymentGwy){
    if (StringUtils.isBlank(paymentMethod)) return null;
    switch (paymentMethod) {
      case PAID_CC:
      case PAID_CC_NAME:
      case PAID_PAYPAL:
      case PAID_PAYPAL_NAME:
        return null;
      default:
        return ReceiptMethod.PTY_WIRE_AU_AUD;
    }
  }

}
