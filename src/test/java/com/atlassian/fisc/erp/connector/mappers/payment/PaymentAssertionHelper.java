package com.atlassian.fisc.erp.connector.mappers.payment;

import com.atlassian.finance.bdm.PaymentApplyLineBDM;
import com.atlassian.finance.bdm.PaymentBDM;
import com.atlassian.finance.enums.ReceiptMethod;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorConfig;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldRef;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.atlassian.fisc.erp.connector.domain.payment.CustomerPayment;
import com.atlassian.fisc.erp.connector.domain.payment.CustomerPaymentApply;
import com.atlassian.fisc.erp.connector.util.TwoWayAsserter;

import static com.atlassian.fisc.erp.connector.constant.Constant.*;
import static com.atlassian.fisc.erp.connector.util.TestUtil.epoch;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaymentAssertionHelper {

  private final FiscErpConnectorConfig configProperties;

  public PaymentAssertionHelper(FiscErpConnectorConfig configProperties) {
    this.configProperties = configProperties;
  }

  public void assertNsPayment(PaymentBDM payment, CustomerPayment nsPayment,boolean expectedFirst) {
    assertNotNull(payment);
    assertNotNull(nsPayment);
    TwoWayAsserter twoWayAsserter = new TwoWayAsserter(expectedFirst);
    twoWayAsserter.assertEquals(payment.getMemo(), nsPayment.getMemo());
    twoWayAsserter.assertEquals( configProperties.getCurrency().get(payment.getCurrency()),
            nsPayment.getCurrency().getInternalId());
    twoWayAsserter.assertEquals(payment.getStatus(), nsPayment.getStatus());
    twoWayAsserter.assertEquals(payment.getId(), nsPayment.getExternalId());
    twoWayAsserter.assertEquals(payment.getTransactionNumber(), nsPayment.getTranId());
    twoWayAsserter.assertEquals(payment.getCustomerId(), nsPayment.getCustomer().getInternalId());
    twoWayAsserter.assertEquals(epoch(payment.getTransactionCreateDate()), epoch(nsPayment.getTranDate()));
    twoWayAsserter.assertEquals(payment.getTotalAmount().doubleValue(), nsPayment.getPayment());

    validatePaymentMethod(payment.getPaymentReceiptMethod(), nsPayment.getPaymentMethod(),true);

    // check apply list
    assertEquals(payment.getPaymentApplyLines().size(), nsPayment.getApplyList().getApply().length);
    for (int i = 0; i < payment.getPaymentApplyLines().size(); i++) {
      CustomerPaymentApply nsPaymentApply = nsPayment.getApplyList().getApply()[i];
      PaymentApplyLineBDM paymentApplyLine = payment.getPaymentApplyLines().get(i);
      twoWayAsserter.assertEquals(paymentApplyLine.getAmount().doubleValue(), nsPaymentApply.getAmount());
      twoWayAsserter.assertEquals(paymentApplyLine.getInvoiceId(), nsPaymentApply.getDoc().toString());
    }

    validateCustomFields(nsPayment.getCustomFieldList(), payment, false);

    //  TODO validate lookups
  }

  public void assertPayment(CustomerPayment nsPayment, PaymentBDM payment,boolean expectedFirst) {
    assertNotNull(payment);
    assertNotNull(nsPayment);
    TwoWayAsserter twoWayAsserter = new TwoWayAsserter(expectedFirst);
    twoWayAsserter.assertEquals(nsPayment.getMemo(), payment.getMemo());
    twoWayAsserter.assertEquals(nsPayment.getPaymentMethod().getExternalId(), payment.getPaymentMethod());
    twoWayAsserter.assertEquals(configProperties.getCurrency().get(nsPayment.getCurrency().getInternalId()),
            payment.getCurrency());
    twoWayAsserter.assertEquals(nsPayment.getStatus(), payment.getStatus());
    twoWayAsserter.assertEquals(nsPayment.getTranId(), payment.getTransactionNumber());
    twoWayAsserter.assertEquals(nsPayment.getArAcct().getExternalId(), payment.getAccountReceivable());
    twoWayAsserter.assertEquals(nsPayment.getCustomer().getExternalId(), payment.getCustomerId());
    twoWayAsserter.assertEquals(epoch(nsPayment.getTranDate()), epoch(payment.getTransactionCreateDate()));
    twoWayAsserter.assertEquals(nsPayment.getPayment(), payment.getTotalAmount().doubleValue());
    twoWayAsserter.assertEquals(nsPayment.getApplyList().getApply().length, payment.getPaymentApplyLines().size());


    // check apply list
    assertEquals(nsPayment.getApplyList().getApply().length, payment.getPaymentApplyLines().size());
    CustomerPaymentApply nsPaymentApply = nsPayment.getApplyList().getApply()[0];
    PaymentApplyLineBDM paymentApplyLine = payment.getPaymentApplyLines().get(0);
    twoWayAsserter.assertEquals(nsPaymentApply.getAmount(), paymentApplyLine.getAmount().doubleValue());
    twoWayAsserter.assertEquals(nsPaymentApply.getDoc().toString(), paymentApplyLine.getInvoiceId());

    // check custom fields
    validateCustomFields(nsPayment.getCustomFieldList(), payment, true);

    //  TODO validate lookups
  }

  private void validateCustomFields(
      CustomFieldList customFieldList, PaymentBDM payment, boolean expectedFirst) {
    TwoWayAsserter ast = new TwoWayAsserter(expectedFirst);
    for (CustomFieldRef customFieldRef : customFieldList.getCustomField()) {
      String scriptId = customFieldRef.getScriptId();
      switch (scriptId) {
        case "custbody_tns_transaction_receipt_id":
          ast.assertEquals(customFieldRef.getValue(), payment.getTransactionReceiptId());
          break;
        case "custbody_tns_batch_id":
          ast.assertEquals(customFieldRef.getValue(), payment.getBatchId());
          break;
        case "custbody_subsidiary_currency":
          //  TODO BDM field mapping defined = ast.assertEquals(customFieldRef.getValue,
          // payment.??);
          break;
        default:
          break;
      }
    }
  }

  private void validatePaymentMethod(ReceiptMethod paymentReceiptMethod, RecordRef paymentMethod,boolean expectedFirst) {
    TwoWayAsserter twoWayAsserter = new TwoWayAsserter(expectedFirst);
    if(paymentReceiptMethod !=null){
    if(PAID_CC_RECEIPT_METHODS.contains(paymentReceiptMethod.getName())){
      twoWayAsserter.assertEquals(PAID_CC,paymentMethod.getInternalId());
    }else if(PAID_PAYPAL_RECEIPT_METHODS.contains(paymentReceiptMethod.getName())){
      twoWayAsserter.assertEquals(PAID_PAYPAL,paymentMethod.getInternalId());
    }}
  }
}
