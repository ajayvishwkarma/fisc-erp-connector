package com.atlassian.fisc.erp.connector.mappers;

import com.atlassian.finance.bdm.PaymentBDM;
import com.atlassian.finance.enums.ReceiptMethod;
import com.atlassian.finance.enums.ReceiptSource;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.atlassian.fisc.erp.connector.domain.payment.CustomerPayment;
import com.atlassian.fisc.erp.connector.dto.custom.CustomFields;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import static com.atlassian.fisc.erp.connector.constant.Constant.*;

@Mapper(
    uses = {
      PaymentApplyLineMapper.class,
      NetSuiteMapperFn.class,
    })
public interface PaymentMapper {
  PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

  default CustomerPayment convert(PaymentBDM paymentBDM) {
    return updateCustomFields(map(paymentBDM), extractNsCustomFields(paymentBDM));
  }

  default PaymentBDM convert(CustomerPayment nsPayment) {
    return updateCustomFields(
        map(nsPayment), CustomFieldsMapper.INSTANCE.convert(nsPayment.getCustomFieldList()));
  }

  /*
   Automatically mapped fields:
     - memo                                                     Pay-ROW 9
     - status                                                   Pay-ROW 11
  */
  @Mapping(
      target = "id",
      constant = "1234") // TODO To be removed in future - externalId? //Pay-ROW 2
  @Mapping(target = "transactionNumber", source = "tranId") // Pay-ROW 3
  @Mapping(
      target = "accountReceivable",
      source = "arAcct",
      qualifiedBy =
          NetSuiteMapping.class) // TODO To be removed in future - receivableAccount ? //Pay-ROW 4
  @Mapping(
      target = "currency",
      source = "currency",
      qualifiedBy = NetSuiteMapping.class) // needs lookup //Pay-ROW 8
  @Mapping(
      target = "paymentMethod",
      source = "paymentMethod",
      qualifiedBy = NetSuiteMapping.class) // Pay-ROW 10
  @Mapping(target = "erpAccount", constant = ATLASSIAN_AU_BU_USD_BUSINESS_UNIT_NAME) // Pay-ROW 5
  @Mapping(
      target = "customerId",
      source = "customer",
      qualifiedBy = NetSuiteMapping.class) // Pay-ROW 6
  @Mapping(
      target = "transactionCreateDate",
      source = "tranDate",
      dateFormat = DATE_TIME_FORMAT) // Pay-ROW 7
  @Mapping(target = "totalAmount", source = "payment") // Pay-ROW 12
  @Mapping(target = "paymentApplyLines", source = "applyList.apply")
  @Mapping(
          target = "cashAccount",
          source = "account",
          qualifiedBy = NetSuiteMapping.class) // Pay-ROW 6
  @Mapping(target = "sourceSystem", constant = SOURCE_SYSTEM_HAMS) // Pay-ROW 1
  PaymentBDM map(CustomerPayment nsPayment);

  @InheritInverseConfiguration
  @Mapping(target = "externalId", source = "id") //  TODO revisit //Pay-ROW 2
  // TODO @Mapping(target = "erpAccount??", source = "payment.erpAccount") erpAccount not in NS// //Pay-ROW 5
  @Mapping(target = "internalId", source= "paymentErpId")
  @Mapping(target = "paymentMethod", source = "paymentReceiptMethod", qualifiedByName = "getPaymentMethod")
  CustomerPayment map(PaymentBDM payment);

  @Mapping(
      target = "transactionReceiptId",
      source = "custbody_tns_transaction_receipt_id") // Pay-ROW 13
  @Mapping(target = "batchId", source = "custbody_tns_batch_id") // Pay-ROW 16
  //  TODO @Mapping(target = "??", source = "customFields.custbody_subsidiary_currency") //Pay-ROW
  // 17
  PaymentBDM updateCustomFields(@MappingTarget PaymentBDM paymentBDM, CustomFields customFields);

  @AfterMapping
  default void afterUpdateCustomFields(
      @MappingTarget PaymentBDM paymentBDM, CustomFields customFields) {
    paymentBDM.setPaymentReceiptMethod(
        getPaymentReceipt(paymentBDM, customFields)); // Pay-ROW 18 TODO update BDM mapping table
  }

  @InheritInverseConfiguration
  CustomFields extractNsCustomFields(PaymentBDM payment);

  @Mapping(target = "customFieldList", source = "customFields", qualifiedBy = NetSuiteMapping.class)
  CustomerPayment updateCustomFields(
      @MappingTarget CustomerPayment nsPayment, CustomFields customFields);

  default ReceiptMethod getPaymentReceipt(PaymentBDM payment, CustomFields custFields) {
    return MapperUtil.getReceiptMethod(
        payment.getCurrency(), payment.getPaymentMethod(), custFields.stripe_payment_gateway);
  }

  @Named("getReceiptSourceEnum")
  default ReceiptSource getReceiptSourceEnum(String receiptSource) {
    return ReceiptSource.valueOfLabel(receiptSource);
  }

  @Named("getReceiptSourceEnum")
  default String getReceiptSourceString(ReceiptSource receiptSource) {
    return receiptSource != null ? receiptSource.getName() : ReceiptSource.HAMS.getName();
  }

  @Named("getPaymentMethod")
  default RecordRef getPaymentMethod(ReceiptMethod paymentReceiptMethod) {
    if (paymentReceiptMethod == null) return null;
    var recordRef = new RecordRef();
    if(PAID_CC_RECEIPT_METHODS.contains(paymentReceiptMethod.getName())){
      recordRef.setInternalId(PAID_CC);
    }else if(PAID_PAYPAL_RECEIPT_METHODS.contains(paymentReceiptMethod.getName())){
      recordRef.setInternalId(PAID_PAYPAL);
    }else{
      recordRef.setInternalId(PAID_ON_TERMS);
    }
    return recordRef;
  }
}
