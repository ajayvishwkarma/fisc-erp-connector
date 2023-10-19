package com.atlassian.fisc.erp.connector.lookups;


import com.atlassian.finance.bdm.PaymentBDM;
import com.atlassian.fisc.erp.connector.domain.payment.CustomerPayment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentLookup {
  private final LookupHelper helper;

  public PaymentBDM forBdmFields(PaymentBDM paymentBDM, CustomerPayment nsPayment) {
    // currency
    paymentBDM.setCurrency(helper.currency(nsPayment.getCurrency()));

    //BankAccountRef
    paymentBDM.setBankAccountRef(helper.bankAccountRef(paymentBDM.getPaymentReceiptMethod()));//Pay-Row 20

    //  TODO handle other lookups
    return paymentBDM;
  }

  @java.lang.SuppressWarnings(
      "squid:S1172") // pattern needs paymentBDM param, will be used in future
  public CustomerPayment forNsFields(CustomerPayment nsPayment, PaymentBDM paymentBDM) {
    //  currency
    helper.nsCurrency(nsPayment.getCurrency());

    // TODO: Lookup for customer

    // TODO: Lookup for account Receivable

    // TODO: Lookup for payment method

    return nsPayment;
  }
}
