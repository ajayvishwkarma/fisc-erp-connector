package com.atlassian.fisc.erp.connector.mappers.invoice;

import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldRef;
import com.atlassian.fisc.erp.connector.domain.invoice.Invoice;
import com.atlassian.fisc.erp.connector.domain.invoice.InvoiceItem;
import com.atlassian.fisc.erp.connector.domain.invoice.InvoiceItemList;
import com.atlassian.fisc.erp.connector.mappers.common.TestGenHelper;
import com.atlassian.fisc.erp.connector.util.TestUtil;

import java.util.LinkedList;
import java.util.List;

import static com.atlassian.fisc.erp.connector.mappers.NetSuiteMapperFn.mapToRecordRef;
import static com.atlassian.fisc.erp.connector.util.TestUtil.calDate;

public class NsInvoiceTestGen {

  public Invoice generateDummyData() {
    Invoice nsInvoice = new Invoice();
    nsInvoice.setCreatedDate(calDate(2023, 11, 12));
    nsInvoice.setLastModifiedDate(calDate(2023, 11, 12));
    nsInvoice.setNexus(mapToRecordRef("InternalId"));
    nsInvoice.setSubsidiaryTaxRegNum(mapToRecordRef("InternalId"));
    nsInvoice.setTaxRegOverride(Boolean.TRUE);
    nsInvoice.setTaxDetailsOverride(Boolean.TRUE);
    nsInvoice.setCustomForm(mapToRecordRef("InternalId"));
    nsInvoice.setNextApprover(mapToRecordRef("InternalId"));
    nsInvoice.setEntity(mapToRecordRef("InternalId"));
    nsInvoice.setBillingAccount(mapToRecordRef("InternalId"));
    nsInvoice.setRecurringBill(Boolean.TRUE);
    nsInvoice.setTranDate(calDate(2023, 11, 12));
    nsInvoice.setTranId("TranId");
    nsInvoice.setEntityTaxRegNum(mapToRecordRef("InternalId"));
    nsInvoice.setSource("Source");
    nsInvoice.setCreatedFrom(mapToRecordRef("InternalId"));
    nsInvoice.setPostingPeriod(mapToRecordRef("InternalId"));
    nsInvoice.setOpportunity(mapToRecordRef("InternalId"));
    nsInvoice.setDepartment(mapToRecordRef("InternalId"));
    nsInvoice.set_class(mapToRecordRef("InternalId"));
    nsInvoice.setTerms(mapToRecordRef("IMMEDIATE"));
    nsInvoice.setLocation(mapToRecordRef("InternalId"));
    nsInvoice.setSubsidiary(mapToRecordRef("InternalId"));
    nsInvoice.setCurrency(mapToRecordRef("1"));
    nsInvoice.setDueDate(calDate(2023, 11, 12));
    nsInvoice.setDiscountDate(calDate(2023, 11, 12));
    nsInvoice.setDiscountAmount(1D);
    nsInvoice.setSalesRep(mapToRecordRef("InternalId"));
    nsInvoice.setContribPct("ContribPct");
    nsInvoice.setPartner(mapToRecordRef("InternalId"));
    nsInvoice.setLeadSource(mapToRecordRef("InternalId"));
    nsInvoice.setStartDate(calDate(2023, 11, 12));
    nsInvoice.setEndDate(calDate(2023, 11, 12));
    nsInvoice.setOtherRefNum("OtherRefNum");
    nsInvoice.setMemo("Memo");
    nsInvoice.setSalesEffectiveDate(calDate(2023, 11, 12));
    nsInvoice.setExcludeCommission(Boolean.TRUE);
    nsInvoice.setTotalCostEstimate(1D);
    nsInvoice.setEstGrossProfit(1D);
    nsInvoice.setEstGrossProfitPercent(1D);
    nsInvoice.setRevRecSchedule(mapToRecordRef("InternalId"));
    nsInvoice.setRevRecStartDate(calDate(2023, 11, 12));
    nsInvoice.setRevRecEndDate(calDate(2023, 11, 12));
    nsInvoice.setAmountPaid(1D);
    nsInvoice.setAmountRemaining(1D);
    nsInvoice.setBalance(1D);
    nsInvoice.setAccount(mapToRecordRef("InternalId"));
    nsInvoice.setOnCreditHold("OnCreditHold");
    nsInvoice.setExchangeRate(1D);
    nsInvoice.setCurrencyName("US Dollars");
    nsInvoice.setPromoCode(mapToRecordRef("InternalId"));
    nsInvoice.setDiscountItem(mapToRecordRef("InternalId"));
    nsInvoice.setDiscountRate("DiscountRate");
    nsInvoice.setIsTaxable(Boolean.TRUE);
    nsInvoice.setTaxItem(mapToRecordRef("InternalId"));
    nsInvoice.setTaxRate(1D);
    nsInvoice.setToBePrinted(Boolean.TRUE);
    nsInvoice.setToBeEmailed(Boolean.TRUE);
    nsInvoice.setToBeFaxed(Boolean.TRUE);
    nsInvoice.setFax("Fax");
    nsInvoice.setMessageSel(mapToRecordRef("InternalId"));
    nsInvoice.setMessage("Message");
    nsInvoice.setBillingAddress(TestGenHelper.getDummyNsAddress(true));
    nsInvoice.setBillAddressList(mapToRecordRef("InternalId"));
    nsInvoice.setShippingAddress(TestGenHelper.getDummyNsAddress(false));
    nsInvoice.setShipIsResidential(Boolean.TRUE);
    nsInvoice.setShipAddressList(mapToRecordRef("InternalId"));
    nsInvoice.setFob("fob");
    nsInvoice.setShipDate(calDate(2023, 11, 12));
    nsInvoice.setShipMethod(mapToRecordRef("InternalId"));
    nsInvoice.setShippingCost(1D);
    nsInvoice.setShippingTax1Rate(1D);
    nsInvoice.setShippingTax2Rate("ShippingTax2Rate");
    nsInvoice.setShippingTaxCode(mapToRecordRef("InternalId"));
    nsInvoice.setHandlingTaxCode(mapToRecordRef("InternalId"));
    nsInvoice.setHandlingTax1Rate(1D);
    nsInvoice.setHandlingCost(1D);
    nsInvoice.setHandlingTax2Rate("HandlingTax2Rate");
    nsInvoice.setTrackingNumbers("TrackingNumbers");
    nsInvoice.setLinkedTrackingNumbers("LinkedTrackingNumbers");
    nsInvoice.setSalesGroup(mapToRecordRef("InternalId"));
    nsInvoice.setSubTotal(1D);
    nsInvoice.setCanHaveStackable(Boolean.TRUE);
    nsInvoice.setRecognizedRevenue(1D);
    nsInvoice.setDeferredRevenue(1D);
    nsInvoice.setRevRecOnRevCommitment(Boolean.TRUE);
    nsInvoice.setSyncSalesTeams(Boolean.TRUE);
    nsInvoice.setDiscountTotal(1D);
    nsInvoice.setTaxTotal(1D);
    nsInvoice.setAltShippingCost(1D);
    nsInvoice.setAltHandlingCost(1D);
    nsInvoice.setTotal(1D);
    nsInvoice.setStatus("Status");
    nsInvoice.setJob(mapToRecordRef("InternalId"));
    nsInvoice.setBillingSchedule(mapToRecordRef("InternalId"));
    nsInvoice.setEmail("Email");
    nsInvoice.setTax2Total(1D);
    nsInvoice.setVatRegNum("VatRegNum");
    nsInvoice.setExpCostDiscount(mapToRecordRef("InternalId"));
    nsInvoice.setItemCostDiscount(mapToRecordRef("InternalId"));
    nsInvoice.setTimeDiscount(mapToRecordRef("InternalId"));
    nsInvoice.setExpCostDiscRate("ExpCostDiscRate");
    nsInvoice.setItemCostDiscRate("ItemCostDiscRate");
    nsInvoice.setTimeDiscRate("TimeDiscRate");
    nsInvoice.setExpCostDiscAmount(1D);
    nsInvoice.setExpCostTaxRate1(1D);
    nsInvoice.setExpCostTaxRate2(1D);
    nsInvoice.setItemCostDiscAmount(1D);
    nsInvoice.setExpCostTaxCode(mapToRecordRef("InternalId"));
    nsInvoice.setExpCostDiscTax1Amt(1D);
    nsInvoice.setItemCostTaxRate1(1D);
    nsInvoice.setTimeDiscAmount(1D);
    nsInvoice.setItemCostTaxCode(mapToRecordRef("InternalId"));
    nsInvoice.setExpCostDiscTaxable(Boolean.TRUE);
    nsInvoice.setItemCostDiscTaxable(Boolean.TRUE);
    nsInvoice.setItemCostTaxRate2(1D);
    nsInvoice.setItemCostDiscTax1Amt(1D);
    nsInvoice.setItemCostDiscPrint(Boolean.TRUE);
    nsInvoice.setTimeDiscTaxable(Boolean.TRUE);
    nsInvoice.setTimeTaxRate1(1D);
    nsInvoice.setExpCostDiscPrint(Boolean.TRUE);
    nsInvoice.setTimeTaxCode(mapToRecordRef("InternalId"));
    nsInvoice.setTimeDiscPrint(Boolean.TRUE);
    nsInvoice.setGiftCertApplied(1D);
    nsInvoice.setTimeDiscTax1Amt(1D);
    nsInvoice.setTranIsVsoeBundle(Boolean.TRUE);
    nsInvoice.setTimeTaxRate2(1D);
    nsInvoice.setVsoeAutoCalc(Boolean.TRUE);
    nsInvoice.setSyncPartnerTeams(Boolean.TRUE);
    nsInvoice.setItemList(getDummyItemList());
    nsInvoice.setApprovalStatus(mapToRecordRef("InternalId"));
    nsInvoice.setInternalId("internalId");
    nsInvoice.setExternalId("ExternalId");
    nsInvoice.setCustomFieldList(buildInvoiceCustomFields());
    return nsInvoice;
  }

  private InvoiceItemList getDummyItemList() {
    InvoiceItemList invoiceItemList = new InvoiceItemList();
    InvoiceItem[] invoiceItems = new InvoiceItem[1];
    InvoiceItem invoiceItem = new InvoiceItem();
    invoiceItem.setJob(mapToRecordRef("Job"));
    invoiceItem.setLine(1L);
    invoiceItem.setDescription("Description");
    invoiceItem.setAmount(3.14);
    invoiceItem.setQuantity(3D);
    invoiceItem.setOrderLine(1L);
    invoiceItem.setRevRecStartDate(calDate(2023, 11, 12));
    invoiceItem.setRevRecEndDate(calDate(2023, 11, 12));
    invoiceItem.setItem(mapToRecordRef("14667"));
    invoiceItem.set_class(mapToRecordRef("ProductId"));
    invoiceItem.setCustomFieldList(buildInvoiceItemCustomFields());

    invoiceItems[0] = invoiceItem;
    invoiceItemList.setItem(invoiceItems);
    return invoiceItemList;
  }

  private CustomFieldList buildInvoiceCustomFields() {
    //  TODO add custom fields
    List<CustomFieldRef> customFields = new LinkedList<>();
    customFields.add(new CustomFieldRef("1", "custbody_contract_modification", false));
    customFields.add(new CustomFieldRef("2", "custbody_atl_cust_type", "Aggregator"));
    return TestUtil.toCustomFieldList(customFields);
  }

  private CustomFieldList buildInvoiceItemCustomFields() {
    //  TODO add custom fields
    List<CustomFieldRef> customFields = new LinkedList<>();
    customFields.add(new CustomFieldRef("1", "custcol_poi_id", "value:custcol_poi_id"));
    return TestUtil.toCustomFieldList(customFields);
  }
}
