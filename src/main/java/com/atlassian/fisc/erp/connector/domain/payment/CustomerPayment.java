package com.atlassian.fisc.erp.connector.domain.payment;

import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.Record;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Calendar;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerPayment extends Record implements Serializable {

  private Calendar createdDate;
  private Calendar lastModifiedDate;
  private RecordRef customForm;
  private RecordRef arAcct;
  private RecordRef customer;
  private Double balance;
  private Double pending;
  private RecordRef currency;
  private Double payment;
  private Boolean autoApply;
  private Calendar tranDate;
  private String tranId;
  private RecordRef postingPeriod;
  private RecordRef paymentMethod;
  private Boolean ccIsPurchaseCardBin;
  private String memo;
  private Boolean ccProcessAsPurchaseCard;
  private String checkNum;
  private String currencyName;
  private Double exchangeRate;
  private RecordRef creditCard;
  private Boolean chargeIt;
  private String ccNumber;
  private Calendar ccExpireDate;
  private String ccName;
  private String ccStreet;
  private String ccZipCode;
  private Boolean ccApproved;
  private String authCode;
  private Boolean isRecurringPayment;
  private String ccSecurityCode;
  private Boolean ignoreAvs;
  private String threeDStatusCode;
  private String pnRefNum;
  private RecordRef creditCardProcessor;
  private String debitCardIssueNo;
  private Calendar validFrom;
  private Boolean undepFunds;
  private RecordRef account;
  private Double total;
  private RecordRef subsidiary;
  private Double applied;
  private Double unapplied;
  private RecordRef _class;
  private RecordRef department;
  private RecordRef location;
  private String status;
  private CustomerPaymentApplyList applyList;
  private CustomFieldList customFieldList;
  private String internalId;
  private String externalId;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public CustomerPayment() {}

  public CustomerPayment(
      String internalId,
      String externalId,
      Calendar createdDate,
      Calendar lastModifiedDate,
      RecordRef customForm,
      RecordRef arAcct,
      RecordRef customer,
      Double balance,
      Double pending,
      RecordRef currency,
      Double payment,
      Boolean autoApply,
      Calendar tranDate,
      String tranId,
      RecordRef postingPeriod,
      RecordRef paymentMethod,
      Boolean ccIsPurchaseCardBin,
      String memo,
      Boolean ccProcessAsPurchaseCard,
      String checkNum,
      String currencyName,
      Double exchangeRate,
      RecordRef creditCard,
      Boolean chargeIt,
      String ccNumber,
      Calendar ccExpireDate,
      String ccName,
      String ccStreet,
      String ccZipCode,
      Boolean ccApproved,
      String authCode,
      Boolean isRecurringPayment,
      String ccSecurityCode,
      Boolean ignoreAvs,
      String threeDStatusCode,
      String pnRefNum,
      RecordRef creditCardProcessor,
      String debitCardIssueNo,
      Calendar validFrom,
      Boolean undepFunds,
      RecordRef account,
      Double total,
      RecordRef subsidiary,
      Double applied,
      Double unapplied,
      RecordRef _class,
      RecordRef department,
      RecordRef location,
      String status,
      CustomerPaymentApplyList applyList,
      CustomFieldList customFieldList) {
    this.internalId = internalId;
    this.externalId = externalId;
    this.createdDate = createdDate;
    this.lastModifiedDate = lastModifiedDate;
    this.customForm = customForm;
    this.arAcct = arAcct;
    this.customer = customer;
    this.balance = balance;
    this.pending = pending;
    this.currency = currency;
    this.payment = payment;
    this.autoApply = autoApply;
    this.tranDate = tranDate;
    this.tranId = tranId;
    this.postingPeriod = postingPeriod;
    this.paymentMethod = paymentMethod;
    this.ccIsPurchaseCardBin = ccIsPurchaseCardBin;
    this.memo = memo;
    this.ccProcessAsPurchaseCard = ccProcessAsPurchaseCard;
    this.checkNum = checkNum;
    this.currencyName = currencyName;
    this.exchangeRate = exchangeRate;
    this.creditCard = creditCard;
    this.chargeIt = chargeIt;
    this.ccNumber = ccNumber;
    this.ccExpireDate = ccExpireDate;
    this.ccName = ccName;
    this.ccStreet = ccStreet;
    this.ccZipCode = ccZipCode;
    this.ccApproved = ccApproved;
    this.authCode = authCode;
    this.isRecurringPayment = isRecurringPayment;
    this.ccSecurityCode = ccSecurityCode;
    this.ignoreAvs = ignoreAvs;
    this.threeDStatusCode = threeDStatusCode;
    this.pnRefNum = pnRefNum;
    this.creditCardProcessor = creditCardProcessor;
    this.debitCardIssueNo = debitCardIssueNo;
    this.validFrom = validFrom;
    this.undepFunds = undepFunds;
    this.account = account;
    this.total = total;
    this.subsidiary = subsidiary;
    this.applied = applied;
    this.unapplied = unapplied;
    this._class = _class;
    this.department = department;
    this.location = location;
    this.status = status;
    this.applyList = applyList;
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

  public RecordRef getCustomForm() {
    return this.customForm;
  }

  public void setCustomForm(RecordRef customForm) {
    this.customForm = customForm;
  }

  public RecordRef getArAcct() {
    return this.arAcct;
  }

  public void setArAcct(RecordRef arAcct) {
    this.arAcct = arAcct;
  }

  public RecordRef getCustomer() {
    return this.customer;
  }

  public void setCustomer(RecordRef customer) {
    this.customer = customer;
  }

  public Double getBalance() {
    return this.balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public Double getPending() {
    return this.pending;
  }

  public void setPending(Double pending) {
    this.pending = pending;
  }

  public RecordRef getCurrency() {
    return this.currency;
  }

  public void setCurrency(RecordRef currency) {
    this.currency = currency;
  }

  public Double getPayment() {
    return this.payment;
  }

  public void setPayment(Double payment) {
    this.payment = payment;
  }

  public Boolean getAutoApply() {
    return this.autoApply;
  }

  public void setAutoApply(Boolean autoApply) {
    this.autoApply = autoApply;
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

  public RecordRef getPostingPeriod() {
    return this.postingPeriod;
  }

  public void setPostingPeriod(RecordRef postingPeriod) {
    this.postingPeriod = postingPeriod;
  }

  public RecordRef getPaymentMethod() {
    return this.paymentMethod;
  }

  public void setPaymentMethod(RecordRef paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public Boolean getCcIsPurchaseCardBin() {
    return this.ccIsPurchaseCardBin;
  }

  public void setCcIsPurchaseCardBin(Boolean ccIsPurchaseCardBin) {
    this.ccIsPurchaseCardBin = ccIsPurchaseCardBin;
  }

  public String getMemo() {
    return this.memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public Boolean getCcProcessAsPurchaseCard() {
    return this.ccProcessAsPurchaseCard;
  }

  public void setCcProcessAsPurchaseCard(Boolean ccProcessAsPurchaseCard) {
    this.ccProcessAsPurchaseCard = ccProcessAsPurchaseCard;
  }

  public String getCheckNum() {
    return this.checkNum;
  }

  public void setCheckNum(String checkNum) {
    this.checkNum = checkNum;
  }

  public String getCurrencyName() {
    return this.currencyName;
  }

  public void setCurrencyName(String currencyName) {
    this.currencyName = currencyName;
  }

  public Double getExchangeRate() {
    return this.exchangeRate;
  }

  public void setExchangeRate(Double exchangeRate) {
    this.exchangeRate = exchangeRate;
  }

  public RecordRef getCreditCard() {
    return this.creditCard;
  }

  public void setCreditCard(RecordRef creditCard) {
    this.creditCard = creditCard;
  }

  public Boolean getChargeIt() {
    return this.chargeIt;
  }

  public void setChargeIt(Boolean chargeIt) {
    this.chargeIt = chargeIt;
  }

  public String getCcNumber() {
    return this.ccNumber;
  }

  public void setCcNumber(String ccNumber) {
    this.ccNumber = ccNumber;
  }

  public Calendar getCcExpireDate() {
    return this.ccExpireDate;
  }

  public void setCcExpireDate(Calendar ccExpireDate) {
    this.ccExpireDate = ccExpireDate;
  }

  public String getCcName() {
    return this.ccName;
  }

  public void setCcName(String ccName) {
    this.ccName = ccName;
  }

  public String getCcStreet() {
    return this.ccStreet;
  }

  public void setCcStreet(String ccStreet) {
    this.ccStreet = ccStreet;
  }

  public String getCcZipCode() {
    return this.ccZipCode;
  }

  public void setCcZipCode(String ccZipCode) {
    this.ccZipCode = ccZipCode;
  }

  public Boolean getCcApproved() {
    return this.ccApproved;
  }

  public void setCcApproved(Boolean ccApproved) {
    this.ccApproved = ccApproved;
  }

  public String getAuthCode() {
    return this.authCode;
  }

  public void setAuthCode(String authCode) {
    this.authCode = authCode;
  }

  public Boolean getIsRecurringPayment() {
    return this.isRecurringPayment;
  }

  public void setIsRecurringPayment(Boolean isRecurringPayment) {
    this.isRecurringPayment = isRecurringPayment;
  }

  public String getCcSecurityCode() {
    return this.ccSecurityCode;
  }

  public void setCcSecurityCode(String ccSecurityCode) {
    this.ccSecurityCode = ccSecurityCode;
  }

  public Boolean getIgnoreAvs() {
    return this.ignoreAvs;
  }

  public void setIgnoreAvs(Boolean ignoreAvs) {
    this.ignoreAvs = ignoreAvs;
  }

  public String getThreeDStatusCode() {
    return this.threeDStatusCode;
  }

  public void setThreeDStatusCode(String threeDStatusCode) {
    this.threeDStatusCode = threeDStatusCode;
  }

  public String getPnRefNum() {
    return this.pnRefNum;
  }

  public void setPnRefNum(String pnRefNum) {
    this.pnRefNum = pnRefNum;
  }

  public RecordRef getCreditCardProcessor() {
    return this.creditCardProcessor;
  }

  public void setCreditCardProcessor(RecordRef creditCardProcessor) {
    this.creditCardProcessor = creditCardProcessor;
  }

  public String getDebitCardIssueNo() {
    return this.debitCardIssueNo;
  }

  public void setDebitCardIssueNo(String debitCardIssueNo) {
    this.debitCardIssueNo = debitCardIssueNo;
  }

  public Calendar getValidFrom() {
    return this.validFrom;
  }

  public void setValidFrom(Calendar validFrom) {
    this.validFrom = validFrom;
  }

  public Boolean getUndepFunds() {
    return this.undepFunds;
  }

  public void setUndepFunds(Boolean undepFunds) {
    this.undepFunds = undepFunds;
  }

  public RecordRef getAccount() {
    return this.account;
  }

  public void setAccount(RecordRef account) {
    this.account = account;
  }

  public Double getTotal() {
    return this.total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public RecordRef getSubsidiary() {
    return this.subsidiary;
  }

  public void setSubsidiary(RecordRef subsidiary) {
    this.subsidiary = subsidiary;
  }

  public RecordRef getLocation() {
    return this.location;
  }

  public void setLocation(RecordRef location) {
    this.location = location;
  }

  public Double getApplied() {
    return this.applied;
  }

  public void setApplied(Double applied) {
    this.applied = applied;
  }

  public Double getUnapplied() {
    return this.unapplied;
  }

  public void setUnapplied(Double unapplied) {
    this.unapplied = unapplied;
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

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public CustomerPaymentApplyList getApplyList() {
    return this.applyList;
  }

  public void setApplyList(CustomerPaymentApplyList applyList) {
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

  public String getExternalId() {
    return this.externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }

}
