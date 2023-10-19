package com.atlassian.fisc.erp.connector.domain.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.HashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class RecordType implements Serializable {

  private String _value_;
  private static HashMap _table_ = new HashMap();
  public static final String _account = "account";
  public static final String _accountingPeriod = "accountingPeriod";
  public static final String _advInterCompanyJournalEntry = "advInterCompanyJournalEntry";
  public static final String _assemblyBuild = "assemblyBuild";
  public static final String _assemblyUnbuild = "assemblyUnbuild";
  public static final String _assemblyItem = "assemblyItem";
  public static final String _billingAccount = "billingAccount";
  public static final String _billingSchedule = "billingSchedule";
  public static final String _bin = "bin";
  public static final String _binTransfer = "binTransfer";
  public static final String _binWorksheet = "binWorksheet";
  public static final String _bom = "bom";
  public static final String _bomRevision = "bomRevision";
  public static final String _budget = "budget";
  public static final String _budgetCategory = "budgetCategory";
  public static final String _calendarEvent = "calendarEvent";
  public static final String _campaign = "campaign";
  public static final String _campaignAudience = "campaignAudience";
  public static final String _campaignCategory = "campaignCategory";
  public static final String _campaignChannel = "campaignChannel";
  public static final String _campaignFamily = "campaignFamily";
  public static final String _campaignOffer = "campaignOffer";
  public static final String _campaignResponse = "campaignResponse";
  public static final String _campaignSearchEngine = "campaignSearchEngine";
  public static final String _campaignSubscription = "campaignSubscription";
  public static final String _campaignVertical = "campaignVertical";
  public static final String _cashRefund = "cashRefund";
  public static final String _cashSale = "cashSale";
  public static final String _check = "check";
  public static final String _charge = "charge";
  public static final String _classification = "classification";
  public static final String _consolidatedExchangeRate = "consolidatedExchangeRate";
  public static final String _contact = "contact";
  public static final String _contactCategory = "contactCategory";
  public static final String _contactRole = "contactRole";
  public static final String _costCategory = "costCategory";
  public static final String _couponCode = "couponCode";
  public static final String _creditMemo = "creditMemo";
  public static final String _crmCustomField = "crmCustomField";
  public static final String _currency = "currency";
  public static final String _currencyRate = "currencyRate";
  public static final String _customList = "customList";
  public static final String _customPurchase = "customPurchase";
  public static final String _customRecord = "customRecord";
  public static final String _customRecordCustomField = "customRecordCustomField";
  public static final String _customRecordType = "customRecordType";
  public static final String _customSale = "customSale";
  public static final String _customSegment = "customSegment";
  public static final String _customTransaction = "customTransaction";
  public static final String _customTransactionType = "customTransactionType";
  public static final String _customer = "customer";
  public static final String _customerCategory = "customerCategory";
  public static final String _customerDeposit = "customerDeposit";
  public static final String _customerMessage = "customerMessage";
  public static final String _customerPayment = "customerPayment";
  public static final String _customerRefund = "customerRefund";
  public static final String _customerStatus = "customerStatus";
  public static final String _customerSubsidiaryRelationship = "customerSubsidiaryRelationship";
  public static final String _deposit = "deposit";
  public static final String _depositApplication = "depositApplication";
  public static final String _department = "department";
  public static final String _descriptionItem = "descriptionItem";
  public static final String _discountItem = "discountItem";
  public static final String _downloadItem = "downloadItem";
  public static final String _employee = "employee";
  public static final String _entityCustomField = "entityCustomField";
  public static final String _entityGroup = "entityGroup";
  public static final String _estimate = "estimate";
  public static final String _expenseCategory = "expenseCategory";
  public static final String _expenseReport = "expenseReport";
  public static final String _fairValuePrice = "fairValuePrice";
  public static final String _file = "file";
  public static final String _folder = "folder";
  public static final String _generalToken = "generalToken";
  public static final String _giftCertificate = "giftCertificate";
  public static final String _giftCertificateItem = "giftCertificateItem";
  public static final String _globalAccountMapping = "globalAccountMapping";
  public static final String _hcmJob = "hcmJob";
  public static final String _inboundShipment = "inboundShipment";
  public static final String _interCompanyJournalEntry = "interCompanyJournalEntry";
  public static final String _interCompanyTransferOrder = "interCompanyTransferOrder";
  public static final String _inventoryAdjustment = "inventoryAdjustment";
  public static final String _inventoryCostRevaluation = "inventoryCostRevaluation";
  public static final String _inventoryItem = "inventoryItem";
  public static final String _inventoryNumber = "inventoryNumber";
  public static final String _inventoryTransfer = "inventoryTransfer";
  public static final String _invoice = "invoice";
  public static final String _itemAccountMapping = "itemAccountMapping";
  public static final String _itemCustomField = "itemCustomField";
  public static final String _itemDemandPlan = "itemDemandPlan";
  public static final String _itemFulfillment = "itemFulfillment";
  public static final String _itemGroup = "itemGroup";
  public static final String _itemNumberCustomField = "itemNumberCustomField";
  public static final String _itemOptionCustomField = "itemOptionCustomField";
  public static final String _itemSupplyPlan = "itemSupplyPlan";
  public static final String _itemRevision = "itemRevision";
  public static final String _issue = "issue";
  public static final String _job = "job";
  public static final String _jobStatus = "jobStatus";
  public static final String _jobType = "jobType";
  public static final String _itemReceipt = "itemReceipt";
  public static final String _journalEntry = "journalEntry";
  public static final String _kitItem = "kitItem";
  public static final String _leadSource = "leadSource";
  public static final String _location = "location";
  public static final String _lotNumberedInventoryItem = "lotNumberedInventoryItem";
  public static final String _lotNumberedAssemblyItem = "lotNumberedAssemblyItem";
  public static final String _markupItem = "markupItem";
  public static final String _merchandiseHierarchyNode = "merchandiseHierarchyNode";
  public static final String _message = "message";
  public static final String _manufacturingCostTemplate = "manufacturingCostTemplate";
  public static final String _manufacturingOperationTask = "manufacturingOperationTask";
  public static final String _manufacturingRouting = "manufacturingRouting";
  public static final String _nexus = "nexus";
  public static final String _nonInventoryPurchaseItem = "nonInventoryPurchaseItem";
  public static final String _nonInventoryResaleItem = "nonInventoryResaleItem";
  public static final String _nonInventorySaleItem = "nonInventorySaleItem";
  public static final String _note = "note";
  public static final String _noteType = "noteType";
  public static final String _opportunity = "opportunity";
  public static final String _otherChargePurchaseItem = "otherChargePurchaseItem";
  public static final String _otherChargeResaleItem = "otherChargeResaleItem";
  public static final String _otherChargeSaleItem = "otherChargeSaleItem";
  public static final String _otherCustomField = "otherCustomField";
  public static final String _otherNameCategory = "otherNameCategory";
  public static final String _partner = "partner";
  public static final String _partnerCategory = "partnerCategory";
  public static final String _paycheck = "paycheck";
  public static final String _paycheckJournal = "paycheckJournal";
  public static final String _paymentCard = "paymentCard";
  public static final String _paymentCardToken = "paymentCardToken";
  public static final String _paymentItem = "paymentItem";
  public static final String _paymentMethod = "paymentMethod";
  public static final String _payrollItem = "payrollItem";
  public static final String _periodEndJournal = "periodEndJournal";
  public static final String _phoneCall = "phoneCall";
  public static final String _priceLevel = "priceLevel";
  public static final String _pricingGroup = "pricingGroup";
  public static final String _projectTask = "projectTask";
  public static final String _promotionCode = "promotionCode";
  public static final String _purchaseOrder = "purchaseOrder";
  public static final String _purchaseRequisition = "purchaseRequisition";
  public static final String _resourceAllocation = "resourceAllocation";
  public static final String _returnAuthorization = "returnAuthorization";
  public static final String _revRecSchedule = "revRecSchedule";
  public static final String _revRecTemplate = "revRecTemplate";
  public static final String _salesOrder = "salesOrder";
  public static final String _salesRole = "salesRole";
  public static final String _salesTaxItem = "salesTaxItem";
  public static final String _serializedInventoryItem = "serializedInventoryItem";
  public static final String _serializedAssemblyItem = "serializedAssemblyItem";
  public static final String _servicePurchaseItem = "servicePurchaseItem";
  public static final String _serviceResaleItem = "serviceResaleItem";
  public static final String _serviceSaleItem = "serviceSaleItem";
  public static final String _solution = "solution";
  public static final String _siteCategory = "siteCategory";
  public static final String _state = "state";
  public static final String _statisticalJournalEntry = "statisticalJournalEntry";
  public static final String _subsidiary = "subsidiary";
  public static final String _subtotalItem = "subtotalItem";
  public static final String _supportCase = "supportCase";
  public static final String _supportCaseIssue = "supportCaseIssue";
  public static final String _supportCaseOrigin = "supportCaseOrigin";
  public static final String _supportCasePriority = "supportCasePriority";
  public static final String _supportCaseStatus = "supportCaseStatus";
  public static final String _supportCaseType = "supportCaseType";
  public static final String _task = "task";
  public static final String _taxAcct = "taxAcct";
  public static final String _taxGroup = "taxGroup";
  public static final String _taxType = "taxType";
  public static final String _term = "term";
  public static final String _timeBill = "timeBill";
  public static final String _timeSheet = "timeSheet";
  public static final String _topic = "topic";
  public static final String _transferOrder = "transferOrder";
  public static final String _transactionBodyCustomField = "transactionBodyCustomField";
  public static final String _transactionColumnCustomField = "transactionColumnCustomField";
  public static final String _unitsType = "unitsType";
  public static final String _usage = "usage";
  public static final String _vendor = "vendor";
  public static final String _vendorCategory = "vendorCategory";
  public static final String _vendorBill = "vendorBill";
  public static final String _vendorCredit = "vendorCredit";
  public static final String _vendorPayment = "vendorPayment";
  public static final String _vendorReturnAuthorization = "vendorReturnAuthorization";
  public static final String _vendorSubsidiaryRelationship = "vendorSubsidiaryRelationship";
  public static final String _winLossReason = "winLossReason";
  public static final String _workOrder = "workOrder";
  public static final String _workOrderIssue = "workOrderIssue";
  public static final String _workOrderCompletion = "workOrderCompletion";
  public static final String _workOrderClose = "workOrderClose";
  public static final RecordType account = new RecordType("account");
  public static final RecordType accountingPeriod = new RecordType("accountingPeriod");
  public static final RecordType advInterCompanyJournalEntry =
      new RecordType("advInterCompanyJournalEntry");
  public static final RecordType assemblyBuild = new RecordType("assemblyBuild");
  public static final RecordType assemblyUnbuild = new RecordType("assemblyUnbuild");
  public static final RecordType assemblyItem = new RecordType("assemblyItem");
  public static final RecordType billingAccount = new RecordType("billingAccount");
  public static final RecordType billingSchedule = new RecordType("billingSchedule");
  public static final RecordType bin = new RecordType("bin");
  public static final RecordType binTransfer = new RecordType("binTransfer");
  public static final RecordType binWorksheet = new RecordType("binWorksheet");
  public static final RecordType bom = new RecordType("bom");
  public static final RecordType bomRevision = new RecordType("bomRevision");
  public static final RecordType budget = new RecordType("budget");
  public static final RecordType budgetCategory = new RecordType("budgetCategory");
  public static final RecordType calendarEvent = new RecordType("calendarEvent");
  public static final RecordType campaign = new RecordType("campaign");
  public static final RecordType campaignAudience = new RecordType("campaignAudience");
  public static final RecordType campaignCategory = new RecordType("campaignCategory");
  public static final RecordType campaignChannel = new RecordType("campaignChannel");
  public static final RecordType campaignFamily = new RecordType("campaignFamily");
  public static final RecordType campaignOffer = new RecordType("campaignOffer");
  public static final RecordType campaignResponse = new RecordType("campaignResponse");
  public static final RecordType campaignSearchEngine = new RecordType("campaignSearchEngine");
  public static final RecordType campaignSubscription = new RecordType("campaignSubscription");
  public static final RecordType campaignVertical = new RecordType("campaignVertical");
  public static final RecordType cashRefund = new RecordType("cashRefund");
  public static final RecordType cashSale = new RecordType("cashSale");
  public static final RecordType check = new RecordType("check");
  public static final RecordType charge = new RecordType("charge");
  public static final RecordType classification = new RecordType("classification");
  public static final RecordType consolidatedExchangeRate =
      new RecordType("consolidatedExchangeRate");
  public static final RecordType contact = new RecordType("contact");
  public static final RecordType contactCategory = new RecordType("contactCategory");
  public static final RecordType contactRole = new RecordType("contactRole");
  public static final RecordType costCategory = new RecordType("costCategory");
  public static final RecordType couponCode = new RecordType("couponCode");
  public static final RecordType creditMemo = new RecordType("creditMemo");
  public static final RecordType crmCustomField = new RecordType("crmCustomField");
  public static final RecordType currency = new RecordType("currency");
  public static final RecordType currencyRate = new RecordType("currencyRate");
  public static final RecordType customList = new RecordType("customList");
  public static final RecordType customPurchase = new RecordType("customPurchase");
  public static final RecordType customRecord = new RecordType("customRecord");
  public static final RecordType customRecordCustomField =
      new RecordType("customRecordCustomField");
  public static final RecordType customRecordType = new RecordType("customRecordType");
  public static final RecordType customSale = new RecordType("customSale");
  public static final RecordType customSegment = new RecordType("customSegment");
  public static final RecordType customTransaction = new RecordType("customTransaction");
  public static final RecordType customTransactionType = new RecordType("customTransactionType");
  public static final RecordType customer = new RecordType("customer");
  public static final RecordType customerCategory = new RecordType("customerCategory");
  public static final RecordType customerDeposit = new RecordType("customerDeposit");
  public static final RecordType customerMessage = new RecordType("customerMessage");
  public static final RecordType customerPayment = new RecordType("customerPayment");
  public static final RecordType customerRefund = new RecordType("customerRefund");
  public static final RecordType customerStatus = new RecordType("customerStatus");
  public static final RecordType customerSubsidiaryRelationship =
      new RecordType("customerSubsidiaryRelationship");
  public static final RecordType deposit = new RecordType("deposit");
  public static final RecordType depositApplication = new RecordType("depositApplication");
  public static final RecordType department = new RecordType("department");
  public static final RecordType descriptionItem = new RecordType("descriptionItem");
  public static final RecordType discountItem = new RecordType("discountItem");
  public static final RecordType downloadItem = new RecordType("downloadItem");
  public static final RecordType employee = new RecordType("employee");
  public static final RecordType entityCustomField = new RecordType("entityCustomField");
  public static final RecordType entityGroup = new RecordType("entityGroup");
  public static final RecordType estimate = new RecordType("estimate");
  public static final RecordType expenseCategory = new RecordType("expenseCategory");
  public static final RecordType expenseReport = new RecordType("expenseReport");
  public static final RecordType fairValuePrice = new RecordType("fairValuePrice");
  public static final RecordType file = new RecordType("file");
  public static final RecordType folder = new RecordType("folder");
  public static final RecordType generalToken = new RecordType("generalToken");
  public static final RecordType giftCertificate = new RecordType("giftCertificate");
  public static final RecordType giftCertificateItem = new RecordType("giftCertificateItem");
  public static final RecordType globalAccountMapping = new RecordType("globalAccountMapping");
  public static final RecordType hcmJob = new RecordType("hcmJob");
  public static final RecordType inboundShipment = new RecordType("inboundShipment");
  public static final RecordType interCompanyJournalEntry =
      new RecordType("interCompanyJournalEntry");
  public static final RecordType interCompanyTransferOrder =
      new RecordType("interCompanyTransferOrder");
  public static final RecordType inventoryAdjustment = new RecordType("inventoryAdjustment");
  public static final RecordType inventoryCostRevaluation =
      new RecordType("inventoryCostRevaluation");
  public static final RecordType inventoryItem = new RecordType("inventoryItem");
  public static final RecordType inventoryNumber = new RecordType("inventoryNumber");
  public static final RecordType inventoryTransfer = new RecordType("inventoryTransfer");
  public static final RecordType invoice = new RecordType("invoice");
  public static final RecordType itemAccountMapping = new RecordType("itemAccountMapping");
  public static final RecordType itemCustomField = new RecordType("itemCustomField");
  public static final RecordType itemDemandPlan = new RecordType("itemDemandPlan");
  public static final RecordType itemFulfillment = new RecordType("itemFulfillment");
  public static final RecordType itemGroup = new RecordType("itemGroup");
  public static final RecordType itemNumberCustomField = new RecordType("itemNumberCustomField");
  public static final RecordType itemOptionCustomField = new RecordType("itemOptionCustomField");
  public static final RecordType itemSupplyPlan = new RecordType("itemSupplyPlan");
  public static final RecordType itemRevision = new RecordType("itemRevision");
  public static final RecordType issue = new RecordType("issue");
  public static final RecordType job = new RecordType("job");
  public static final RecordType jobStatus = new RecordType("jobStatus");
  public static final RecordType jobType = new RecordType("jobType");
  public static final RecordType itemReceipt = new RecordType("itemReceipt");
  public static final RecordType journalEntry = new RecordType("journalEntry");
  public static final RecordType kitItem = new RecordType("kitItem");
  public static final RecordType leadSource = new RecordType("leadSource");
  public static final RecordType location = new RecordType("location");
  public static final RecordType lotNumberedInventoryItem =
      new RecordType("lotNumberedInventoryItem");
  public static final RecordType lotNumberedAssemblyItem =
      new RecordType("lotNumberedAssemblyItem");
  public static final RecordType markupItem = new RecordType("markupItem");
  public static final RecordType merchandiseHierarchyNode =
      new RecordType("merchandiseHierarchyNode");
  public static final RecordType message = new RecordType("message");
  public static final RecordType manufacturingCostTemplate =
      new RecordType("manufacturingCostTemplate");
  public static final RecordType manufacturingOperationTask =
      new RecordType("manufacturingOperationTask");
  public static final RecordType manufacturingRouting = new RecordType("manufacturingRouting");
  public static final RecordType nexus = new RecordType("nexus");
  public static final RecordType nonInventoryPurchaseItem =
      new RecordType("nonInventoryPurchaseItem");
  public static final RecordType nonInventoryResaleItem = new RecordType("nonInventoryResaleItem");
  public static final RecordType nonInventorySaleItem = new RecordType("nonInventorySaleItem");
  public static final RecordType note = new RecordType("note");
  public static final RecordType noteType = new RecordType("noteType");
  public static final RecordType opportunity = new RecordType("opportunity");
  public static final RecordType otherChargePurchaseItem =
      new RecordType("otherChargePurchaseItem");
  public static final RecordType otherChargeResaleItem = new RecordType("otherChargeResaleItem");
  public static final RecordType otherChargeSaleItem = new RecordType("otherChargeSaleItem");
  public static final RecordType otherCustomField = new RecordType("otherCustomField");
  public static final RecordType otherNameCategory = new RecordType("otherNameCategory");
  public static final RecordType partner = new RecordType("partner");
  public static final RecordType partnerCategory = new RecordType("partnerCategory");
  public static final RecordType paycheck = new RecordType("paycheck");
  public static final RecordType paycheckJournal = new RecordType("paycheckJournal");
  public static final RecordType paymentCard = new RecordType("paymentCard");
  public static final RecordType paymentCardToken = new RecordType("paymentCardToken");
  public static final RecordType paymentItem = new RecordType("paymentItem");
  public static final RecordType paymentMethod = new RecordType("paymentMethod");
  public static final RecordType payrollItem = new RecordType("payrollItem");
  public static final RecordType periodEndJournal = new RecordType("periodEndJournal");
  public static final RecordType phoneCall = new RecordType("phoneCall");
  public static final RecordType priceLevel = new RecordType("priceLevel");
  public static final RecordType pricingGroup = new RecordType("pricingGroup");
  public static final RecordType projectTask = new RecordType("projectTask");
  public static final RecordType promotionCode = new RecordType("promotionCode");
  public static final RecordType purchaseOrder = new RecordType("purchaseOrder");
  public static final RecordType purchaseRequisition = new RecordType("purchaseRequisition");
  public static final RecordType resourceAllocation = new RecordType("resourceAllocation");
  public static final RecordType returnAuthorization = new RecordType("returnAuthorization");
  public static final RecordType revRecSchedule = new RecordType("revRecSchedule");
  public static final RecordType revRecTemplate = new RecordType("revRecTemplate");
  public static final RecordType salesOrder = new RecordType("salesOrder");
  public static final RecordType salesRole = new RecordType("salesRole");
  public static final RecordType salesTaxItem = new RecordType("salesTaxItem");
  public static final RecordType serializedInventoryItem =
      new RecordType("serializedInventoryItem");
  public static final RecordType serializedAssemblyItem = new RecordType("serializedAssemblyItem");
  public static final RecordType servicePurchaseItem = new RecordType("servicePurchaseItem");
  public static final RecordType serviceResaleItem = new RecordType("serviceResaleItem");
  public static final RecordType serviceSaleItem = new RecordType("serviceSaleItem");
  public static final RecordType solution = new RecordType("solution");
  public static final RecordType siteCategory = new RecordType("siteCategory");
  public static final RecordType state = new RecordType("state");
  public static final RecordType statisticalJournalEntry =
      new RecordType("statisticalJournalEntry");
  public static final RecordType subsidiary = new RecordType("subsidiary");
  public static final RecordType subtotalItem = new RecordType("subtotalItem");
  public static final RecordType supportCase = new RecordType("supportCase");
  public static final RecordType supportCaseIssue = new RecordType("supportCaseIssue");
  public static final RecordType supportCaseOrigin = new RecordType("supportCaseOrigin");
  public static final RecordType supportCasePriority = new RecordType("supportCasePriority");
  public static final RecordType supportCaseStatus = new RecordType("supportCaseStatus");
  public static final RecordType supportCaseType = new RecordType("supportCaseType");
  public static final RecordType task = new RecordType("task");
  public static final RecordType taxAcct = new RecordType("taxAcct");
  public static final RecordType taxGroup = new RecordType("taxGroup");
  public static final RecordType taxType = new RecordType("taxType");
  public static final RecordType term = new RecordType("term");
  public static final RecordType timeBill = new RecordType("timeBill");
  public static final RecordType timeSheet = new RecordType("timeSheet");
  public static final RecordType topic = new RecordType("topic");
  public static final RecordType transferOrder = new RecordType("transferOrder");
  public static final RecordType transactionBodyCustomField =
      new RecordType("transactionBodyCustomField");
  public static final RecordType transactionColumnCustomField =
      new RecordType("transactionColumnCustomField");
  public static final RecordType unitsType = new RecordType("unitsType");
  public static final RecordType usage = new RecordType("usage");
  public static final RecordType vendor = new RecordType("vendor");
  public static final RecordType vendorCategory = new RecordType("vendorCategory");
  public static final RecordType vendorBill = new RecordType("vendorBill");
  public static final RecordType vendorCredit = new RecordType("vendorCredit");
  public static final RecordType vendorPayment = new RecordType("vendorPayment");
  public static final RecordType vendorReturnAuthorization =
      new RecordType("vendorReturnAuthorization");
  public static final RecordType vendorSubsidiaryRelationship =
      new RecordType("vendorSubsidiaryRelationship");
  public static final RecordType winLossReason = new RecordType("winLossReason");
  public static final RecordType workOrder = new RecordType("workOrder");
  public static final RecordType workOrderIssue = new RecordType("workOrderIssue");
  public static final RecordType workOrderCompletion = new RecordType("workOrderCompletion");
  public static final RecordType workOrderClose = new RecordType("workOrderClose");

  protected RecordType(String value) {
    this._value_ = value;
    _table_.put(this._value_, this);
  }

  public String getValue() {
    return this._value_;
  }

  public static RecordType fromValue(String value) throws IllegalArgumentException {
    RecordType enumeration = (RecordType) _table_.get(value);
    if (enumeration == null) {
      throw new IllegalArgumentException();
    } else {
      return enumeration;
    }
  }

  @JsonCreator
  public static RecordType fromString(@JsonProperty("value") String value)
      throws IllegalArgumentException {
    return fromValue(value);
  }

  public String toString() {
    return this._value_;
  }

  public Object readResolve() throws ObjectStreamException {
    return fromValue(this._value_);
  }
}
