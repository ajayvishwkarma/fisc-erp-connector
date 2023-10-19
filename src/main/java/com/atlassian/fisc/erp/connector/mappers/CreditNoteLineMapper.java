package com.atlassian.fisc.erp.connector.mappers;

import com.atlassian.finance.bdm.CreditNoteLineBDM;
import com.atlassian.finance.bdm.TaxItemBDM;
import com.atlassian.fisc.erp.connector.constant.Constant;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemoItem;
import com.atlassian.fisc.erp.connector.dto.custom.CustomFields;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Mapper(uses = {NetSuiteMapperFn.class})
public interface CreditNoteLineMapper {
  CreditNoteLineMapper INSTANCE = Mappers.getMapper(CreditNoteLineMapper.class);

  default CreditNoteLineBDM convert(CreditMemoItem creditMemoItem) {
    return updateCustomFields(
        map(creditMemoItem),
        CustomFieldsMapper.INSTANCE.convert(creditMemoItem.getCustomFieldList()));
  }

  default CreditMemoItem convert(CreditNoteLineBDM creditNoteLineBDM) {
    return updateCustomFields(map(creditNoteLineBDM), extractNsCustomFields(creditNoteLineBDM));
  }

  @Mapping(target = "description", source = "description")  //CrMemo-ROW 31
  @Mapping(target = "total", source = "amount")  //CrMemo-ROW 32
  // TODO creditMemoLines.orderLineId <-> creditMemoItem.lineId
  @Mapping(target = "itemId", source = "item.internalId")  //TODO revisit CrMemo-ROW ??
  @Mapping(target = "lineId", source = "line")  //CrMemo-ROW 34
  @Mapping(target = "quantity", source = "quantity")  //CrMemo-ROW 35
  @Mapping(
          target = "revenueStartDate",
      source = "revRecStartDate",
      dateFormat = Constant.DATE_TIME_FORMAT)  //CrMemo-ROW 36
  @Mapping(
          target = "revenueEndDate",
      source = "revRecEndDate",
      dateFormat = Constant.DATE_TIME_FORMAT)  //CrMemo-ROW 37
  // @Mapping(source = "creditMemoItem", target = "taxItems", qualifiedByName =
  // "convertCreditMemoItemToTaxItemList")
  CreditNoteLineBDM map(CreditMemoItem creditMemoItem);

  @InheritInverseConfiguration
  @Mapping(
      target = "tax1Amt",
      source = "taxItems",
      qualifiedByName = "convertTaxItemBDMListToTaxAmount")  // CrMemo-ROW 38
  @Mapping(
      target = "taxCode",
      source = "taxItems",
      qualifiedByName = "convertToxItemBDMListToRecordRef")  // CrMemo-ROW 39
  @Mapping(
      target = "taxRate1",
      source = "taxItems",
      qualifiedByName = "convertTaxItemBDMListToTaxRate")  // CrMemo-ROW 40
  @Mapping(
          target = "_class",
          constant = Constant.CFLU_87,
          qualifiedBy = NetSuiteMapping.class)
  CreditMemoItem map(CreditNoteLineBDM creditNoteLineBDM);

  @Mapping(target = "isThisaTaxLine", source = "custcol_is_this_a_tax_line")  //CrMemo-ROW 41
  @Mapping(target = "entitlementId", source = "custcol_sen")  //CrMemo-ROW 42
  @Mapping(target = "orderLineId", source = "custcol_poi_id")  // CrMemo-ROW 34
  @Mapping(target = "lineId", source = "custcol_poi_id")  // CrMemo-ROW 43
  @Mapping(target = "billingType", source = "custcol_billing_type")  //CrMemo-ROW 44
  @Mapping(
          target = "maintenanceStartDate",
      source = "custcolmaintstart",
      dateFormat = Constant.DATE_TIME_FORMAT)  //CrMemo-ROW 46
  @Mapping(
          target = "maintenanceEndDate",
      source = "custcolmaintend",
      dateFormat = Constant.DATE_TIME_FORMAT)  //CrMemo-ROW 47
  @Mapping(target = "newListPrice", source = "custcol_new_list_price")  //CrMemo-ROW 48
  @Mapping(target = "renewListPrice", source = "custcol_renew_list_price")  //CrMemo-ROW 49
  @Mapping(target = "saleAction", source = "custcol_sale_action")  //CrMemo-ROW 50
  @Mapping(target = "productBaseName", source = "custcol_product_base_name")  //CrMemo-ROW 51
  @Mapping(target = "productFamily", source = "custcol_product_family")  //CrMemo-ROW 52
  @Mapping(target = "productFeatureKey", source = "custcol_product_feature_key")  //CrMemo-ROW 53
  @Mapping(target = "pricingPlanId", source = "custcol_pricing_plan_id")  //CrMemo-ROW 54
  @Mapping(target = "invoiceLine", source = "custcol_cm_inv_line")  //CrMemo-ROW 55
  @Mapping(target = "hostingType", source = "custcol_hosting_type")  //CrMemo-ROW 56
  @Mapping(target = "pacPluginEdition", source = "custcol_inv_edition")  //CrMemo-ROW 57
  @Mapping(target = "pluginTotalAmount", source = "custcol_inv_plugin_tot_amount")  //CrMemo-ROW 58
  @Mapping(target = "marketplaceVendorId", source = "custcol_marketplace_vendor.internalId")  //CrMemo-ROW 59
  @Mapping(
          target = "marketplacePreviousHamsOrderId",
      source = "custcol_mpac_previous_hams_order_id")//CrMemo-ROW 60
  @Mapping(target = "productFeatureUsage", source = "custcol_product_feature_usage")  //CrMemo-ROW 61
  @Mapping(target = "discountFlag", source = "custcol_discount_flag")  //CrMemo-ROW 62
  @Mapping(target = "newUpgradeFlag", source = "custcol_new_upgrade_flag")  //CrMemo-ROW 63
  @Mapping(target = "manualAdjustmentAmount", source = "custcol_manual_adj_amt")  //CrMemo-ROW 65
  @Mapping(target = "sourceSystem", source = "custbody_source_system")  //CrMemo-ROW 26
  @Mapping(target = "exchangeRate", source = "custbody_exchange_rate")  //CrMemo-ROW 27
  CreditNoteLineBDM updateCustomFields(
      @MappingTarget CreditNoteLineBDM creditNoteLineBDM, CustomFields customFields);

  //  TODO REFACTOR: apply @InheritInverseConfiguration for custom fields
  @InheritInverseConfiguration
  CustomFields extractNsCustomFields(CreditNoteLineBDM creditNoteLineBDM);

  @Mapping(target = "customFieldList", source = "customFields", qualifiedBy = NetSuiteMapping.class)  //TODO CrMemo-ROW ??
  CreditMemoItem updateCustomFields(
      @MappingTarget CreditMemoItem nsCreditMemoItem, CustomFields customFields);

  @Named("convertTaxItemBDMListToTaxAmount")
  default Double convertTaxItemBDMListToTaxAmount(List<TaxItemBDM> value) {
    return CollectionUtils.isEmpty(value)
        ? null
        : Double.valueOf(String.valueOf(value.get(0).getTaxAmount()));
  }

  @Named("convertTaxItemBDMListToTaxRate")
  default Double convertTaxItemBDMListToTaxRate(List<TaxItemBDM> value) {
    return CollectionUtils.isEmpty(value)
        ? null
        : Double.valueOf(String.valueOf(value.get(0).getTaxRate()));
  }

  @Named("convertToxItemBDMListToRecordRef")
  default RecordRef convertToxItemBDMListToRecordRef(List<TaxItemBDM> value) {
    var taxCode = CollectionUtils.isEmpty(value) ? null : String.valueOf(value.get(0).getTaxCode());
    var recordRef = new RecordRef();
    recordRef.setInternalId(taxCode);
    return recordRef;
  }

  @Named("convertCreditMemoItemToTaxItemList")
  default List<TaxItemBDM> convertCreditMemoItemToTaxItemList(CreditMemoItem value) {
    TaxItemBDM taxItemBDM = new TaxItemBDM();
    taxItemBDM.setTaxAmount(BigDecimal.valueOf(value.getTax1Amt()));
    taxItemBDM.setTaxCode(value.getTaxCode().getInternalId());
    taxItemBDM.setTaxRate(BigDecimal.valueOf(value.getTaxRate1()));
    List<TaxItemBDM> list = new ArrayList<>();
    list.add(taxItemBDM);
    return list;
  }
}
