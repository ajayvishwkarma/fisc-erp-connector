package com.atlassian.fisc.erp.connector.mappers;

import com.atlassian.finance.bdm.RefundBDM;
import com.atlassian.finance.enums.ReceiptMethod;
import com.atlassian.fisc.erp.connector.constant.Constant;
import com.atlassian.fisc.erp.connector.domain.refund.CustomerRefund;
import com.atlassian.fisc.erp.connector.domain.refund.CustomerRefundApply;
import com.atlassian.fisc.erp.connector.domain.refund.CustomerRefundApplyList;
import com.atlassian.fisc.erp.connector.dto.custom.CustomFields;
import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

@Mapper(uses = {RefundLineMapper.class, NetSuiteMapperFn.class})
public interface RefundMapper {
  RefundMapper INSTANCE = Mappers.getMapper(RefundMapper.class);

  default CustomerRefund convert(RefundBDM refund) {
    return updateCustomFields(map(refund), extractNsCustomFields(refund));
  }

  default RefundBDM convert(CustomerRefund nsRefund) {
    return updateCustomFields(
        map(nsRefund), CustomFieldsMapper.INSTANCE.convert(nsRefund.getCustomFieldList()));
  }

  @Mapping(target = "sourceSystem", constant = Constant.SOURCE_SYSTEM_HAMS) // TODO Refund-ROW 1
  //  @Mapping(target = "customerType", source = "??") TODO Refund-ROW 2
  @Mapping(target = "transactionNumber", source = "tranId") //  Refund-ROW 3
  @Mapping(
      target = "customerId",
      source = "customer",
      qualifiedBy = NetSuiteMapping.class) //  TODO:Revisit Refund-ROW 4
  //  @Mapping(target = "erpAccountId", source = "??") TODO Refund-ROW 5
  //  @Mapping(target = "accountId", source = "??") TODO Refund-ROW 6
  @Mapping(target = "currency", source = "currencyName") //  Refund-ROW 7
  @Mapping(target = "id", source = "externalId") // TODO Refund-ROW 8
  @Mapping(
      target = "transactionDate",
      source = "createdDate",
      dateFormat = Constant.DATE_TIME_FORMAT) //  Refund-ROW 9
  @Mapping(target = "memo", source = "memo") //  Refund-ROW 10
  @Mapping(
      target = "paymentMethod",
      source = "paymentMethod",
      qualifiedBy = NetSuiteMapping.class) // TODO Review Refund-ROW 11
  @Mapping(target = "amount", source = "applyList", qualifiedByName = "amount") // Refund-ROW 13
  @Mapping(target = "refundLines", source = "applyList.apply")
  RefundBDM map(CustomerRefund nsRefund);

  @InheritInverseConfiguration
  @Mapping(target = "currency", ignore = true) // Refund-ROW 7 mapped to currencyName
  CustomerRefund map(RefundBDM refund);

  @Named("amount")
  default BigDecimal amount(CustomerRefundApplyList applyList) {
    if (null == applyList || ObjectUtils.isEmpty(applyList.getApply())) return null;
    double sum =
        Arrays.stream(applyList.getApply())
            .map(CustomerRefundApply::getTotal)
            .filter(Objects::nonNull)
            .mapToDouble(Double.class::cast)
            .sum();
    return BigDecimal.valueOf(sum);
  }

  @Mapping(
      target = "transactionReceiptId",
      source = "custbody_tns_transaction_receipt_id") //  Refund-ROW 12
  RefundBDM updateCustomFields(@MappingTarget RefundBDM refundBDM, CustomFields customFields);

  @AfterMapping
  default void afterUpdateCustomFields(
      @MappingTarget RefundBDM refundBDM, CustomFields customFields) {
    refundBDM.setRefundReceiptMethod(getRefundReceipt(refundBDM, customFields)); // Refund-ROW ??
  }

  @InheritInverseConfiguration
  CustomFields extractNsCustomFields(RefundBDM refund);

  @Mapping(target = "customFieldList", source = "customFields", qualifiedBy = NetSuiteMapping.class)
  CustomerRefund updateCustomFields(
      @MappingTarget CustomerRefund nsRefund, CustomFields customFields);

  default ReceiptMethod getRefundReceipt(RefundBDM refundBDM, CustomFields custFields) {
    return MapperUtil.getReceiptMethod(
        refundBDM.getCurrency(), refundBDM.getPaymentMethod(), custFields.stripe_payment_gateway);
  }
}
