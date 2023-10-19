package com.atlassian.fisc.erp.connector.mappers.payment;

import com.atlassian.finance.bdm.PaymentApplyLineBDM;
import com.atlassian.finance.bdm.PaymentBDM;
import com.atlassian.finance.enums.ReceiptMethod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PaymentTestGen {
  public PaymentBDM generateDummyData() {
    PaymentBDM payment = new PaymentBDM();
    payment.setCurrency("USD");
    payment.setMemo("memo");
    payment.setPaymentMethod("PAIDCC");
    payment.setStatus("PAID");
    payment.setId("id");
    payment.setTransactionNumber("transactionNumber");
    payment.setAccountReceivable("accountReceivable");
    payment.setCustomerId("customerId");
    payment.setTransactionCreateDate("2023-01-25T08:32:30+05:30");
    payment.setTotalAmount(BigDecimal.valueOf(121.1d));
    payment.setPaymentApplyLines(createDummyPaymentAddressLines());
    payment.setPaymentReceiptMethod(ReceiptMethod.PTY_HAMS_PAYPAL_JPY);
    return payment;
  }

  private List<PaymentApplyLineBDM> createDummyPaymentAddressLines() {
    PaymentApplyLineBDM paymentApplyLine1 = new PaymentApplyLineBDM();
    paymentApplyLine1.setAmount(new BigDecimal("51.1"));
    paymentApplyLine1.setInvoiceId("1234");

    PaymentApplyLineBDM paymentApplyLine2 = new PaymentApplyLineBDM();
    paymentApplyLine2.setAmount(new BigDecimal("70"));
    paymentApplyLine2.setInvoiceId("5678");

    List<PaymentApplyLineBDM> paymentApplyLines = new ArrayList<>();
    paymentApplyLines.add(paymentApplyLine1);
    paymentApplyLines.add(paymentApplyLine2);
    return paymentApplyLines;
  }
}
