package com.atlassian.fisc.erp.connector.domain.refund;

import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.Record;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Calendar;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerRefund extends Record implements Serializable {

  private Calendar createdDate;
  private Calendar lastModifiedDate;
  private String status;
  private String transactionNumber;
  private RecordRef customer;
  private RecordRef customForm;
  private Double balance;
  private RecordRef arAcct;
  private String currencyName;
  private Double exchangeRate;
  private String address;
  private Double total;
  private RecordRef currency;
  private Calendar tranDate;
  private RecordRef voidJournal;
  private RecordRef postingPeriod;
  private String memo;
  private RecordRef paymentMethod;
  private RecordRef account;
  private Boolean toBePrinted;
  private String tranId;
  private String debitCardIssueNo;
  private RecordRef creditCardProcessor;
  private Boolean chargeIt;
  private String pnRefNum;
  private Calendar validFrom;
  private RecordRef subsidiary;
  private RecordRef department;
  private RecordRef _class;
  private RecordRef location;
  private RecordRef creditCard;
  private Boolean ccIsPurchaseCardBin;
  private String ccNumber;
  private Boolean ccProcessAsPurchaseCard;
  private Calendar ccExpireDate;
  private String ccName;
  private String ccStreet;
  private String ccZipCode;
  private Boolean ccApproved;
  private CustomerRefundApplyList applyList;
  private CustomFieldList customFieldList;
  private String internalId;
  private String externalId;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public CustomerRefund() {}

  public CustomerRefund(
      String internalId,
      String externalId,
      Calendar createdDate,
      Calendar lastModifiedDate,
      String status,
      String transactionNumber,
      RecordRef customer,
      RecordRef customForm,
      Double balance,
      RecordRef arAcct,
      String currencyName,
      Double exchangeRate,
      String address,
      Double total,
      RecordRef currency,
      Calendar tranDate,
      RecordRef voidJournal,
      RecordRef postingPeriod,
      String memo,
      RecordRef paymentMethod,
      RecordRef account,
      Boolean toBePrinted,
      String tranId,
      String debitCardIssueNo,
      RecordRef creditCardProcessor,
      Boolean chargeIt,
      String pnRefNum,
      Calendar validFrom,
      RecordRef subsidiary,
      RecordRef department,
      RecordRef _class,
      RecordRef location,
      RecordRef creditCard,
      Boolean ccIsPurchaseCardBin,
      String ccNumber,
      Boolean ccProcessAsPurchaseCard,
      Calendar ccExpireDate,
      String ccName,
      String ccStreet,
      String ccZipCode,
      Boolean ccApproved,
      CustomerRefundApplyList applyList,
      CustomFieldList customFieldList) {
    this.internalId = internalId;
    this.externalId = externalId;
    this.createdDate = createdDate;
    this.lastModifiedDate = lastModifiedDate;
    this.status = status;
    this.transactionNumber = transactionNumber;
    this.customer = customer;
    this.customForm = customForm;
    this.balance = balance;
    this.arAcct = arAcct;
    this.currencyName = currencyName;
    this.exchangeRate = exchangeRate;
    this.address = address;
    this.total = total;
    this.currency = currency;
    this.tranDate = tranDate;
    this.voidJournal = voidJournal;
    this.postingPeriod = postingPeriod;
    this.memo = memo;
    this.paymentMethod = paymentMethod;
    this.account = account;
    this.toBePrinted = toBePrinted;
    this.tranId = tranId;
    this.debitCardIssueNo = debitCardIssueNo;
    this.creditCardProcessor = creditCardProcessor;
    this.chargeIt = chargeIt;
    this.pnRefNum = pnRefNum;
    this.validFrom = validFrom;
    this.subsidiary = subsidiary;
    this.department = department;
    this._class = _class;
    this.location = location;
    this.creditCard = creditCard;
    this.ccIsPurchaseCardBin = ccIsPurchaseCardBin;
    this.ccNumber = ccNumber;
    this.ccProcessAsPurchaseCard = ccProcessAsPurchaseCard;
    this.ccExpireDate = ccExpireDate;
    this.ccName = ccName;
    this.ccStreet = ccStreet;
    this.ccZipCode = ccZipCode;
    this.ccApproved = ccApproved;
    this.applyList = applyList;
    this.customFieldList = customFieldList;
  }

  public Calendar getLastModifiedDate() {
    return this.lastModifiedDate;
  }

  public void setLastModifiedDate(Calendar lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTransactionNumber() {
    return this.transactionNumber;
  }

  public void setTransactionNumber(String transactionNumber) {
    this.transactionNumber = transactionNumber;
  }

  public Calendar getCreatedDate() {
    return this.createdDate;
  }

  public void setCreatedDate(Calendar createdDate) {
    this.createdDate = createdDate;
  }

  public RecordRef getCustomer() {
    return this.customer;
  }

  public void setCustomer(RecordRef customer) {
    this.customer = customer;
  }

  public RecordRef getCustomForm() {
    return this.customForm;
  }

  public void setCustomForm(RecordRef customForm) {
    this.customForm = customForm;
  }

  public Double getBalance() {
    return this.balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public RecordRef getArAcct() {
    return this.arAcct;
  }

  public void setArAcct(RecordRef arAcct) {
    this.arAcct = arAcct;
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

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Double getTotal() {
    return this.total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public RecordRef getCurrency() {
    return this.currency;
  }

  public void setCurrency(RecordRef currency) {
    this.currency = currency;
  }

  public Calendar getTranDate() {
    return this.tranDate;
  }

  public void setTranDate(Calendar tranDate) {
    this.tranDate = tranDate;
  }

  public RecordRef getVoidJournal() {
    return this.voidJournal;
  }

  public void setVoidJournal(RecordRef voidJournal) {
    this.voidJournal = voidJournal;
  }

  public RecordRef getPostingPeriod() {
    return this.postingPeriod;
  }

  public void setPostingPeriod(RecordRef postingPeriod) {
    this.postingPeriod = postingPeriod;
  }

  public String getMemo() {
    return this.memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public RecordRef getPaymentMethod() {
    return this.paymentMethod;
  }

  public void setPaymentMethod(RecordRef paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public RecordRef getAccount() {
    return this.account;
  }

  public void setAccount(RecordRef account) {
    this.account = account;
  }

  public Boolean getToBePrinted() {
    return this.toBePrinted;
  }

  public void setToBePrinted(Boolean toBePrinted) {
    this.toBePrinted = toBePrinted;
  }

  public String getTranId() {
    return this.tranId;
  }

  public void setTranId(String tranId) {
    this.tranId = tranId;
  }

  public String getDebitCardIssueNo() {
    return this.debitCardIssueNo;
  }

  public void setDebitCardIssueNo(String debitCardIssueNo) {
    this.debitCardIssueNo = debitCardIssueNo;
  }

  public RecordRef getCreditCardProcessor() {
    return this.creditCardProcessor;
  }

  public void setCreditCardProcessor(RecordRef creditCardProcessor) {
    this.creditCardProcessor = creditCardProcessor;
  }

  public Boolean getChargeIt() {
    return this.chargeIt;
  }

  public void setChargeIt(Boolean chargeIt) {
    this.chargeIt = chargeIt;
  }

  public String getPnRefNum() {
    return this.pnRefNum;
  }

  public void setPnRefNum(String pnRefNum) {
    this.pnRefNum = pnRefNum;
  }

  public Calendar getValidFrom() {
    return this.validFrom;
  }

  public void setValidFrom(Calendar validFrom) {
    this.validFrom = validFrom;
  }

  public RecordRef getSubsidiary() {
    return this.subsidiary;
  }

  public void setSubsidiary(RecordRef subsidiary) {
    this.subsidiary = subsidiary;
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

  public RecordRef getCreditCard() {
    return this.creditCard;
  }

  public void setCreditCard(RecordRef creditCard) {
    this.creditCard = creditCard;
  }

  public RecordRef getLocation() {
    return this.location;
  }

  public void setLocation(RecordRef location) {
    this.location = location;
  }

  public Boolean getCcIsPurchaseCardBin() {
    return this.ccIsPurchaseCardBin;
  }

  public void setCcIsPurchaseCardBin(Boolean ccIsPurchaseCardBin) {
    this.ccIsPurchaseCardBin = ccIsPurchaseCardBin;
  }

  public String getCcNumber() {
    return this.ccNumber;
  }

  public void setCcNumber(String ccNumber) {
    this.ccNumber = ccNumber;
  }

  public Boolean getCcProcessAsPurchaseCard() {
    return this.ccProcessAsPurchaseCard;
  }

  public void setCcProcessAsPurchaseCard(Boolean ccProcessAsPurchaseCard) {
    this.ccProcessAsPurchaseCard = ccProcessAsPurchaseCard;
  }

  public CustomFieldList getCustomFieldList() {
    return this.customFieldList;
  }

  public void setCustomFieldList(CustomFieldList customFieldList) {
    this.customFieldList = customFieldList;
  }

  public Calendar getCcExpireDate() {
    return this.ccExpireDate;
  }

  public void setCcExpireDate(Calendar ccExpireDate) {
    this.ccExpireDate = ccExpireDate;
  }

  public String getCcStreet() {
    return this.ccStreet;
  }

  public void setCcStreet(String ccStreet) {
    this.ccStreet = ccStreet;
  }

  public String getCcName() {
    return this.ccName;
  }

  public void setCcName(String ccName) {
    this.ccName = ccName;
  }

  public Boolean getCcApproved() {
    return this.ccApproved;
  }

  public void setCcApproved(Boolean ccApproved) {
    this.ccApproved = ccApproved;
  }

  public String getCcZipCode() {
    return this.ccZipCode;
  }

  public void setCcZipCode(String ccZipCode) {
    this.ccZipCode = ccZipCode;
  }

  public String getInternalId() {
    return this.internalId;
  }

  public void setInternalId(String internalId) {
    this.internalId = internalId;
  }

  public CustomerRefundApplyList getApplyList() {
    return this.applyList;
  }

  public void setApplyList(CustomerRefundApplyList applyList) {
    this.applyList = applyList;
  }

  public String getExternalId() {
    return this.externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }

}
