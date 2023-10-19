package com.atlassian.fisc.erp.connector.mappers;

import com.atlassian.finance.bdm.InvoiceLineBDM;
import com.atlassian.finance.bdm.TaxItemBDM;
import com.atlassian.fisc.erp.connector.constant.Constant;
import com.atlassian.fisc.erp.connector.domain.invoice.InvoiceItem;
import com.atlassian.fisc.erp.connector.dto.custom.CustomFields;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Mapper(uses = {NetSuiteMapperFn.class})
public interface InvoiceLineMapper {
  InvoiceLineMapper INSTANCE = Mappers.getMapper(InvoiceLineMapper.class);

  default InvoiceLineBDM convert(InvoiceItem nsInvoiceItem) {
    return updateCustomFields(
        map(nsInvoiceItem),
        CustomFieldsMapper.INSTANCE.convert(nsInvoiceItem.getCustomFieldList()));
  }

  default InvoiceItem convert(InvoiceLineBDM invoiceLineBDM) {
    return updateCustomFields(map(invoiceLineBDM), extractNsCustomFields(invoiceLineBDM));
  }

  @Mapping(target = "lineNumber", source = "line") // Invc-ROW : 57
  @Mapping(target = "orderLineId", source = "orderLine") // Invc-ROW : 58
  @Mapping(target = "itemId", source = "item", qualifiedBy = NetSuiteMapping.class) // Invc-ROW : 59
  @Mapping(target = "quantity", source = "quantity") // Invc-ROW : 60
  @Mapping(target = "total", source = "amount") // Invc-ROW : 61
  @Mapping(
      target = "periodStartDate",
      source = "revRecStartDate",
      dateFormat = Constant.DATE_TIME_FORMAT) // Invc-ROW : 64
  @Mapping(
      target = "periodEndDate",
      source = "revRecEndDate",
      dateFormat = Constant.DATE_TIME_FORMAT) // Invc-ROW : 65
  //  Impl: taxAmountToTaxItems and taxItemsToTaxAmount
  @Mapping(target = "taxItems", source = "taxAmount") // Invc-ROW : 66
  InvoiceLineBDM map(InvoiceItem invoiceLine);

  @InheritInverseConfiguration
  @Mapping(
      target = "_class",
      constant = Constant.CFLU_87,
      qualifiedBy = NetSuiteMapping.class) // Invc-ROW : 62 (netsuite - field) no BDM field
  @Mapping(
      target = "line",
      source = "lineNumber",
      qualifiedByName = "parseWithDecimal") // Invc-ROW : 57
  @Mapping(
      target = "orderLine",
      source = "orderLineId",
      qualifiedByName = "parseWithDecimal") // Invc-ROW : 58
  InvoiceItem map(InvoiceLineBDM invoiceLine);

  @Mapping(target = "lineId", source = "custcol_poi_id") // Invc-ROW : 56
  @Mapping(target = "isTaxLine", source = "custcol_is_this_a_tax_line") // Invc-ROW : 69
  @Mapping(target = "newUpgradeFlag", source = "custcol_new_upgrade_flag", defaultValue= "false") // Invc-ROW : 70
  @Mapping(target = "previousInvoiceId", source = "custcol_prev_order_id") // Invc-ROW : 71
  @Mapping(
      target = "evergreenRecurringBillingFlag",
      source = "custcol_evergreen_recurring_flag") // Invc-ROW : 73
  @Mapping(target = "orderItemId", source = "custcol_ccp_order_line_id") // Invc-ROW : 74
  @Mapping(target = "entitlementId", source = "custcol_legacy_entitlement_id") // Invc-ROW : 75
  @Mapping(target = "entitlementNumber", source = "custcol_ccp_entitlement_number") // Invc-ROW : 76
  @Mapping(target = "newListPrice", source = "custcol_new_list_price") // Invc-ROW : 78
  @Mapping(target = "renewListPrice", source = "custcol_renew_list_price") // Invc-ROW : 79
  @Mapping(target = "hostingType", source = "custcol_hosting_type") // Invc-ROW : 80
  @Mapping(target = "billingType", source = "custcol_billing_type") // Invc-ROW : 81
  @Mapping(target = "manualDiscountReason", source = "custcol_manual_disc_reason") // Invc-ROW : 82
  @Mapping(target = "manualDiscountReasonCode", source = "custcol_manual_adj_code") // Invc-ROW : 83
  @Mapping(target = "manualDiscountAmount", source = "custcol_manual_adj_amt") // Invc-ROW : 84
  @Mapping(
      target = "listPriceAdjustmentAmount",
      source = "custcol_list_price_adj_amt") // Invc-ROW : 85
  @Mapping(
      target = "listPriceAdjustmentReason",
      source = "custcol_list_price_adj_code") // Invc-ROW : 86
  @Mapping(
      target = "listPriceAdjustmentCode",
      source = "custcol_list_price_adj_desc") // Invc-ROW : 87
  @Mapping(target = "expertDiscountAmount", source = "custcol_disc_amount_func") // Invc-ROW : 88
  @Mapping(
      target = "expertDiscountPercentage",
      source = "custcol_discount_percentage") // Invc-ROW : 89
  @Mapping(
      target = "loyaltyDiscountAdjustmentAmount",
      source = "custcol_lp_disc_price") // Invc-ROW : 90
  @Mapping(
      target = "loyaltyDiscountAdjustmentPercentage",
      source = "custcol_lp_discount_percentage") // Invc-ROW : 91
  @Mapping(
      target = "loyaltyDiscountAdjustmentCode",
      source = "custcol_lp_discount_code") // Invc-ROW : 92
  @Mapping(
      target = "loyaltyDiscountAdjustmentReason",
      source = "custcol_lp_discount_description") // Invc-ROW : 93
  @Mapping(
      target = "vendorDiscountAmount",
      source = "custcol_marketplace_promotion_amt") // Invc-ROW : 94
  @Mapping(target = "saleAction", source = "custcol_sale_action") // Invc-ROW : 95
  @Mapping(target = "upgradeCreditAmount", source = "custcol_upgrade_credit_amt") // Invc-ROW : 96
  @Mapping(
      target = "externalProductBaseName",
      source = "custcol_product_base_name") // Invc-ROW : 98
  @Mapping(target = "externalProductFamily", source = "custcol_product_family") // Invc-ROW : 99
  @Mapping(target = "pricingPlanFeature", source = "custcol_product_feature_key") // Invc-ROW : 100
  @Mapping(target = "pricingPlanId", source = "custcol_pricing_plan_id") // Invc-ROW : 101
  @Mapping(
      target = "commercialGlpDefault",
      source = "custcol_glp_stdlistpriceplan") // Invc-ROW : 102
  @Mapping(
      target = "advantageousPricingFlag",
      source = "custcol_advantage_pricing") // Invc-ROW : 103
  @Mapping(
      target = "grandfatherPricePlan",
      source = "custcol_grandfathered_price_plan") // Invc-ROW : 104
  @Mapping(target = "pluginEdition", source = "custcol_custcol_inv_edition") // Invc-ROW : 107
  @Mapping(target = "pluginTotalAmount", source = "custcol_inv_plugin_tot_amount") // Invc-ROW : 108
  @Mapping(target = "vendorId", source = "custcol_marketplace_vendor.internalId") // Invc-ROW : 109
  @Mapping(target = "exchangeRate", source = "custcol_exchange_rate") // Invc-ROW : 110
  @Mapping(
      target = "marketplaceAppType",
      source = "custcol_ccp_mpac_app_platform") // Invc-ROW : 111
  @Mapping(target = "discountFlag", source = "custcol_discount_flag") // Invc-ROW : 112
  @Mapping(target = "lineSource", source = "custcol_source_sys") // Invc-ROW : 113
  InvoiceLineBDM updateCustomFields(
      @MappingTarget InvoiceLineBDM invoiceLineBDM, CustomFields invoiceLineCustomFields);

  @InheritInverseConfiguration
  @Mapping(
      target = "custcolmaintstart",
      source = "periodStartDate",
      dateFormat = Constant.DATE_TIME_FORMAT) // Invc-ROW : 64 (2)
  @Mapping(
      target = "custcolmaintend",
      source = "periodEndDate",
      dateFormat = Constant.DATE_TIME_FORMAT) // Invc-ROW : 65 (2)
  @Mapping(target = "custcol_product_feature_usage", source = "quantity") // Invc-ROW : 77 //TODO verify mapping
  @Mapping(
      target = "custcol_glp_stdlistpriceplan",
      source = "commercialGlpDefault") // Invc-ROW : 102
  CustomFields extractNsCustomFields(InvoiceLineBDM invoiceLine);

  @Mapping(
      source = "invoiceCustomFields",
      target = "customFieldList",
      qualifiedBy = NetSuiteMapping.class)
  InvoiceItem updateCustomFields(
      @MappingTarget InvoiceItem nsInvoiceItem, CustomFields invoiceCustomFields);

  default List<TaxItemBDM> taxAmountToTaxItems(Double taxAmount) {
    if (Objects.isNull(taxAmount)) return null;
    var tax = new TaxItemBDM();
    tax.setTaxAmount(BigDecimal.valueOf(taxAmount));
    return List.of(tax);
  }

  default BigDecimal taxItemsToTaxAmount(List<TaxItemBDM> value) {
    return CollectionUtils.isEmpty(value) ? BigDecimal.ZERO : value.get(0).getTaxAmount();
  }

  @Named("parseWithDecimal")
  default Long parseWithDecimal(String value) {
    // The value from erp was in format "1.0", and thus had to add this additional logic
    return null != value ? Long.valueOf((long) Double.parseDouble(value)) : null;
  }
}
