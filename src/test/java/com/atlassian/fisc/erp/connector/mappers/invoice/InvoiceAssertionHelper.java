package com.atlassian.fisc.erp.connector.mappers.invoice;

import com.atlassian.finance.bdm.InvoiceBDM;
import com.atlassian.finance.bdm.InvoiceLineBDM;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorConfig;
import com.atlassian.fisc.erp.connector.constant.Constant;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldRef;
import com.atlassian.fisc.erp.connector.domain.common.ListOrRecordRef;
import com.atlassian.fisc.erp.connector.domain.invoice.Invoice;
import com.atlassian.fisc.erp.connector.domain.invoice.InvoiceItem;
import com.atlassian.fisc.erp.connector.mappers.common.AssertionHelper;
import com.atlassian.fisc.erp.connector.util.TwoWayAsserter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.atlassian.fisc.erp.connector.util.TestUtil.epoch;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvoiceAssertionHelper {
  private final FiscErpConnectorConfig configProperties;
  private final AssertionHelper assertionHelper;

  public InvoiceAssertionHelper(FiscErpConnectorConfig configProperties) {
    this.configProperties = configProperties;
    this.assertionHelper = new AssertionHelper(configProperties);
  }

  public void assertNsInvoice(InvoiceBDM invoiceBdm, Invoice nsInvoice) {
    assertNotNull(nsInvoice);
    commonAsserter(invoiceBdm, nsInvoice, true);
  }

  public void assertInvoice(Invoice nsInvoice, InvoiceBDM invoiceBdm) {
    assertNotNull(invoiceBdm);
    commonAsserter(invoiceBdm, nsInvoice, false);
  }

  private void commonAsserter(InvoiceBDM invoiceBdm, Invoice nsInvoice, boolean expectedFirst) {
    TwoWayAsserter ast = new TwoWayAsserter(expectedFirst);
    ast.assertEquals(
        configProperties.getCurrency().get(invoiceBdm.getCurrency()),
        nsInvoice.getCurrency().getInternalId());
    ast.assertEquals(invoiceBdm.getId(), nsInvoice.getExternalId());
    ast.assertEquals(invoiceBdm.getInvoiceErpId(), nsInvoice.getInternalId());
    ast.assertEquals(invoiceBdm.getTransactionNumber(), nsInvoice.getTranId());
    ast.assertEquals(epoch(invoiceBdm.getTransactionDate()), epoch(nsInvoice.getTranDate()));
    ast.assertEquals(epoch(invoiceBdm.getDueDate()), epoch(nsInvoice.getDueDate()));
    ast.assertEquals(invoiceBdm.getFormType().name(), Constant.INVOICE_FORM_TYPE);
    ast.assertEquals(invoiceBdm.getCustomerId(), nsInvoice.getEntity().getInternalId());
    ast.assertEquals(invoiceBdm.getAccountId(), nsInvoice.getAccount().getInternalId());

    if (ast.isForward()) {
      if (Constant.STATUS_PAID.equals(invoiceBdm.getStatus())) {
        ast.assertEquals(Constant.INVOICE_STATUS_PAID_IN_FULL, nsInvoice.getStatus());
      } else {
        ast.assertEquals(Constant.STATUS_OPEN, nsInvoice.getStatus());
      }
    }

    ast.assertEquals(invoiceBdm.getTerms().getName(), nsInvoice.getTerms().getInternalId());
    assertionHelper.validateAddress(invoiceBdm.getBillTo(), nsInvoice.getBillingAddress(), ast);
    assertionHelper.validateAddress(invoiceBdm.getShipTo(), nsInvoice.getShippingAddress(), ast);
    ast.assertEquals(invoiceBdm.getBalanceAmount(), BigDecimal.valueOf(nsInvoice.getBalance()));
    ast.assertEquals(invoiceBdm.getInvoiceLines().size(), nsInvoice.getItemList().getItem().length);

    List<InvoiceItem> invoiceItems = Arrays.asList(nsInvoice.getItemList().getItem());

    for (int i = 0; i < invoiceItems.size(); i++) {
      InvoiceItem invoiceItem = invoiceItems.get(i);
      InvoiceLineBDM invoiceLine = invoiceBdm.getInvoiceLines().get(i);
      // TODO assertEquals(invoiceLine.getItemId(), invoiceItem.getItem().getInternalId());
      ast.assertEquals(invoiceLine.getLineNumber(), invoiceItem.getLine().toString());
      ast.assertEquals(invoiceLine.getQuantity(), invoiceItem.getQuantity().intValue());
      ast.assertEquals(invoiceLine.getTotal(), BigDecimal.valueOf(invoiceItem.getAmount()));
      // TODO need req - assertEquals(invoiceLine.getProductId(),
      // invoiceItem.get_class().getInternalId());
      ast.assertEquals(invoiceLine.getDescription(), invoiceItem.getDescription());
      ast.assertEquals(
          epoch(invoiceLine.getPeriodStartDate()), epoch(invoiceItem.getRevRecStartDate()));
      ast.assertEquals(
          epoch(invoiceLine.getPeriodEndDate()), epoch(invoiceItem.getRevRecEndDate()));
      validateCustomFields(invoiceLine, invoiceItem.getCustomFieldList(), ast);
    }
    validateCustomFields(invoiceBdm, nsInvoice.getCustomFieldList(), ast);
  }

  private void validateCustomFields(
          InvoiceBDM invoiceBDM, CustomFieldList customFieldList, TwoWayAsserter ast) {
    for (CustomFieldRef customFieldRef : customFieldList.getCustomField()) {
      String scriptId = customFieldRef.getScriptId();
      switch (scriptId) {
        case "custbody_source_system":
          ast.assertEquals(customFieldRef.getValue(), invoiceBDM.getSourceSystem().getName());
          break;
        case "custbody_end_customer":
          ast.assertEquals(customFieldRef.getValue(), invoiceBDM.getEndCustomerId());
          break;
        case "custbody_creditcardcountry":
          ast.assertEquals(customFieldRef.getValue(), invoiceBDM.getCustomerCreditCardCountry());
          break;
        case "custbody_atl_common_billregion":
          ast.assertEquals(customFieldRef.getValue(), invoiceBDM.getBillingRegion());
          break;
        case "custbody_atl_common_shipregion":
          ast.assertEquals(customFieldRef.getValue(), invoiceBDM.getShippingRegion());
          break;
        case "custbodybillingcontact":
          ast.assertEquals(((ListOrRecordRef) customFieldRef.getValue()).getInternalId(), invoiceBDM.getBillingContactId());
          break;
        case "custbodytechnicalcontact":
          ast.assertEquals(((ListOrRecordRef) customFieldRef.getValue()).getInternalId(), invoiceBDM.getTechnicalContactId());
          break;
        case "custbody_contract_modification":
          ast.assertEquals(
                  customFieldRef.getValue(), invoiceBDM.getCmodFlag());
          break;
        case "custbodycustbody_exchange_rate":
          ast.assertEquals(
                  new BigDecimal((String) customFieldRef.getValue()), invoiceBDM.getExchangeRate());
          break;
        case "custbodyrebill_invoice":
          ast.assertEquals(
                  Boolean.valueOf((String) customFieldRef.getValue()), invoiceBDM.getReInvoicedFlag());
          break;
        case "custbody_orgnl_dunn_end_date":
          ast.assertEquals(customFieldRef.getValue(), invoiceBDM.getOriginalDunningDate());
          break;
        case "custbody_atl_cust_type":  //Invc-TST-ROW : 20
          ast.assertEquals(customFieldRef.getValue(), invoiceBDM.getCustomerType());
          break;
        default:
          break;
      }
    }
  }

  private void validateCustomFields(
          InvoiceLineBDM invoiceLine, CustomFieldList customFieldList, TwoWayAsserter ast) {
    for (CustomFieldRef customFieldRef : customFieldList.getCustomField()) {
      String scriptId = customFieldRef.getScriptId();
      switch (scriptId) {
        case "custcol_poi_id":
          ast.assertEquals(customFieldRef.getValue(), invoiceLine.getLineId());
          break;
        case "custcolmaintstart":
          ast.assertEquals(customFieldRef.getValue(), invoiceLine.getPeriodStartDate());
          break;
        case "custcolmaintend":
          ast.assertEquals(customFieldRef.getValue(), invoiceLine.getPeriodEndDate());
          break;
        case "custcol_new_upgrade_flag":
          ast.assertEquals(
                  customFieldRef.getValue(), invoiceLine.getNewUpgradeFlag());
          break;
        case "custcol_prev_order_id":
          ast.assertEquals(customFieldRef.getValue(), invoiceLine.getPreviousInvoiceId());
          break;
        case "custcol_evergreen_recurring_flag":
          ast.assertEquals(
                  Boolean.valueOf((String) customFieldRef.getValue()),
                  invoiceLine.getEvergreenRecurringBillingFlag());
          break;
        case "custbody_contract_modification":
          ast.assertEquals(customFieldRef.getValue(), invoiceLine.getOrderItemId());
          break;
        case "custcol_ccp_entitlement_number":
          ast.assertEquals(customFieldRef.getValue(), invoiceLine.getEntitlementNumber());
          break;
        case "custcol_product_feature_usage":
          ast.assertEquals(
                  Integer.parseInt((String) customFieldRef.getValue()), invoiceLine.getQuantity());
          break;
        case "custcol_new_list_price":
          ast.assertEquals(
                  customFieldRef.getValue(), invoiceLine.getNewListPrice().doubleValue());
          break;
        case "custcol_renew_list_price":
          ast.assertEquals(
                  customFieldRef.getValue(), invoiceLine.getRenewListPrice().doubleValue());
          break;
        case "custcol_hosting_type":
          ast.assertEquals(customFieldRef.getValue(), invoiceLine.getHostingType());
          break;
        case "custcol_manual_disc_reason":
          ast.assertEquals(customFieldRef.getValue(), invoiceLine.getManualDiscountReason());
          break;
        case "custcol_manual_adj_code":
          ast.assertEquals(customFieldRef.getValue(), invoiceLine.getManualDiscountReasonCode());
          break;
        case "custcol_manual_adj_amt":
          ast.assertEquals(
                  customFieldRef.getValue(),
                  invoiceLine.getManualDiscountAmount().doubleValue());
          break;
        case "custcol_list_price_adj_amt":
          ast.assertEquals(
                  Double.valueOf(String.valueOf(customFieldRef.getValue())),
                  invoiceLine.getListPriceAdjustmentAmount().doubleValue());
          break;
        case "custcol_list_price_adj_code":
          ast.assertEquals(customFieldRef.getValue(), invoiceLine.getListPriceAdjustmentReason());
          break;
        case "custcol_list_price_adj_desc":
          ast.assertEquals(customFieldRef.getValue(), invoiceLine.getListPriceAdjustmentCode());
          break;
        case "custcol_disc_amount_func":
          ast.assertEquals(
                  Double.valueOf(String.valueOf(customFieldRef.getValue())),
                  invoiceLine.getExpertDiscountAmount().doubleValue());
          break;
        case "custcol_discount_percentage":
          ast.assertEquals(
                  Double.valueOf(String.valueOf(customFieldRef.getValue())),
                  invoiceLine.getExpertDiscountPercentage().doubleValue());
          break;
        case "custcol_lp_disc_price":
          ast.assertEquals(
                  Double.valueOf(String.valueOf(customFieldRef.getValue())),
                  invoiceLine.getLoyaltyDiscountAdjustmentAmount().doubleValue());
          break;
        case "custcol_lp_discount_percentage":
          ast.assertEquals(
                  Double.valueOf(String.valueOf(customFieldRef.getValue())),
                  invoiceLine.getLoyaltyDiscountAdjustmentPercentage().doubleValue());
          break;
        case "custcol_lp_discount_code":
          ast.assertEquals(
                  customFieldRef.getValue(), invoiceLine.getLoyaltyDiscountAdjustmentCode());
          break;
        case "custcol_lp_discount_description":
          ast.assertEquals(
                  customFieldRef.getValue(), invoiceLine.getLoyaltyDiscountAdjustmentReason());
          break;
        case "custcol_marketplace_promotion_amt":
          ast.assertEquals(
                  Double.valueOf(String.valueOf(customFieldRef.getValue())),
                  invoiceLine.getVendorDiscountAmount().doubleValue());
          break;
        case "custcol_upgrade_credit_amt":
          ast.assertEquals(
                  Double.valueOf(String.valueOf(customFieldRef.getValue())),
                  invoiceLine.getUpgradeCreditAmount().doubleValue());
          break;
        case "custcol_product_base_name":
          ast.assertEquals(customFieldRef.getValue(), invoiceLine.getExternalProductBaseName());
          break;
        case "custcol_product_family":
          ast.assertEquals(customFieldRef.getValue(), invoiceLine.getExternalProductFamily());
          break;
        case "custcol_product_feature_key":
          ast.assertEquals(customFieldRef.getValue(), invoiceLine.getPricingPlanFeature());
          break;
        case "custcol_pricing_plan_id":
          ast.assertEquals(customFieldRef.getValue(), invoiceLine.getPricingPlanId());
          break;
        case "custcol_glp_stdlistpriceplan":
          ast.assertEquals(
                  Double.valueOf(String.valueOf(customFieldRef.getValue())),
                  invoiceLine.getCommercialGlpDefault().doubleValue());
          break;
        case "custcol_advantage_pricing":
          ast.assertEquals(
                  Boolean.valueOf((String) customFieldRef.getValue()),
                  invoiceLine.getAdvantageousPricingFlag());
          break;
        case "custcol_grandfathered_price_plan":
          ast.assertEquals(
                  Boolean.valueOf((String) customFieldRef.getValue()),
                  invoiceLine.getGrandfatherPricePlan());
          break;
        case "custcol_custcol_inv_edition":
          ast.assertEquals(customFieldRef.getValue(), invoiceLine.getPluginEdition());
          break;
        case "custcol_inv_plugin_tot_amount":
          ast.assertEquals(
                  Double.valueOf(String.valueOf(customFieldRef.getValue())),
                  invoiceLine.getPluginTotalAmount().doubleValue());
          break;
        case "custcol_marketplace_vendor":
          ast.assertEquals((((ListOrRecordRef) customFieldRef.getValue()).getInternalId()), invoiceLine.getVendorId());
          break;
        case "custcol_exchange_rate":
          ast.assertEquals(
                  Double.valueOf(String.valueOf(customFieldRef.getValue())), invoiceLine.getExchangeRate().doubleValue());
          break;
        case "custcol_ccp_mpac_app_platform":
          ast.assertEquals(customFieldRef.getValue(), invoiceLine.getMarketplaceAppType());
          break;
        case "custcol_discount_flag":
          ast.assertEquals(
                  customFieldRef.getValue(), invoiceLine.getDiscountFlag());
          break;
        case "custcol_source_sys":
          ast.assertEquals(customFieldRef.getValue(), invoiceLine.getLineSource());
          break;
        default:
          break;
      }
    }
  }
}
