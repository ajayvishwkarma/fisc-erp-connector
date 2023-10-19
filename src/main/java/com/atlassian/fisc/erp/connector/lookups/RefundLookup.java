package com.atlassian.fisc.erp.connector.lookups;

import com.atlassian.finance.bdm.RefundBDM;
import com.atlassian.fisc.erp.connector.domain.refund.CustomerRefund;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RefundLookup {
  private final LookupHelper helper;

  public CustomerRefund preMapNsFields(CustomerRefund nsRefund) {
    // payment method lookup
    helper.paymentMethod(nsRefund.getPaymentMethod());
    return nsRefund;
  }

  public RefundBDM forBdmFields(RefundBDM refundBDM, CustomerRefund nsRefund) {
    // currency
    refundBDM.setCurrency(helper.currency(nsRefund.getCurrency()));

    //  TODO handle other lookups

    return refundBDM;
  }

  @java.lang.SuppressWarnings(
      "squid:S1172") // pattern needs refundBDM param, will be used in future
  public CustomerRefund forNsFields(CustomerRefund nsRefund, RefundBDM refundBDM) {
    //  currency
    helper.nsCurrency(nsRefund.getCurrency());

    helper.nsPaymentMethod(nsRefund.getPaymentMethod());
    //  TODO handle other lookups

    return nsRefund;
  }
}
