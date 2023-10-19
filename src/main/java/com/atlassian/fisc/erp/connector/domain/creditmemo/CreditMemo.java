package com.atlassian.fisc.erp.connector.domain.creditmemo;

import com.atlassian.fisc.erp.connector.domain.common.Address;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.Record;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Calendar;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditMemo extends Record implements Serializable {

  private Calendar createdDate;
  private Calendar lastModifiedDate;
  private RecordRef nexus;
  private RecordRef subsidiaryTaxRegNum;
  private Boolean taxRegOverride;
  private Boolean taxDetailsOverride;
  private RecordRef customForm;
  private RecordRef currency;
  private RecordRef entity;
  private String vatRegNum;
  private Calendar tranDate;
  private String tranId;
  private RecordRef entityTaxRegNum;
  private RecordRef createdFrom;
  private RecordRef postingPeriod;
  private RecordRef department;
  private RecordRef _class;
  private RecordRef location;
  private RecordRef subsidiary;
  private RecordRef job;
  private RecordRef salesRep;
  private RecordRef partner;
  private String contribPct;
  private String otherRefNum;
  private String memo;
  private Boolean excludeCommission;
  private RecordRef leadSource;
  private Double balance;
  private RecordRef account;
  private Double exchangeRate;
  private String onCreditHold;
  private Double amountPaid;
  private Calendar salesEffectiveDate;
  private Double totalCostEstimate;
  private Double estGrossProfit;
  private Double estGrossProfitPercent;
  private String currencyName;
  private RecordRef promoCode;
  private Double amountRemaining;
  private RecordRef discountItem;
  private String source;
  private String discountRate;
  private Boolean isTaxable;
  private RecordRef taxItem;
  private Double taxRate;
  private Double unapplied;
  private Boolean autoApply;
  private Double applied;
  private Boolean toBePrinted;
  private Boolean toBeEmailed;
  private String email;
  private Boolean toBeFaxed;
  private String fax;
  private RecordRef messageSel;
  private String message;
  private Address billingAddress;
  private RecordRef billAddressList;
  private RecordRef shipMethod;
  private Double shippingCost;
  private Double shippingTax1Rate;
  private RecordRef shippingTaxCode;
  private RecordRef handlingTaxCode;
  private String shippingTax2Rate;
  private Double handlingTax1Rate;
  private String handlingTax2Rate;
  private Double handlingCost;
  private Double subTotal;
  private Double discountTotal;
  private Double recognizedRevenue;
  private Double deferredRevenue;
  private Boolean revRecOnRevCommitment;
  private Double taxTotal;
  private Double tax2Total;
  private Double altShippingCost;
  private Double altHandlingCost;
  private Boolean isMultiShipTo;
  private Double total;
  private RecordRef salesGroup;
  private Boolean syncSalesTeams;
  private String status;
  private RecordRef giftCert;
  private Double giftCertTotal;
  private Double giftCertApplied;
  private Double giftCertAvailable;
  private Boolean tranIsVsoeBundle;
  private Boolean vsoeAutoCalc;
  private Boolean syncPartnerTeams;
  private CreditMemoItemList itemList;
  private CreditMemoApplyList applyList;
  private CustomFieldList customFieldList;
  private String internalId;
  private String externalId;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public CreditMemo() {}

  public CreditMemo(
      String internalId,
      String externalId,
      Calendar createdDate,
      Calendar lastModifiedDate,
      RecordRef nexus,
      RecordRef subsidiaryTaxRegNum,
      Boolean taxRegOverride,
      Boolean taxDetailsOverride,
      RecordRef customForm,
      RecordRef currency,
      RecordRef entity,
      String vatRegNum,
      Calendar tranDate,
      String tranId,
      RecordRef entityTaxRegNum,
      RecordRef createdFrom,
      RecordRef postingPeriod,
      RecordRef department,
      RecordRef _class,
      RecordRef location,
      RecordRef subsidiary,
      RecordRef job,
      RecordRef salesRep,
      RecordRef partner,
      String contribPct,
      String otherRefNum,
      String memo,
      Boolean excludeCommission,
      RecordRef leadSource,
      Double balance,
      RecordRef account,
      Double exchangeRate,
      String onCreditHold,
      Double amountPaid,
      Calendar salesEffectiveDate,
      Double totalCostEstimate,
      Double estGrossProfit,
      Double estGrossProfitPercent,
      String currencyName,
      RecordRef promoCode,
      Double amountRemaining,
      RecordRef discountItem,
      String source,
      String discountRate,
      Boolean isTaxable,
      RecordRef taxItem,
      Double taxRate,
      Double unapplied,
      Boolean autoApply,
      Double applied,
      Boolean toBePrinted,
      Boolean toBeEmailed,
      String email,
      Boolean toBeFaxed,
      String fax,
      RecordRef messageSel,
      String message,
      Address billingAddress,
      RecordRef billAddressList,
      RecordRef shipMethod,
      Double shippingCost,
      Double shippingTax1Rate,
      RecordRef shippingTaxCode,
      RecordRef handlingTaxCode,
      String shippingTax2Rate,
      Double handlingTax1Rate,
      String handlingTax2Rate,
      Double handlingCost,
      Double subTotal,
      Double discountTotal,
      Double recognizedRevenue,
      Double deferredRevenue,
      Boolean revRecOnRevCommitment,
      Double taxTotal,
      Double tax2Total,
      Double altShippingCost,
      Double altHandlingCost,
      Boolean isMultiShipTo,
      Double total,
      RecordRef salesGroup,
      Boolean syncSalesTeams,
      String status,
      RecordRef giftCert,
      Double giftCertTotal,
      Double giftCertApplied,
      Double giftCertAvailable,
      Boolean tranIsVsoeBundle,
      Boolean vsoeAutoCalc,
      Boolean syncPartnerTeams,
      CreditMemoItemList itemList,
      CreditMemoApplyList applyList,
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
    this.currency = currency;
    this.entity = entity;
    this.vatRegNum = vatRegNum;
    this.tranDate = tranDate;
    this.tranId = tranId;
    this.entityTaxRegNum = entityTaxRegNum;
    this.createdFrom = createdFrom;
    this.postingPeriod = postingPeriod;
    this.department = department;
    this._class = _class;
    this.location = location;
    this.subsidiary = subsidiary;
    this.job = job;
    this.salesRep = salesRep;
    this.partner = partner;
    this.contribPct = contribPct;
    this.otherRefNum = otherRefNum;
    this.memo = memo;
    this.excludeCommission = excludeCommission;
    this.leadSource = leadSource;
    this.balance = balance;
    this.account = account;
    this.exchangeRate = exchangeRate;
    this.onCreditHold = onCreditHold;
    this.amountPaid = amountPaid;
    this.salesEffectiveDate = salesEffectiveDate;
    this.totalCostEstimate = totalCostEstimate;
    this.estGrossProfit = estGrossProfit;
    this.estGrossProfitPercent = estGrossProfitPercent;
    this.currencyName = currencyName;
    this.promoCode = promoCode;
    this.amountRemaining = amountRemaining;
    this.discountItem = discountItem;
    this.source = source;
    this.discountRate = discountRate;
    this.isTaxable = isTaxable;
    this.taxItem = taxItem;
    this.taxRate = taxRate;
    this.unapplied = unapplied;
    this.autoApply = autoApply;
    this.applied = applied;
    this.toBePrinted = toBePrinted;
    this.toBeEmailed = toBeEmailed;
    this.email = email;
    this.toBeFaxed = toBeFaxed;
    this.fax = fax;
    this.messageSel = messageSel;
    this.message = message;
    this.billingAddress = billingAddress;
    this.billAddressList = billAddressList;
    this.shipMethod = shipMethod;
    this.shippingCost = shippingCost;
    this.shippingTax1Rate = shippingTax1Rate;
    this.shippingTaxCode = shippingTaxCode;
    this.handlingTaxCode = handlingTaxCode;
    this.shippingTax2Rate = shippingTax2Rate;
    this.handlingTax1Rate = handlingTax1Rate;
    this.handlingTax2Rate = handlingTax2Rate;
    this.handlingCost = handlingCost;
    this.subTotal = subTotal;
    this.discountTotal = discountTotal;
    this.recognizedRevenue = recognizedRevenue;
    this.deferredRevenue = deferredRevenue;
    this.revRecOnRevCommitment = revRecOnRevCommitment;
    this.taxTotal = taxTotal;
    this.tax2Total = tax2Total;
    this.altShippingCost = altShippingCost;
    this.altHandlingCost = altHandlingCost;
    this.isMultiShipTo = isMultiShipTo;
    this.total = total;
    this.salesGroup = salesGroup;
    this.syncSalesTeams = syncSalesTeams;
    this.status = status;
    this.giftCert = giftCert;
    this.giftCertTotal = giftCertTotal;
    this.giftCertApplied = giftCertApplied;
    this.giftCertAvailable = giftCertAvailable;
    this.tranIsVsoeBundle = tranIsVsoeBundle;
    this.vsoeAutoCalc = vsoeAutoCalc;
    this.syncPartnerTeams = syncPartnerTeams;
    this.itemList = itemList;
    this.applyList = applyList;
    this.customFieldList = customFieldList;
  }

  public Calendar getCreatedDate() {
    return this.createdDate;
  }

  public void setCreatedDate(Calendar createdDate) {
    this.createdDate = createdDate;
  }

  public RecordRef getNexus() {
    return this.nexus;
  }

  public void setNexus(RecordRef nexus) {
    this.nexus = nexus;
  }

  public Calendar getLastModifiedDate() {
    return this.lastModifiedDate;
  }

  public void setLastModifiedDate(Calendar lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public Boolean getTaxRegOverride() {
    return this.taxRegOverride;
  }

  public void setTaxRegOverride(Boolean taxRegOverride) {
    this.taxRegOverride = taxRegOverride;
  }

  public RecordRef getSubsidiaryTaxRegNum() {
    return this.subsidiaryTaxRegNum;
  }

  public void setSubsidiaryTaxRegNum(RecordRef subsidiaryTaxRegNum) {
    this.subsidiaryTaxRegNum = subsidiaryTaxRegNum;
  }

  public RecordRef getCustomForm() {
    return this.customForm;
  }

  public void setCustomForm(RecordRef customForm) {
    this.customForm = customForm;
  }

  public RecordRef getCurrency() {
    return this.currency;
  }

  public void setCurrency(RecordRef currency) {
    this.currency = currency;
  }

  public RecordRef getEntity() {
    return this.entity;
  }

  public void setEntity(RecordRef entity) {
    this.entity = entity;
  }

  public Boolean getTaxDetailsOverride() {
    return this.taxDetailsOverride;
  }

  public void setTaxDetailsOverride(Boolean taxDetailsOverride) {
    this.taxDetailsOverride = taxDetailsOverride;
  }

  public String getVatRegNum() {
    return this.vatRegNum;
  }

  public void setVatRegNum(String vatRegNum) {
    this.vatRegNum = vatRegNum;
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

  public Calendar getTranDate() {
    return this.tranDate;
  }

  public void setTranDate(Calendar tranDate) {
    this.tranDate = tranDate;
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

  public RecordRef get_class() {
    return this._class;
  }

  public void set_class(RecordRef _class) {
    this._class = _class;
  }

  public RecordRef getDepartment() {
    return this.department;
  }

  public void setDepartment(RecordRef department) {
    this.department = department;
  }

  public RecordRef getSubsidiary() {
    return this.subsidiary;
  }

  public void setSubsidiary(RecordRef subsidiary) {
    this.subsidiary = subsidiary;
  }

  public RecordRef getJob() {
    return this.job;
  }

  public void setJob(RecordRef job) {
    this.job = job;
  }

  public RecordRef getLocation() {
    return this.location;
  }

  public void setLocation(RecordRef location) {
    this.location = location;
  }

  public RecordRef getSalesRep() {
    return this.salesRep;
  }

  public void setSalesRep(RecordRef salesRep) {
    this.salesRep = salesRep;
  }

  public RecordRef getPartner() {
    return this.partner;
  }

  public void setPartner(RecordRef partner) {
    this.partner = partner;
  }

  public String getContribPct() {
    return this.contribPct;
  }

  public void setContribPct(String contribPct) {
    this.contribPct = contribPct;
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

  public Boolean getExcludeCommission() {
    return this.excludeCommission;
  }

  public void setExcludeCommission(Boolean excludeCommission) {
    this.excludeCommission = excludeCommission;
  }

  public Double getEstGrossProfit() {
    return this.estGrossProfit;
  }

  public void setEstGrossProfit(Double estGrossProfit) {
    this.estGrossProfit = estGrossProfit;
  }

  public RecordRef getLeadSource() {
    return this.leadSource;
  }

  public void setLeadSource(RecordRef leadSource) {
    this.leadSource = leadSource;
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

  public Double getExchangeRate() {
    return this.exchangeRate;
  }

  public void setExchangeRate(Double exchangeRate) {
    this.exchangeRate = exchangeRate;
  }

  public String getOnCreditHold() {
    return this.onCreditHold;
  }

  public void setOnCreditHold(String onCreditHold) {
    this.onCreditHold = onCreditHold;
  }

  public Double getAmountPaid() {
    return this.amountPaid;
  }

  public void setAmountPaid(Double amountPaid) {
    this.amountPaid = amountPaid;
  }

  public Calendar getSalesEffectiveDate() {
    return this.salesEffectiveDate;
  }

  public void setSalesEffectiveDate(Calendar salesEffectiveDate) {
    this.salesEffectiveDate = salesEffectiveDate;
  }

  public Double getTotalCostEstimate() {
    return this.totalCostEstimate;
  }

  public void setTotalCostEstimate(Double totalCostEstimate) {
    this.totalCostEstimate = totalCostEstimate;
  }

  public Double getEstGrossProfitPercent() {
    return this.estGrossProfitPercent;
  }

  public void setEstGrossProfitPercent(Double estGrossProfitPercent) {
    this.estGrossProfitPercent = estGrossProfitPercent;
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

  public Double getAmountRemaining() {
    return this.amountRemaining;
  }

  public void setAmountRemaining(Double amountRemaining) {
    this.amountRemaining = amountRemaining;
  }

  public RecordRef getDiscountItem() {
    return this.discountItem;
  }

  public void setDiscountItem(RecordRef discountItem) {
    this.discountItem = discountItem;
  }

  public String getSource() {
    return this.source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getDiscountRate() {
    return this.discountRate;
  }

  public void setDiscountRate(String discountRate) {
    this.discountRate = discountRate;
  }

  public RecordRef getTaxItem() {
    return this.taxItem;
  }

  public void setTaxItem(RecordRef taxItem) {
    this.taxItem = taxItem;
  }

  public Boolean getIsTaxable() {
    return this.isTaxable;
  }

  public void setIsTaxable(Boolean isTaxable) {
    this.isTaxable = isTaxable;
  }

  public Double getUnapplied() {
    return this.unapplied;
  }

  public void setUnapplied(Double unapplied) {
    this.unapplied = unapplied;
  }

  public Double getTaxRate() {
    return this.taxRate;
  }

  public void setTaxRate(Double taxRate) {
    this.taxRate = taxRate;
  }

  public Boolean getAutoApply() {
    return this.autoApply;
  }

  public void setAutoApply(Boolean autoApply) {
    this.autoApply = autoApply;
  }

  public Double getApplied() {
    return this.applied;
  }

  public void setApplied(Double applied) {
    this.applied = applied;
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

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getToBeFaxed() {
    return this.toBeFaxed;
  }

  public void setToBeFaxed(Boolean toBeFaxed) {
    this.toBeFaxed = toBeFaxed;
  }

  public RecordRef getMessageSel() {
    return this.messageSel;
  }

  public void setMessageSel(RecordRef messageSel) {
    this.messageSel = messageSel;
  }

  public String getFax() {
    return this.fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public Address getBillingAddress() {
    return this.billingAddress;
  }

  public void setBillingAddress(Address billingAddress) {
    this.billingAddress = billingAddress;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public RecordRef getShipMethod() {
    return this.shipMethod;
  }

  public void setShipMethod(RecordRef shipMethod) {
    this.shipMethod = shipMethod;
  }

  public RecordRef getBillAddressList() {
    return this.billAddressList;
  }

  public void setBillAddressList(RecordRef billAddressList) {
    this.billAddressList = billAddressList;
  }

  public Double getShippingTax1Rate() {
    return this.shippingTax1Rate;
  }

  public void setShippingTax1Rate(Double shippingTax1Rate) {
    this.shippingTax1Rate = shippingTax1Rate;
  }

  public Double getShippingCost() {
    return this.shippingCost;
  }

  public void setShippingCost(Double shippingCost) {
    this.shippingCost = shippingCost;
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

  public String getShippingTax2Rate() {
    return this.shippingTax2Rate;
  }

  public void setShippingTax2Rate(String shippingTax2Rate) {
    this.shippingTax2Rate = shippingTax2Rate;
  }

  public Double getHandlingTax1Rate() {
    return this.handlingTax1Rate;
  }

  public void setHandlingTax1Rate(Double handlingTax1Rate) {
    this.handlingTax1Rate = handlingTax1Rate;
  }

  public String getHandlingTax2Rate() {
    return this.handlingTax2Rate;
  }

  public void setHandlingTax2Rate(String handlingTax2Rate) {
    this.handlingTax2Rate = handlingTax2Rate;
  }

  public Double getHandlingCost() {
    return this.handlingCost;
  }

  public void setHandlingCost(Double handlingCost) {
    this.handlingCost = handlingCost;
  }

  public Double getSubTotal() {
    return this.subTotal;
  }

  public void setSubTotal(Double subTotal) {
    this.subTotal = subTotal;
  }

  public Double getDiscountTotal() {
    return this.discountTotal;
  }

  public void setDiscountTotal(Double discountTotal) {
    this.discountTotal = discountTotal;
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

  public Double getTaxTotal() {
    return this.taxTotal;
  }

  public void setTaxTotal(Double taxTotal) {
    this.taxTotal = taxTotal;
  }

  public Boolean getRevRecOnRevCommitment() {
    return this.revRecOnRevCommitment;
  }

  public void setRevRecOnRevCommitment(Boolean revRecOnRevCommitment) {
    this.revRecOnRevCommitment = revRecOnRevCommitment;
  }

  public Double getTax2Total() {
    return this.tax2Total;
  }

  public void setTax2Total(Double tax2Total) {
    this.tax2Total = tax2Total;
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

  public Boolean getIsMultiShipTo() {
    return this.isMultiShipTo;
  }

  public void setIsMultiShipTo(Boolean isMultiShipTo) {
    this.isMultiShipTo = isMultiShipTo;
  }

  public Double getTotal() {
    return this.total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public RecordRef getSalesGroup() {
    return this.salesGroup;
  }

  public void setSalesGroup(RecordRef salesGroup) {
    this.salesGroup = salesGroup;
  }

  public Boolean getSyncSalesTeams() {
    return this.syncSalesTeams;
  }

  public void setSyncSalesTeams(Boolean syncSalesTeams) {
    this.syncSalesTeams = syncSalesTeams;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public RecordRef getGiftCert() {
    return this.giftCert;
  }

  public void setGiftCert(RecordRef giftCert) {
    this.giftCert = giftCert;
  }

  public Double getGiftCertTotal() {
    return this.giftCertTotal;
  }

  public void setGiftCertTotal(Double giftCertTotal) {
    this.giftCertTotal = giftCertTotal;
  }

  public Double getGiftCertApplied() {
    return this.giftCertApplied;
  }

  public void setGiftCertApplied(Double giftCertApplied) {
    this.giftCertApplied = giftCertApplied;
  }

  public Double getGiftCertAvailable() {
    return this.giftCertAvailable;
  }

  public void setGiftCertAvailable(Double giftCertAvailable) {
    this.giftCertAvailable = giftCertAvailable;
  }

  public Boolean getTranIsVsoeBundle() {
    return this.tranIsVsoeBundle;
  }

  public void setTranIsVsoeBundle(Boolean tranIsVsoeBundle) {
    this.tranIsVsoeBundle = tranIsVsoeBundle;
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

  public CreditMemoItemList getItemList() {
    return this.itemList;
  }

  public void setItemList(CreditMemoItemList itemList) {
    this.itemList = itemList;
  }

  public String getExternalId() {
    return this.externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }

  public CreditMemoApplyList getApplyList() {
    return this.applyList;
  }

  public void setApplyList(CreditMemoApplyList applyList) {
    this.applyList = applyList;
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

}
