package com.atlassian.fisc.erp.connector.domain.invoice;

import com.atlassian.fisc.erp.connector.domain.common.Address;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.Record;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Calendar;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Invoice extends Record implements Serializable {
  private Calendar createdDate;
  private Calendar lastModifiedDate;
  private RecordRef nexus;
  private RecordRef subsidiaryTaxRegNum;
  private Boolean taxRegOverride;
  private Boolean taxDetailsOverride;
  private RecordRef customForm;
  private RecordRef nextApprover;
  private RecordRef entity;
  private RecordRef billingAccount;
  private Boolean recurringBill;
  private Calendar tranDate;
  private String tranId;
  private RecordRef entityTaxRegNum;
  private Calendar taxPointDate;
  private String source;
  private RecordRef createdFrom;
  private RecordRef postingPeriod;
  private RecordRef opportunity;
  private RecordRef department;
  private RecordRef _class;
  private RecordRef terms;
  private RecordRef location;
  private RecordRef subsidiary;
  private RecordRef currency;
  private Calendar dueDate;
  private Calendar discountDate;
  private Double discountAmount;
  private RecordRef salesRep;
  private String contribPct;
  private RecordRef partner;
  private RecordRef leadSource;
  private Calendar startDate;
  private Calendar endDate;
  private String otherRefNum;
  private String memo;
  private Calendar salesEffectiveDate;
  private Boolean excludeCommission;
  private Double totalCostEstimate;
  private Double estGrossProfit;
  private Double estGrossProfitPercent;
  private RecordRef revRecSchedule;
  private Calendar revRecStartDate;
  private Calendar revRecEndDate;
  private Double amountPaid;
  private Double amountRemaining;
  private Double balance;

  @JsonProperty("account")
  private RecordRef account;

  private String onCreditHold;
  private Double exchangeRate;
  private String currencyName;
  private RecordRef promoCode;
  private RecordRef discountItem;
  private String discountRate;
  private Boolean isTaxable;
  private RecordRef taxItem;
  private Double taxRate;
  private Boolean toBePrinted;
  private Boolean toBeEmailed;
  private Boolean toBeFaxed;
  private String fax;
  private RecordRef messageSel;
  private String message;
  private Address billingAddress;
  private RecordRef billAddressList;
  private Address shippingAddress;
  private Boolean shipIsResidential;
  private RecordRef shipAddressList;
  private String fob;
  private Calendar shipDate;
  private RecordRef shipMethod;
  private Double shippingCost;
  private Double shippingTax1Rate;
  private String shippingTax2Rate;
  private RecordRef shippingTaxCode;
  private RecordRef handlingTaxCode;
  private Double handlingTax1Rate;
  private Double handlingCost;
  private String handlingTax2Rate;
  private String trackingNumbers;
  private String linkedTrackingNumbers;
  private RecordRef salesGroup;
  private Double subTotal;
  private Boolean canHaveStackable;
  private Double recognizedRevenue;
  private Double deferredRevenue;
  private Boolean revRecOnRevCommitment;
  private Boolean syncSalesTeams;
  private Double discountTotal;
  private Double taxTotal;
  private Double altShippingCost;
  private Double altHandlingCost;
  private Double total;
  private String status;
  private RecordRef job;
  private RecordRef billingSchedule;
  private String email;
  private Double tax2Total;
  private String vatRegNum;
  private RecordRef expCostDiscount;
  private RecordRef itemCostDiscount;
  private RecordRef timeDiscount;
  private String expCostDiscRate;
  private String itemCostDiscRate;
  private String timeDiscRate;
  private Double expCostDiscAmount;
  private Double expCostTaxRate1;
  private Double expCostTaxRate2;
  private Double itemCostDiscAmount;
  private RecordRef expCostTaxCode;
  private Double expCostDiscTax1Amt;
  private Double itemCostTaxRate1;
  private Double timeDiscAmount;
  private RecordRef itemCostTaxCode;
  private Boolean expCostDiscTaxable;
  private Boolean itemCostDiscTaxable;
  private Double itemCostTaxRate2;
  private Double itemCostDiscTax1Amt;
  private Boolean itemCostDiscPrint;
  private Boolean timeDiscTaxable;
  private Double timeTaxRate1;
  private Boolean expCostDiscPrint;
  private RecordRef timeTaxCode;
  private Boolean timeDiscPrint;
  private Double giftCertApplied;
  private Double timeDiscTax1Amt;
  private Boolean tranIsVsoeBundle;
  private Double timeTaxRate2;
  private Boolean vsoeAutoCalc;
  private Boolean syncPartnerTeams;
  private InvoiceItemList itemList;
  private RecordRef approvalStatus;
  private Boolean overrideInstallments;

  @JsonProperty("customFieldList")
  private CustomFieldList customFieldList;

  private String internalId;
  private String externalId;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public Invoice() {}

  public Invoice(
      String internalId,
      String externalId,
      Calendar createdDate,
      Calendar lastModifiedDate,
      RecordRef nexus,
      RecordRef subsidiaryTaxRegNum,
      Boolean taxRegOverride,
      Boolean taxDetailsOverride,
      RecordRef customForm,
      RecordRef nextApprover,
      RecordRef entity,
      RecordRef billingAccount,
      Boolean recurringBill,
      Calendar tranDate,
      String tranId,
      RecordRef entityTaxRegNum,
      String source,
      RecordRef createdFrom,
      RecordRef postingPeriod,
      RecordRef opportunity,
      RecordRef department,
      RecordRef _class,
      RecordRef terms,
      RecordRef location,
      RecordRef subsidiary,
      RecordRef currency,
      Calendar dueDate,
      Calendar discountDate,
      Double discountAmount,
      RecordRef salesRep,
      String contribPct,
      RecordRef partner,
      RecordRef leadSource,
      Calendar startDate,
      Calendar endDate,
      String otherRefNum,
      String memo,
      Calendar salesEffectiveDate,
      Boolean excludeCommission,
      Double totalCostEstimate,
      Double estGrossProfit,
      Double estGrossProfitPercent,
      RecordRef revRecSchedule,
      Calendar revRecStartDate,
      Calendar revRecEndDate,
      Double amountPaid,
      Double amountRemaining,
      Double balance,
      RecordRef account,
      String onCreditHold,
      Double exchangeRate,
      String currencyName,
      RecordRef promoCode,
      RecordRef discountItem,
      String discountRate,
      Boolean isTaxable,
      RecordRef taxItem,
      Double taxRate,
      Boolean toBePrinted,
      Boolean toBeEmailed,
      Boolean toBeFaxed,
      String fax,
      RecordRef messageSel,
      String message,
      Address billingAddress,
      RecordRef billAddressList,
      Address shippingAddress,
      Boolean shipIsResidential,
      RecordRef shipAddressList,
      String fob,
      Calendar shipDate,
      RecordRef shipMethod,
      Double shippingCost,
      Double shippingTax1Rate,
      String shippingTax2Rate,
      RecordRef shippingTaxCode,
      RecordRef handlingTaxCode,
      Double handlingTax1Rate,
      Double handlingCost,
      String handlingTax2Rate,
      String trackingNumbers,
      String linkedTrackingNumbers,
      RecordRef salesGroup,
      Double subTotal,
      Boolean canHaveStackable,
      Double recognizedRevenue,
      Double deferredRevenue,
      Boolean revRecOnRevCommitment,
      Boolean syncSalesTeams,
      Double discountTotal,
      Double taxTotal,
      Double altShippingCost,
      Double altHandlingCost,
      Double total,
      String status,
      RecordRef job,
      RecordRef billingSchedule,
      String email,
      Double tax2Total,
      String vatRegNum,
      RecordRef expCostDiscount,
      RecordRef itemCostDiscount,
      RecordRef timeDiscount,
      String expCostDiscRate,
      String itemCostDiscRate,
      String timeDiscRate,
      Double expCostDiscAmount,
      Double expCostTaxRate1,
      Double expCostTaxRate2,
      Double itemCostDiscAmount,
      RecordRef expCostTaxCode,
      Double expCostDiscTax1Amt,
      Double itemCostTaxRate1,
      Double timeDiscAmount,
      RecordRef itemCostTaxCode,
      Boolean expCostDiscTaxable,
      Boolean itemCostDiscTaxable,
      Double itemCostTaxRate2,
      Double itemCostDiscTax1Amt,
      Boolean itemCostDiscPrint,
      Boolean timeDiscTaxable,
      Double timeTaxRate1,
      Boolean expCostDiscPrint,
      RecordRef timeTaxCode,
      Boolean timeDiscPrint,
      Double giftCertApplied,
      Double timeDiscTax1Amt,
      Boolean tranIsVsoeBundle,
      Double timeTaxRate2,
      Boolean vsoeAutoCalc,
      Boolean syncPartnerTeams,
      CustomFieldList customFieldList) {
    this.internalId = internalId;
    this.externalId = externalId;
    this.createdDate = createdDate;
    this.lastModifiedDate = lastModifiedDate;
    this.nexus = nexus;
    this.subsidiaryTaxRegNum = subsidiaryTaxRegNum;
    this.taxRegOverride = taxRegOverride;
    this.taxDetailsOverride = taxDetailsOverride;
    this.customForm = customForm;
    this.nextApprover = nextApprover;
    this.entity = entity;
    this.billingAccount = billingAccount;
    this.recurringBill = recurringBill;
    this.tranDate = tranDate;
    this.tranId = tranId;
    this.entityTaxRegNum = entityTaxRegNum;
    this.source = source;
    this.createdFrom = createdFrom;
    this.postingPeriod = postingPeriod;
    this.opportunity = opportunity;
    this.department = department;
    this._class = _class;
    this.terms = terms;
    this.location = location;
    this.subsidiary = subsidiary;
    this.currency = currency;
    this.dueDate = dueDate;
    this.discountDate = discountDate;
    this.discountAmount = discountAmount;
    this.salesRep = salesRep;
    this.contribPct = contribPct;
    this.partner = partner;
    this.leadSource = leadSource;
    this.startDate = startDate;
    this.endDate = endDate;
    this.otherRefNum = otherRefNum;
    this.memo = memo;
    this.salesEffectiveDate = salesEffectiveDate;
    this.excludeCommission = excludeCommission;
    this.totalCostEstimate = totalCostEstimate;
    this.estGrossProfit = estGrossProfit;
    this.estGrossProfitPercent = estGrossProfitPercent;
    this.revRecSchedule = revRecSchedule;
    this.revRecStartDate = revRecStartDate;
    this.revRecEndDate = revRecEndDate;
    this.amountPaid = amountPaid;
    this.amountRemaining = amountRemaining;
    this.balance = balance;
    this.account = account;
    this.onCreditHold = onCreditHold;
    this.exchangeRate = exchangeRate;
    this.currencyName = currencyName;
    this.promoCode = promoCode;
    this.discountItem = discountItem;
    this.discountRate = discountRate;
    this.isTaxable = isTaxable;
    this.taxItem = taxItem;
    this.taxRate = taxRate;
    this.toBePrinted = toBePrinted;
    this.toBeEmailed = toBeEmailed;
    this.toBeFaxed = toBeFaxed;
    this.fax = fax;
    this.messageSel = messageSel;
    this.message = message;
    this.billingAddress = billingAddress;
    this.billAddressList = billAddressList;
    this.shippingAddress = shippingAddress;
    this.shipIsResidential = shipIsResidential;
    this.shipAddressList = shipAddressList;
    this.fob = fob;
    this.shipDate = shipDate;
    this.shipMethod = shipMethod;
    this.shippingCost = shippingCost;
    this.shippingTax1Rate = shippingTax1Rate;
    this.shippingTax2Rate = shippingTax2Rate;
    this.shippingTaxCode = shippingTaxCode;
    this.handlingTaxCode = handlingTaxCode;
    this.handlingTax1Rate = handlingTax1Rate;
    this.handlingCost = handlingCost;
    this.handlingTax2Rate = handlingTax2Rate;
    this.trackingNumbers = trackingNumbers;
    this.linkedTrackingNumbers = linkedTrackingNumbers;
    this.salesGroup = salesGroup;
    this.subTotal = subTotal;
    this.canHaveStackable = canHaveStackable;
    this.recognizedRevenue = recognizedRevenue;
    this.deferredRevenue = deferredRevenue;
    this.revRecOnRevCommitment = revRecOnRevCommitment;
    this.syncSalesTeams = syncSalesTeams;
    this.discountTotal = discountTotal;
    this.taxTotal = taxTotal;
    this.altShippingCost = altShippingCost;
    this.altHandlingCost = altHandlingCost;
    this.total = total;
    this.status = status;
    this.job = job;
    this.billingSchedule = billingSchedule;
    this.email = email;
    this.tax2Total = tax2Total;
    this.vatRegNum = vatRegNum;
    this.expCostDiscount = expCostDiscount;
    this.itemCostDiscount = itemCostDiscount;
    this.timeDiscount = timeDiscount;
    this.expCostDiscRate = expCostDiscRate;
    this.itemCostDiscRate = itemCostDiscRate;
    this.timeDiscRate = timeDiscRate;
    this.expCostDiscAmount = expCostDiscAmount;
    this.expCostTaxRate1 = expCostTaxRate1;
    this.expCostTaxRate2 = expCostTaxRate2;
    this.itemCostDiscAmount = itemCostDiscAmount;
    this.expCostTaxCode = expCostTaxCode;
    this.expCostDiscTax1Amt = expCostDiscTax1Amt;
    this.itemCostTaxRate1 = itemCostTaxRate1;
    this.timeDiscAmount = timeDiscAmount;
    this.itemCostTaxCode = itemCostTaxCode;
    this.expCostDiscTaxable = expCostDiscTaxable;
    this.itemCostDiscTaxable = itemCostDiscTaxable;
    this.itemCostTaxRate2 = itemCostTaxRate2;
    this.itemCostDiscTax1Amt = itemCostDiscTax1Amt;
    this.itemCostDiscPrint = itemCostDiscPrint;
    this.timeDiscTaxable = timeDiscTaxable;
    this.timeTaxRate1 = timeTaxRate1;
    this.expCostDiscPrint = expCostDiscPrint;
    this.timeTaxCode = timeTaxCode;
    this.timeDiscPrint = timeDiscPrint;
    this.giftCertApplied = giftCertApplied;
    this.timeDiscTax1Amt = timeDiscTax1Amt;
    this.tranIsVsoeBundle = tranIsVsoeBundle;
    this.timeTaxRate2 = timeTaxRate2;
    this.vsoeAutoCalc = vsoeAutoCalc;
    this.syncPartnerTeams = syncPartnerTeams;
    this.itemList = itemList;
    this.approvalStatus = approvalStatus;
    this.customFieldList = customFieldList;
  }

  public Calendar getCreatedDate() {
    return this.createdDate;
  }

  public void setCreatedDate(Calendar createdDate) {
    this.createdDate = createdDate;
  }

  public Calendar getLastModifiedDate() {
    return this.lastModifiedDate;
  }

  public void setLastModifiedDate(Calendar lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public RecordRef getNexus() {
    return this.nexus;
  }

  public void setNexus(RecordRef nexus) {
    this.nexus = nexus;
  }

  public RecordRef getSubsidiaryTaxRegNum() {
    return this.subsidiaryTaxRegNum;
  }

  public void setSubsidiaryTaxRegNum(RecordRef subsidiaryTaxRegNum) {
    this.subsidiaryTaxRegNum = subsidiaryTaxRegNum;
  }

  public Boolean getTaxRegOverride() {
    return this.taxRegOverride;
  }

  public void setTaxRegOverride(Boolean taxRegOverride) {
    this.taxRegOverride = taxRegOverride;
  }

  public Boolean getTaxDetailsOverride() {
    return this.taxDetailsOverride;
  }

  public void setTaxDetailsOverride(Boolean taxDetailsOverride) {
    this.taxDetailsOverride = taxDetailsOverride;
  }

  public RecordRef getCustomForm() {
    return this.customForm;
  }

  public void setCustomForm(RecordRef customForm) {
    this.customForm = customForm;
  }

  public RecordRef getNextApprover() {
    return this.nextApprover;
  }

  public void setNextApprover(RecordRef nextApprover) {
    this.nextApprover = nextApprover;
  }

  public RecordRef getEntity() {
    return this.entity;
  }

  public void setEntity(RecordRef entity) {
    this.entity = entity;
  }

  public RecordRef getBillingAccount() {
    return this.billingAccount;
  }

  public void setBillingAccount(RecordRef billingAccount) {
    this.billingAccount = billingAccount;
  }

  public Boolean getRecurringBill() {
    return this.recurringBill;
  }

  public void setRecurringBill(Boolean recurringBill) {
    this.recurringBill = recurringBill;
  }

  public Calendar getTranDate() {
    return this.tranDate;
  }

  public void setTranDate(Calendar tranDate) {
    this.tranDate = tranDate;
  }

  public String getTranId() {
    return this.tranId;
  }

  public void setTranId(String tranId) {
    this.tranId = tranId;
  }

  public RecordRef getEntityTaxRegNum() {
    return this.entityTaxRegNum;
  }

  public void setEntityTaxRegNum(RecordRef entityTaxRegNum) {
    this.entityTaxRegNum = entityTaxRegNum;
  }

  public String getSource() {
    return this.source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public RecordRef getCreatedFrom() {
    return this.createdFrom;
  }

  public void setCreatedFrom(RecordRef createdFrom) {
    this.createdFrom = createdFrom;
  }

  public RecordRef getPostingPeriod() {
    return this.postingPeriod;
  }

  public void setPostingPeriod(RecordRef postingPeriod) {
    this.postingPeriod = postingPeriod;
  }

  public RecordRef getOpportunity() {
    return this.opportunity;
  }

  public void setOpportunity(RecordRef opportunity) {
    this.opportunity = opportunity;
  }

  public RecordRef getDepartment() {
    return this.department;
  }

  public void setDepartment(RecordRef department) {
    this.department = department;
  }

  public RecordRef get_class() {
    return this._class;
  }

  public void set_class(RecordRef _class) {
    this._class = _class;
  }

  public RecordRef getTerms() {
    return this.terms;
  }

  public void setTerms(RecordRef terms) {
    this.terms = terms;
  }

  public RecordRef getLocation() {
    return this.location;
  }

  public void setLocation(RecordRef location) {
    this.location = location;
  }

  public RecordRef getSubsidiary() {
    return this.subsidiary;
  }

  public void setSubsidiary(RecordRef subsidiary) {
    this.subsidiary = subsidiary;
  }

  public RecordRef getCurrency() {
    return this.currency;
  }

  public void setCurrency(RecordRef currency) {
    this.currency = currency;
  }

  public Calendar getDueDate() {
    return this.dueDate;
  }

  public void setDueDate(Calendar dueDate) {
    this.dueDate = dueDate;
  }

  public Calendar getDiscountDate() {
    return this.discountDate;
  }

  public void setDiscountDate(Calendar discountDate) {
    this.discountDate = discountDate;
  }

  public Double getDiscountAmount() {
    return this.discountAmount;
  }

  public void setDiscountAmount(Double discountAmount) {
    this.discountAmount = discountAmount;
  }

  public RecordRef getSalesRep() {
    return this.salesRep;
  }

  public void setSalesRep(RecordRef salesRep) {
    this.salesRep = salesRep;
  }

  public String getContribPct() {
    return this.contribPct;
  }

  public void setContribPct(String contribPct) {
    this.contribPct = contribPct;
  }

  public RecordRef getPartner() {
    return this.partner;
  }

  public void setPartner(RecordRef partner) {
    this.partner = partner;
  }

  public RecordRef getLeadSource() {
    return this.leadSource;
  }

  public void setLeadSource(RecordRef leadSource) {
    this.leadSource = leadSource;
  }

  public Calendar getStartDate() {
    return this.startDate;
  }

  public void setStartDate(Calendar startDate) {
    this.startDate = startDate;
  }

  public Calendar getEndDate() {
    return this.endDate;
  }

  public void setEndDate(Calendar endDate) {
    this.endDate = endDate;
  }

  public String getOtherRefNum() {
    return this.otherRefNum;
  }

  public void setOtherRefNum(String otherRefNum) {
    this.otherRefNum = otherRefNum;
  }

  public String getMemo() {
    return this.memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public Calendar getSalesEffectiveDate() {
    return this.salesEffectiveDate;
  }

  public void setSalesEffectiveDate(Calendar salesEffectiveDate) {
    this.salesEffectiveDate = salesEffectiveDate;
  }

  public Boolean getExcludeCommission() {
    return this.excludeCommission;
  }

  public void setExcludeCommission(Boolean excludeCommission) {
    this.excludeCommission = excludeCommission;
  }

  public Double getTotalCostEstimate() {
    return this.totalCostEstimate;
  }

  public void setTotalCostEstimate(Double totalCostEstimate) {
    this.totalCostEstimate = totalCostEstimate;
  }

  public Double getEstGrossProfit() {
    return this.estGrossProfit;
  }

  public void setEstGrossProfit(Double estGrossProfit) {
    this.estGrossProfit = estGrossProfit;
  }

  public Double getEstGrossProfitPercent() {
    return this.estGrossProfitPercent;
  }

  public void setEstGrossProfitPercent(Double estGrossProfitPercent) {
    this.estGrossProfitPercent = estGrossProfitPercent;
  }

  public RecordRef getRevRecSchedule() {
    return this.revRecSchedule;
  }

  public void setRevRecSchedule(RecordRef revRecSchedule) {
    this.revRecSchedule = revRecSchedule;
  }

  public Calendar getRevRecStartDate() {
    return this.revRecStartDate;
  }

  public void setRevRecStartDate(Calendar revRecStartDate) {
    this.revRecStartDate = revRecStartDate;
  }

  public Calendar getRevRecEndDate() {
    return this.revRecEndDate;
  }

  public void setRevRecEndDate(Calendar revRecEndDate) {
    this.revRecEndDate = revRecEndDate;
  }

  public Double getAmountPaid() {
    return this.amountPaid;
  }

  public void setAmountPaid(Double amountPaid) {
    this.amountPaid = amountPaid;
  }

  public Double getAmountRemaining() {
    return this.amountRemaining;
  }

  public void setAmountRemaining(Double amountRemaining) {
    this.amountRemaining = amountRemaining;
  }

  public Double getBalance() {
    return this.balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public RecordRef getAccount() {
    return this.account;
  }

  public void setAccount(RecordRef account) {
    this.account = account;
  }

  public String getOnCreditHold() {
    return this.onCreditHold;
  }

  public void setOnCreditHold(String onCreditHold) {
    this.onCreditHold = onCreditHold;
  }

  public Double getExchangeRate() {
    return this.exchangeRate;
  }

  public void setExchangeRate(Double exchangeRate) {
    this.exchangeRate = exchangeRate;
  }

  public String getCurrencyName() {
    return this.currencyName;
  }

  public void setCurrencyName(String currencyName) {
    this.currencyName = currencyName;
  }

  public RecordRef getPromoCode() {
    return this.promoCode;
  }

  public void setPromoCode(RecordRef promoCode) {
    this.promoCode = promoCode;
  }

  public RecordRef getDiscountItem() {
    return this.discountItem;
  }

  public void setDiscountItem(RecordRef discountItem) {
    this.discountItem = discountItem;
  }

  public String getDiscountRate() {
    return this.discountRate;
  }

  public void setDiscountRate(String discountRate) {
    this.discountRate = discountRate;
  }

  public Boolean getIsTaxable() {
    return this.isTaxable;
  }

  public void setIsTaxable(Boolean isTaxable) {
    this.isTaxable = isTaxable;
  }

  public RecordRef getTaxItem() {
    return this.taxItem;
  }

  public void setTaxItem(RecordRef taxItem) {
    this.taxItem = taxItem;
  }

  public Double getTaxRate() {
    return this.taxRate;
  }

  public void setTaxRate(Double taxRate) {
    this.taxRate = taxRate;
  }

  public Boolean getToBePrinted() {
    return this.toBePrinted;
  }

  public void setToBePrinted(Boolean toBePrinted) {
    this.toBePrinted = toBePrinted;
  }

  public Boolean getToBeEmailed() {
    return this.toBeEmailed;
  }

  public void setToBeEmailed(Boolean toBeEmailed) {
    this.toBeEmailed = toBeEmailed;
  }

  public Boolean getToBeFaxed() {
    return this.toBeFaxed;
  }

  public void setToBeFaxed(Boolean toBeFaxed) {
    this.toBeFaxed = toBeFaxed;
  }

  public String getFax() {
    return this.fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public RecordRef getMessageSel() {
    return this.messageSel;
  }

  public void setMessageSel(RecordRef messageSel) {
    this.messageSel = messageSel;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Address getBillingAddress() {
    return this.billingAddress;
  }

  public void setBillingAddress(Address billingAddress) {
    this.billingAddress = billingAddress;
  }

  public RecordRef getBillAddressList() {
    return this.billAddressList;
  }

  public void setBillAddressList(RecordRef billAddressList) {
    this.billAddressList = billAddressList;
  }

  public Address getShippingAddress() {
    return this.shippingAddress;
  }

  public void setShippingAddress(Address shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public Boolean getShipIsResidential() {
    return this.shipIsResidential;
  }

  public void setShipIsResidential(Boolean shipIsResidential) {
    this.shipIsResidential = shipIsResidential;
  }

  public RecordRef getShipAddressList() {
    return this.shipAddressList;
  }

  public void setShipAddressList(RecordRef shipAddressList) {
    this.shipAddressList = shipAddressList;
  }

  public String getFob() {
    return this.fob;
  }

  public void setFob(String fob) {
    this.fob = fob;
  }

  public Calendar getShipDate() {
    return this.shipDate;
  }

  public void setShipDate(Calendar shipDate) {
    this.shipDate = shipDate;
  }

  public RecordRef getShipMethod() {
    return this.shipMethod;
  }

  public void setShipMethod(RecordRef shipMethod) {
    this.shipMethod = shipMethod;
  }

  public Double getShippingCost() {
    return this.shippingCost;
  }

  public void setShippingCost(Double shippingCost) {
    this.shippingCost = shippingCost;
  }

  public Double getShippingTax1Rate() {
    return this.shippingTax1Rate;
  }

  public void setShippingTax1Rate(Double shippingTax1Rate) {
    this.shippingTax1Rate = shippingTax1Rate;
  }

  public String getShippingTax2Rate() {
    return this.shippingTax2Rate;
  }

  public void setShippingTax2Rate(String shippingTax2Rate) {
    this.shippingTax2Rate = shippingTax2Rate;
  }

  public RecordRef getShippingTaxCode() {
    return this.shippingTaxCode;
  }

  public void setShippingTaxCode(RecordRef shippingTaxCode) {
    this.shippingTaxCode = shippingTaxCode;
  }

  public RecordRef getHandlingTaxCode() {
    return this.handlingTaxCode;
  }

  public void setHandlingTaxCode(RecordRef handlingTaxCode) {
    this.handlingTaxCode = handlingTaxCode;
  }

  public Double getHandlingTax1Rate() {
    return this.handlingTax1Rate;
  }

  public void setHandlingTax1Rate(Double handlingTax1Rate) {
    this.handlingTax1Rate = handlingTax1Rate;
  }

  public Double getHandlingCost() {
    return this.handlingCost;
  }

  public void setHandlingCost(Double handlingCost) {
    this.handlingCost = handlingCost;
  }

  public String getHandlingTax2Rate() {
    return this.handlingTax2Rate;
  }

  public void setHandlingTax2Rate(String handlingTax2Rate) {
    this.handlingTax2Rate = handlingTax2Rate;
  }

  public String getTrackingNumbers() {
    return this.trackingNumbers;
  }

  public void setTrackingNumbers(String trackingNumbers) {
    this.trackingNumbers = trackingNumbers;
  }

  public String getLinkedTrackingNumbers() {
    return this.linkedTrackingNumbers;
  }

  public void setLinkedTrackingNumbers(String linkedTrackingNumbers) {
    this.linkedTrackingNumbers = linkedTrackingNumbers;
  }

  public RecordRef getSalesGroup() {
    return this.salesGroup;
  }

  public void setSalesGroup(RecordRef salesGroup) {
    this.salesGroup = salesGroup;
  }

  public Double getSubTotal() {
    return this.subTotal;
  }

  public void setSubTotal(Double subTotal) {
    this.subTotal = subTotal;
  }

  public Boolean getCanHaveStackable() {
    return this.canHaveStackable;
  }

  public void setCanHaveStackable(Boolean canHaveStackable) {
    this.canHaveStackable = canHaveStackable;
  }

  public Double getRecognizedRevenue() {
    return this.recognizedRevenue;
  }

  public void setRecognizedRevenue(Double recognizedRevenue) {
    this.recognizedRevenue = recognizedRevenue;
  }

  public Double getDeferredRevenue() {
    return this.deferredRevenue;
  }

  public void setDeferredRevenue(Double deferredRevenue) {
    this.deferredRevenue = deferredRevenue;
  }

  public Boolean getRevRecOnRevCommitment() {
    return this.revRecOnRevCommitment;
  }

  public void setRevRecOnRevCommitment(Boolean revRecOnRevCommitment) {
    this.revRecOnRevCommitment = revRecOnRevCommitment;
  }

  public Boolean getSyncSalesTeams() {
    return this.syncSalesTeams;
  }

  public void setSyncSalesTeams(Boolean syncSalesTeams) {
    this.syncSalesTeams = syncSalesTeams;
  }

  public Double getDiscountTotal() {
    return this.discountTotal;
  }

  public void setDiscountTotal(Double discountTotal) {
    this.discountTotal = discountTotal;
  }

  public Double getTaxTotal() {
    return this.taxTotal;
  }

  public void setTaxTotal(Double taxTotal) {
    this.taxTotal = taxTotal;
  }

  public Double getAltShippingCost() {
    return this.altShippingCost;
  }

  public void setAltShippingCost(Double altShippingCost) {
    this.altShippingCost = altShippingCost;
  }

  public Double getAltHandlingCost() {
    return this.altHandlingCost;
  }

  public void setAltHandlingCost(Double altHandlingCost) {
    this.altHandlingCost = altHandlingCost;
  }

  public Double getTotal() {
    return this.total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public RecordRef getJob() {
    return this.job;
  }

  public void setJob(RecordRef job) {
    this.job = job;
  }

  public RecordRef getBillingSchedule() {
    return this.billingSchedule;
  }

  public void setBillingSchedule(RecordRef billingSchedule) {
    this.billingSchedule = billingSchedule;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Double getTax2Total() {
    return this.tax2Total;
  }

  public void setTax2Total(Double tax2Total) {
    this.tax2Total = tax2Total;
  }

  public String getVatRegNum() {
    return this.vatRegNum;
  }

  public void setVatRegNum(String vatRegNum) {
    this.vatRegNum = vatRegNum;
  }

  public RecordRef getExpCostDiscount() {
    return this.expCostDiscount;
  }

  public void setExpCostDiscount(RecordRef expCostDiscount) {
    this.expCostDiscount = expCostDiscount;
  }

  public RecordRef getItemCostDiscount() {
    return this.itemCostDiscount;
  }

  public void setItemCostDiscount(RecordRef itemCostDiscount) {
    this.itemCostDiscount = itemCostDiscount;
  }

  public RecordRef getTimeDiscount() {
    return this.timeDiscount;
  }

  public void setTimeDiscount(RecordRef timeDiscount) {
    this.timeDiscount = timeDiscount;
  }

  public String getExpCostDiscRate() {
    return this.expCostDiscRate;
  }

  public void setExpCostDiscRate(String expCostDiscRate) {
    this.expCostDiscRate = expCostDiscRate;
  }

  public String getItemCostDiscRate() {
    return this.itemCostDiscRate;
  }

  public void setItemCostDiscRate(String itemCostDiscRate) {
    this.itemCostDiscRate = itemCostDiscRate;
  }

  public String getTimeDiscRate() {
    return this.timeDiscRate;
  }

  public void setTimeDiscRate(String timeDiscRate) {
    this.timeDiscRate = timeDiscRate;
  }

  public Double getExpCostDiscAmount() {
    return this.expCostDiscAmount;
  }

  public void setExpCostDiscAmount(Double expCostDiscAmount) {
    this.expCostDiscAmount = expCostDiscAmount;
  }

  public Double getExpCostTaxRate1() {
    return this.expCostTaxRate1;
  }

  public void setExpCostTaxRate1(Double expCostTaxRate1) {
    this.expCostTaxRate1 = expCostTaxRate1;
  }

  public Double getExpCostTaxRate2() {
    return this.expCostTaxRate2;
  }

  public void setExpCostTaxRate2(Double expCostTaxRate2) {
    this.expCostTaxRate2 = expCostTaxRate2;
  }

  public Double getItemCostDiscAmount() {
    return this.itemCostDiscAmount;
  }

  public void setItemCostDiscAmount(Double itemCostDiscAmount) {
    this.itemCostDiscAmount = itemCostDiscAmount;
  }

  public RecordRef getExpCostTaxCode() {
    return this.expCostTaxCode;
  }

  public void setExpCostTaxCode(RecordRef expCostTaxCode) {
    this.expCostTaxCode = expCostTaxCode;
  }

  public Double getExpCostDiscTax1Amt() {
    return this.expCostDiscTax1Amt;
  }

  public void setExpCostDiscTax1Amt(Double expCostDiscTax1Amt) {
    this.expCostDiscTax1Amt = expCostDiscTax1Amt;
  }

  public Double getItemCostTaxRate1() {
    return this.itemCostTaxRate1;
  }

  public void setItemCostTaxRate1(Double itemCostTaxRate1) {
    this.itemCostTaxRate1 = itemCostTaxRate1;
  }

  public Double getTimeDiscAmount() {
    return this.timeDiscAmount;
  }

  public void setTimeDiscAmount(Double timeDiscAmount) {
    this.timeDiscAmount = timeDiscAmount;
  }

  public RecordRef getItemCostTaxCode() {
    return this.itemCostTaxCode;
  }

  public void setItemCostTaxCode(RecordRef itemCostTaxCode) {
    this.itemCostTaxCode = itemCostTaxCode;
  }

  public Boolean getExpCostDiscTaxable() {
    return this.expCostDiscTaxable;
  }

  public void setExpCostDiscTaxable(Boolean expCostDiscTaxable) {
    this.expCostDiscTaxable = expCostDiscTaxable;
  }

  public Boolean getItemCostDiscTaxable() {
    return this.itemCostDiscTaxable;
  }

  public void setItemCostDiscTaxable(Boolean itemCostDiscTaxable) {
    this.itemCostDiscTaxable = itemCostDiscTaxable;
  }

  public Double getItemCostTaxRate2() {
    return this.itemCostTaxRate2;
  }

  public void setItemCostTaxRate2(Double itemCostTaxRate2) {
    this.itemCostTaxRate2 = itemCostTaxRate2;
  }

  public Double getItemCostDiscTax1Amt() {
    return this.itemCostDiscTax1Amt;
  }

  public void setItemCostDiscTax1Amt(Double itemCostDiscTax1Amt) {
    this.itemCostDiscTax1Amt = itemCostDiscTax1Amt;
  }

  public Boolean getItemCostDiscPrint() {
    return this.itemCostDiscPrint;
  }

  public void setItemCostDiscPrint(Boolean itemCostDiscPrint) {
    this.itemCostDiscPrint = itemCostDiscPrint;
  }

  public Boolean getTimeDiscTaxable() {
    return this.timeDiscTaxable;
  }

  public void setTimeDiscTaxable(Boolean timeDiscTaxable) {
    this.timeDiscTaxable = timeDiscTaxable;
  }

  public Double getTimeTaxRate1() {
    return this.timeTaxRate1;
  }

  public void setTimeTaxRate1(Double timeTaxRate1) {
    this.timeTaxRate1 = timeTaxRate1;
  }

  public Boolean getExpCostDiscPrint() {
    return this.expCostDiscPrint;
  }

  public void setExpCostDiscPrint(Boolean expCostDiscPrint) {
    this.expCostDiscPrint = expCostDiscPrint;
  }

  public RecordRef getTimeTaxCode() {
    return this.timeTaxCode;
  }

  public void setTimeTaxCode(RecordRef timeTaxCode) {
    this.timeTaxCode = timeTaxCode;
  }

  public Boolean getTimeDiscPrint() {
    return this.timeDiscPrint;
  }

  public void setTimeDiscPrint(Boolean timeDiscPrint) {
    this.timeDiscPrint = timeDiscPrint;
  }

  public Double getGiftCertApplied() {
    return this.giftCertApplied;
  }

  public void setGiftCertApplied(Double giftCertApplied) {
    this.giftCertApplied = giftCertApplied;
  }

  public Double getTimeDiscTax1Amt() {
    return this.timeDiscTax1Amt;
  }

  public void setTimeDiscTax1Amt(Double timeDiscTax1Amt) {
    this.timeDiscTax1Amt = timeDiscTax1Amt;
  }

  public Boolean getTranIsVsoeBundle() {
    return this.tranIsVsoeBundle;
  }

  public void setTranIsVsoeBundle(Boolean tranIsVsoeBundle) {
    this.tranIsVsoeBundle = tranIsVsoeBundle;
  }

  public Double getTimeTaxRate2() {
    return this.timeTaxRate2;
  }

  public void setTimeTaxRate2(Double timeTaxRate2) {
    this.timeTaxRate2 = timeTaxRate2;
  }

  public Boolean getVsoeAutoCalc() {
    return this.vsoeAutoCalc;
  }

  public void setVsoeAutoCalc(Boolean vsoeAutoCalc) {
    this.vsoeAutoCalc = vsoeAutoCalc;
  }

  public Boolean getSyncPartnerTeams() {
    return this.syncPartnerTeams;
  }

  public void setSyncPartnerTeams(Boolean syncPartnerTeams) {
    this.syncPartnerTeams = syncPartnerTeams;
  }

  public InvoiceItemList getItemList() {
    return this.itemList;
  }

  public void setItemList(InvoiceItemList itemList) {
    this.itemList = itemList;
  }

  public RecordRef getApprovalStatus() {
    return this.approvalStatus;
  }

  public void setApprovalStatus(RecordRef approvalStatus) {
    this.approvalStatus = approvalStatus;
  }

  public CustomFieldList getCustomFieldList() {
    return this.customFieldList;
  }

  public void setCustomFieldList(CustomFieldList customFieldList) {
    this.customFieldList = customFieldList;
  }

  public String getInternalId() {
    return this.internalId;
  }

  public void setInternalId(String internalId) {
    this.internalId = internalId;
  }

  public String getExternalId() {
    return this.externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }

}
