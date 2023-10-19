package com.atlassian.fisc.erp.connector.mappers;

import com.atlassian.finance.bdm.CreditNoteApplyLineBDM;
import com.atlassian.finance.bdm.CreditNoteBDM;
import com.atlassian.finance.bdm.CreditNoteLineBDM;
import com.atlassian.fisc.erp.connector.constant.Constant;
import com.atlassian.fisc.erp.connector.domain.creditmemo.*;
import com.atlassian.fisc.erp.connector.dto.custom.CustomFields;
import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.atlassian.fisc.erp.connector.constant.Constant.*;
import static org.springframework.util.CollectionUtils.isEmpty;

@Mapper(uses = {CreditNoteLineMapper.class, NetSuiteMapperFn.class, AddressMapper.class, EnumMapperFn.class})
public interface CreditNoteMapper {
  CreditNoteMapper INSTANCE = Mappers.getMapper(CreditNoteMapper.class);

  default CreditNoteBDM convert(CreditMemo creditMemo) {
    return updateCustomFields(
            map(creditMemo), CustomFieldsMapper.INSTANCE.convert(creditMemo.getCustomFieldList()));
  }

  default CreditMemo convert(CreditNoteBDM creditNoteBDM) {
    return updateCustomFields(map(creditNoteBDM), extractNsCustomFields(creditNoteBDM));
  }

  @Mapping(target = "sourceSystem", constant = TRANSACTION_SOURCE_SYSTEM_HAMS ) //CrMemo-ROW 01
  @Mapping(target = "applyCreditMemoToInvoice",source = "applyList.apply") // applyCreditMemoToInvoice
  @Mapping(target = "creditNoteApplyLines", source = "applyList.apply")  // creditNoteApplyLines
  @Mapping(target = "transactionNumber", source = "tranId") //CrMemo-ROW 04
  @Mapping(target = "transactionDate",source = "tranDate", dateFormat = DATE_TIME_FORMAT) //CrMemo-ROW 05
  @Mapping(target = "formType", constant = CREDITMEMO_FORM_TYPE ) //CrMemo-ROW ??
  @Mapping(target = "invoiceId", source = "createdFrom", qualifiedBy = NetSuiteMapping.class) //CrMemo-ROW 09
  @Mapping(target = "currency", source = "currency", qualifiedBy = NetSuiteMapping.class) //CrMemo-ROW 10
  @Mapping(target = "purchaseOrderNumber", source = "otherRefNum" ) //CrMemo-ROW 11
  @Mapping(target = "exchangeRate", source = "exchangeRate" ) //CrMemo-ROW 12
  @Mapping(target = "creditMemoLines", source = "itemList.item" )
  @Mapping(target = "memo", source = "memo" ) //CrMemo-ROW 28
  @Mapping(target = "customerId", source = "entity" , qualifiedBy = NetSuiteMapping.class) //CrMemo-ROW 66
  @Mapping(target = "billTo", source = "billingAddress") // CrMemo-ROW 67 - 75
  @Mapping(target = "subsidiaryId", constant = Constant.ATLASSIAN_AU_BU_USD_BUSINESS_UNIT_NAME)
  CreditNoteBDM map(CreditMemo nsCreditMemo);


  @InheritInverseConfiguration
  @Mapping(target = "source", source = "sourceSystem") //CrMemo-ROW 01
  @Mapping(target = "applyList", source = "creditNoteApplyLines") //applyList
  @Mapping(target = "itemList", source = "creditMemoLines")
  @Mapping(target = "internalId", source = "creditnoteErpId")
  CreditMemo map(CreditNoteBDM creditNoteBdm);

  @Mapping(target = "invoiceNumber", source = "refNum") //  TODO Revisit CrMemo-ROW ??
  @Mapping(target = "invoiceErpId", source = "doc") //  TODO Revisit CrMemo-ROW ??
  @Mapping(target = "applyAmount", source = "amount") //  TODO Revisit CrMemo-ROW ??
  @Mapping(target = "applyDate", source = "applyDate", dateFormat = DATE_TIME_FORMAT) //  TODO Revisit CrMemo-ROW ??
  CreditNoteApplyLineBDM map(CreditMemoApply creditMemoApply);

  @InheritInverseConfiguration
  CreditMemoApply map(CreditNoteApplyLineBDM creditNoteApplyLineBDM);

  @Mapping(target = "billingContactId", source = "custbodybillingcontact.internalId") //CrMemo-ROW 13
  @Mapping(target = "technicalContactId", source = "custbodytechnicalcontact.internalId") //CrMemo-ROW 14
  @Mapping(target = "customerType", source = "custbody_atl_cust_type") //CrMemo-ROW 16
  @Mapping(target = "refundCode", source = "custbody_refund_code" ) //CrMemo-ROW 17
  @Mapping(target = "refundReasonDescription", source = "custbody_refund_reason_desc") //CrMemo-ROW 18
  @Mapping(target = "slaCreditAmount", source = "custbody_sla_credit_amt") //CrMemo-ROW 20
  @Mapping(target = "creditNoteCreationReasonId", source = "custbody_cr_reason_tr.internalId") //CrMemo-ROW 22
  @Mapping(target = "creditMemoSource", source = "custbodyatl_credit_memo_source.internalId") //CrMemo-ROW 23
  @Mapping(target = "paymentMethod", source = "custbody_payment_method") //CrMemo-ROW 24
  @Mapping(target = "taxPrintAddress", source = "custbody_atl_tax_print_address") //CrMemo-ROW 25
  @Mapping(target = "issueRefund", source = "cust_issueRefund") //  TODO Revisit CrMemo-ROW ??
  @Mapping(target = "endCustomerId", source = "custbody_end_customer")
  CreditNoteBDM updateCustomFields(@MappingTarget CreditNoteBDM creditNoteBDM, CustomFields customFields);

  @InheritInverseConfiguration
  CustomFields extractNsCustomFields(CreditNoteBDM creditNoteBDM);

  @Mapping(target = "customFieldList", source = "customFields", qualifiedBy = NetSuiteMapping.class)
  CreditMemo updateCustomFields(@MappingTarget CreditMemo nsCreditMemo, CustomFields customFields);

  default boolean applyCreditMemoToInvoice(CreditMemoApply[] apply) {
    return null != apply && apply.length > 0;
  }

  default CreditMemoApplyList applyList(List<CreditNoteApplyLineBDM> cnApplyLineList) {
    if (isEmpty(cnApplyLineList)) return null;
    var apply = cnApplyLineList.stream().map(this::map).toArray(size -> new CreditMemoApply[size]);
    return new CreditMemoApplyList(apply, false);
  }

  default List<CreditNoteApplyLineBDM> creditNoteApplyLines(CreditMemoApply[] apply) {
    if (ObjectUtils.isEmpty(apply)) return null;
    return Arrays.stream(apply).map(this::map).collect(Collectors.toList());
  }

  default List<CreditNoteLineBDM> mapItemList(CreditMemoItem[] itemList) {
    if (ObjectUtils.isEmpty(itemList)) return null;
    // handle item list
    int lineNumber = 0;
    List<CreditNoteLineBDM> creditMemoLines = new ArrayList<>();
    for (CreditMemoItem creditMemoItem : itemList) {
      CreditNoteLineBDM creditNoteLineBDM = CreditNoteLineMapper.INSTANCE.convert(creditMemoItem);
      String lineId = String.valueOf(lineNumber++);
      creditNoteLineBDM.setLineId(lineId);
      creditNoteLineBDM.setOrderLineId(lineId);
      creditMemoLines.add(creditNoteLineBDM);
    }
    return creditMemoLines;
  }

  default CreditMemoItemList map(List<CreditNoteLineBDM> creditNoteLines) {
    if (isEmpty(creditNoteLines)) return null;

    //  handle credit memo items
    List<CreditMemoItem> creditMemoItems = new ArrayList<>();
    for (CreditNoteLineBDM creditNoteLineBDM : creditNoteLines) {
      CreditMemoItem creditMemoItem = CreditNoteLineMapper.INSTANCE.convert(creditNoteLineBDM);
      creditMemoItems.add(creditMemoItem);
    }
    CreditMemoItemList creditMemoItemList = new CreditMemoItemList();
    creditMemoItemList.setItem(creditMemoItems.toArray(new CreditMemoItem[creditMemoItems.size()]));
    return creditMemoItemList;
  }
}