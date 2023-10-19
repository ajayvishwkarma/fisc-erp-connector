package com.atlassian.fisc.erp.connector.mappers.payment;

import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldRef;
import com.atlassian.fisc.erp.connector.domain.common.RecordType;
import com.atlassian.fisc.erp.connector.domain.payment.CustomerPayment;
import com.atlassian.fisc.erp.connector.domain.payment.CustomerPaymentApply;
import com.atlassian.fisc.erp.connector.domain.payment.CustomerPaymentApplyList;

import static com.atlassian.fisc.erp.connector.util.TestUtil.calDate;
import static com.atlassian.fisc.erp.connector.util.TestUtil.recordRef;

public class NsPaymentTestGen {
  public CustomerPayment generateDummyData() {
    CustomerPayment nsPayment = new CustomerPayment();
    nsPayment.setCurrency(recordRef("1", "USD", RecordType.currency));
    nsPayment.setMemo("memo");
    nsPayment.setPaymentMethod(
        recordRef("PAYCC", "Bofa Remittance AU Method", RecordType.paymentMethod));
    nsPayment.setStatus("PAID");
    nsPayment.setExternalId("setExternalId");
    nsPayment.setTranId("setTranId");
    // TODO check RecordType = RecordType.account
    nsPayment.setArAcct(recordRef("acct-id", "setArAcct", RecordType.account));
    nsPayment.setCustomer(recordRef("customer-id", "setCustomer", RecordType.customer));
    nsPayment.setTranDate(calDate(2023, 0, 20));
    nsPayment.setPayment(101.21d);
    nsPayment.setCustomFieldList(buildCustomFieldList());
    nsPayment.setApplyList(constructDummyNsPaymentApplyLines());

    return nsPayment;
  }

  private CustomerPaymentApplyList constructDummyNsPaymentApplyLines() {
    CustomerPaymentApply nsPaymentApply1 = new CustomerPaymentApply();
    nsPaymentApply1.setAmount(71.21d);
    nsPaymentApply1.setDoc(1234L);
    CustomerPaymentApply nsPaymentApply2 = new CustomerPaymentApply();
    nsPaymentApply2.setAmount(30.21d);
    nsPaymentApply2.setDoc(5678L);
    CustomerPaymentApplyList nsCustomerPaymentApplyList = new CustomerPaymentApplyList();
    nsCustomerPaymentApplyList.setApply(
        new CustomerPaymentApply[] {nsPaymentApply1, nsPaymentApply2});
    return nsCustomerPaymentApplyList;
  }

  private CustomFieldList buildCustomFieldList() {
    CustomFieldRef customFieldRef1 =
        new CustomFieldRef("1", "custbody_source_system", "value:custbody_source_system");
    CustomFieldRef customFieldRef2 =
        new CustomFieldRef(
            "2",
            "custbody_tns_transaction_receipt_id",
            "value:custbody_tns_transaction_receipt_id");

    CustomFieldRef customFieldRef3 =
        new CustomFieldRef("3", "custbody_tns_batch_id", "value:custbody_tns_batch_id");

    CustomFieldRef customFieldRef4 =
        new CustomFieldRef("4", "custbody_subsidiary_currency", "value:custbody_tns_batch_id");
    CustomFieldRef customFieldRef5 =
            new CustomFieldRef("5", "stripe_payment_gateway", "value:Stripe AU");
    CustomFieldRef[] customFieldRefs =
        new CustomFieldRef[] {customFieldRef1, customFieldRef2, customFieldRef3, customFieldRef4, customFieldRef5};
    CustomFieldList customFieldList = new CustomFieldList();
    customFieldList.setCustomField(customFieldRefs);
    return customFieldList;
  }
}
