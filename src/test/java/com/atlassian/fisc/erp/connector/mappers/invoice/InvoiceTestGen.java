package com.atlassian.fisc.erp.connector.mappers.invoice;

import com.atlassian.finance.bdm.InvoiceBDM;
import com.atlassian.finance.bdm.InvoiceLineBDM;
import com.atlassian.finance.bdm.TaxItemBDM;
import com.atlassian.finance.enums.BusinessUnit;
import com.atlassian.finance.enums.InvoiceFormType;
import com.atlassian.finance.enums.Terms;
import com.atlassian.finance.enums.TransactionSourceSystem;
import com.atlassian.fisc.erp.connector.mappers.common.TestGenHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InvoiceTestGen {

  private List<TaxItemBDM> getDummyTaxItems() {
    List<TaxItemBDM> taxItems = new ArrayList<>();
    TaxItemBDM taxItem = new TaxItemBDM();
    taxItem.setTaxAmount(BigDecimal.valueOf(15));
    taxItem.setTaxCode("TaxCode");
    taxItem.setTaxRate(new BigDecimal("3.14"));
    taxItems.add(taxItem);
    return taxItems;
  }

  private List<InvoiceLineBDM> getDummyInvoiceLines() {
    List<InvoiceLineBDM> invoiceLines = new ArrayList<>();
    InvoiceLineBDM invoiceLine = new InvoiceLineBDM();
    invoiceLine.setItemId("15195");
    invoiceLine.setQuantity(3);
    invoiceLine.setTotal(new BigDecimal("3.14"));
    invoiceLine.setDescription("Description");
    invoiceLine.setPeriodStartDate("2023-01-25T14:02:30Z");
    invoiceLine.setPeriodEndDate("2023-02-25T14:02:30+00:00");
    invoiceLine.setEntitlementId("EntitlementId");
    invoiceLine.setSaleAction("SaleAction");
    invoiceLine.setLineId("1");
    invoiceLine.setLineNumber("1");
    invoiceLine.setOrderLineId("1");
    invoiceLine.setProductId("87");
    invoiceLine.setTaxItems(getDummyTaxItems());
    invoiceLine.setIsTaxLine(Boolean.TRUE);
    invoiceLine.setNewUpgradeFlag(Boolean.TRUE);
    invoiceLine.setPreviousInvoiceId("PreviousInvoiceId");
    invoiceLine.setPreviousOrderItemId("PreviousOrderItemId");
    invoiceLine.setEvergreenRecurringBillingFlag(Boolean.TRUE);
    invoiceLine.setOrderItemId("OrderItemId");
    invoiceLine.setEntitlementNumber("EntitlementNumber");
    invoiceLine.setNewListPrice(new BigDecimal("3.14"));
    invoiceLine.setRenewListPrice(new BigDecimal("3.14"));
    invoiceLine.setHostingType("HostingType");
    invoiceLine.setBillingType("BillingType");
    invoiceLine.setManualDiscountReason("ManualDiscountReason");
    invoiceLine.setManualDiscountReasonCode("ManualDiscountReasonCode");
    invoiceLine.setManualDiscountAmount(BigDecimal.valueOf(15));
    invoiceLine.setListPriceAdjustmentReason("ListPriceAdjustmentReason");
    invoiceLine.setListPriceAdjustmentCode("ListPriceAdjustmentCode");
    invoiceLine.setListPriceAdjustmentAmount(BigDecimal.valueOf(15));
    invoiceLine.setExpertDiscountAmount(BigDecimal.valueOf(15));
    invoiceLine.setExpertDiscountPercentage(new BigDecimal("3.14"));
    invoiceLine.setLoyaltyDiscountAdjustmentReason("LoyaltyDiscountAdjustmentReason");
    invoiceLine.setLoyaltyDiscountAdjustmentCode("LoyaltyDiscountAdjustmentCode");
    invoiceLine.setLoyaltyDiscountAdjustmentAmount(BigDecimal.valueOf(15));
    invoiceLine.setLoyaltyDiscountAdjustmentPercentage(new BigDecimal("3.14"));
    invoiceLine.setVendorDiscountAmount(new BigDecimal("31.4"));
    invoiceLine.setUpgradeCreditAmount(new BigDecimal("3.14"));
    invoiceLine.setExternalProductBaseName("ExternalProductBaseName");
    invoiceLine.setExternalProductFamily("ExternalProductFamily");
    invoiceLine.setPricingPlanFeature("PricingPlanFeature");
    invoiceLine.setPricingPlanId("PricingPlanId");
    invoiceLine.setCommercialGlpDefault(BigDecimal.valueOf(15));
//    invoiceLine.setAdvantegousPricingFlag(Boolean.TRUE);
    invoiceLine.setGrandfatherPricePlan(Boolean.TRUE);
    invoiceLine.setPluginEdition("PluginEdition");
    invoiceLine.setPluginTotalAmount(BigDecimal.valueOf(15));
    invoiceLine.setVendorId("VendorId");
    invoiceLine.setExchangeRate(new BigDecimal("3.14"));
    invoiceLine.setMarketplaceAppType("MarketplaceAppType");
    invoiceLine.setDiscountFlag(Boolean.TRUE);
    invoiceLine.setLineSource("lineSource");
    invoiceLines.add(invoiceLine);
    return invoiceLines;
  }

  public InvoiceBDM generateDummyData() {
    InvoiceBDM invoiceBdm = new InvoiceBDM();
    invoiceBdm.setSourceSystem(TransactionSourceSystem.HAMS_INTEGRATION);
    invoiceBdm.setId("1d");
    invoiceBdm.setInvoiceErpId("InvoiceErpId");
    invoiceBdm.setTransactionNumber("TransactionNumber");
    invoiceBdm.setTransactionDate("2023-01-25T14:02:30-00:00");
    invoiceBdm.setFormType(InvoiceFormType.HAMS_INV);
    invoiceBdm.setCustomerId("CustomerId");
    invoiceBdm.setCurrency("USD");
    invoiceBdm.setBillTo(TestGenHelper.getDummyAddress(true));
    invoiceBdm.setShipTo(TestGenHelper.getDummyAddress(false));
    invoiceBdm.setInvoiceLines(getDummyInvoiceLines());
    invoiceBdm.setDueDate("2023-01-25T14:02:30+05:30");
    invoiceBdm.setIsPartnerInvoice(Boolean.TRUE);
    invoiceBdm.setEndCustomerId("EndCustomerId");
    invoiceBdm.setContactId("ContactId");
    invoiceBdm.setAccountId("AccountId");
    invoiceBdm.setSubsidiaryId(BusinessUnit.ATLASSIAN_AU_BU_AUD);
    invoiceBdm.setVatNumber("VatNumber");
    invoiceBdm.setStatus("PAID");
    invoiceBdm.setPurchaseOrderNumber("PurchaseOrderNumber");
    invoiceBdm.setTerms(Terms.IMMEDIATE);
    invoiceBdm.setCustomerType("Aggregator");
    invoiceBdm.setPaymentMethod("PAIDCC");
    invoiceBdm.setCustomerCreditCardCountry("customerCreditCardCountry");
    invoiceBdm.setBillingRegion("BillingRegion");
    invoiceBdm.setShippingRegion("shippingRegion");
    invoiceBdm.setBillingContactId("billingContactId");
    invoiceBdm.setTechnicalContactId("technicalContactId");
    invoiceBdm.setCmodFlag(Boolean.TRUE);
    invoiceBdm.setExchangeRate(new BigDecimal("3.14"));
    invoiceBdm.setUncollectibleInvoiceFlag(Boolean.TRUE);
    invoiceBdm.setReInvoicedFlag(Boolean.TRUE);
    invoiceBdm.setPaidDate("2023-01-25T14:02:30+05:30");
    invoiceBdm.setOriginalDunningDate("2023-01-25T14:02:30+05:30");
    invoiceBdm.setContractNumber("ContractNumber");
    invoiceBdm.setBalanceAmount(new BigDecimal("3.14"));

    return invoiceBdm;
  }
}
