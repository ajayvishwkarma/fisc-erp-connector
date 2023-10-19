package com.atlassian.fisc.erp.connector.domain.creditmemo;

import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Calendar;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditMemoItem implements Serializable {

  private RecordRef job;
  private RecordRef item;
  private Long orderLine;
  private Long line;
  private Double quantity;
  private String description;
  private String binNumbers;
  private RecordRef price;
  private String rate;
  private Double amount;
  private Boolean isTaxable;
  private CustomFieldList options;
  private RecordRef shipAddress;
  private RecordRef shipMethod;
  private RecordRef taxCode;
  private Double taxRate1;
  private Double taxRate2;
  private Double tax1Amt;
  private Double grossAmt;
  private RecordRef department;
  private RecordRef _class;
  private RecordRef location;
  private Double costEstimate;
  private String taxDetailsReference;
  private RecordRef revRecSchedule;
  private Calendar revRecStartDate;
  private Long revRecTermInMonths;
  private Calendar revRecEndDate;
  private RecordRef units;
  private String serialNumbers;
  private Boolean deferRevRec;
  private String giftCertFrom;
  private String giftCertRecipientName;
  private String giftCertRecipientEmail;
  private String giftCertMessage;
  private Double taxAmount;
  private String giftCertNumber;
  private Boolean vsoeIsEstimate;
  private Double vsoePrice;
  private Double vsoeAmount;
  private Double vsoeAllocation;
  private Boolean vsoeDelivered;
  private RecordRef catchUpPeriod;
  private RecordRef chargeType;
  private RecordRef subscriptionLine;
  private CustomFieldList customFieldList;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public CreditMemoItem() {}

  public CreditMemoItem(
      RecordRef job,
      RecordRef item,
      Long orderLine,
      Long line,
      Double quantity,
      String description,
      String binNumbers,
      RecordRef price,
      String rate,
      Double amount,
      Boolean isTaxable,
      CustomFieldList options,
      RecordRef shipAddress,
      RecordRef shipMethod,
      RecordRef taxCode,
      Double taxRate1,
      Double taxRate2,
      Double tax1Amt,
      Double grossAmt,
      RecordRef department,
      RecordRef _class,
      RecordRef location,
      Double costEstimate,
      String taxDetailsReference,
      RecordRef revRecSchedule,
      Calendar revRecStartDate,
      Long revRecTermInMonths,
      Calendar revRecEndDate,
      RecordRef units,
      String serialNumbers,
      Boolean deferRevRec,
      String giftCertFrom,
      String giftCertRecipientName,
      String giftCertRecipientEmail,
      String giftCertMessage,
      Double taxAmount,
      String giftCertNumber,
      Boolean vsoeIsEstimate,
      Double vsoePrice,
      Double vsoeAmount,
      Double vsoeAllocation,
      Boolean vsoeDelivered,
      RecordRef catchUpPeriod,
      RecordRef chargeType,
      RecordRef subscriptionLine,
      CustomFieldList customFieldList) {
    this.job = job;
    this.item = item;
    this.orderLine = orderLine;
    this.line = line;
    this.quantity = quantity;
    this.description = description;
    this.binNumbers = binNumbers;
    this.price = price;
    this.rate = rate;
    this.amount = amount;
    this.isTaxable = isTaxable;
    this.options = options;
    this.shipAddress = shipAddress;
    this.shipMethod = shipMethod;
    this.taxCode = taxCode;
    this.taxRate1 = taxRate1;
    this.taxRate2 = taxRate2;
    this.tax1Amt = tax1Amt;
    this.grossAmt = grossAmt;
    this.department = department;
    this._class = _class;
    this.location = location;
    this.costEstimate = costEstimate;
    this.taxDetailsReference = taxDetailsReference;
    this.revRecSchedule = revRecSchedule;
    this.revRecStartDate = revRecStartDate;
    this.revRecTermInMonths = revRecTermInMonths;
    this.revRecEndDate = revRecEndDate;
    this.units = units;
    this.serialNumbers = serialNumbers;
    this.deferRevRec = deferRevRec;
    this.giftCertFrom = giftCertFrom;
    this.giftCertRecipientName = giftCertRecipientName;
    this.giftCertRecipientEmail = giftCertRecipientEmail;
    this.giftCertMessage = giftCertMessage;
    this.taxAmount = taxAmount;
    this.giftCertNumber = giftCertNumber;
    this.vsoeIsEstimate = vsoeIsEstimate;
    this.vsoePrice = vsoePrice;
    this.vsoeAmount = vsoeAmount;
    this.vsoeAllocation = vsoeAllocation;
    this.vsoeDelivered = vsoeDelivered;
    this.catchUpPeriod = catchUpPeriod;
    this.chargeType = chargeType;
    this.subscriptionLine = subscriptionLine;
    this.customFieldList = customFieldList;
  }

  public RecordRef getJob() {
    return this.job;
  }

  public void setJob(RecordRef job) {
    this.job = job;
  }

  public RecordRef getItem() {
    return this.item;
  }

  public void setItem(RecordRef item) {
    this.item = item;
  }

  public Long getOrderLine() {
    return this.orderLine;
  }

  public void setOrderLine(Long orderLine) {
    this.orderLine = orderLine;
  }

  public Long getLine() {
    return this.line;
  }

  public void setLine(Long line) {
    this.line = line;
  }

  public String getRate() {
    return this.rate;
  }

  public void setRate(String rate) {
    this.rate = rate;
  }

  public Double getQuantity() {
    return this.quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public RecordRef getPrice() {
    return this.price;
  }

  public void setPrice(RecordRef price) {
    this.price = price;
  }

  public Double getAmount() {
    return this.amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getBinNumbers() {
    return this.binNumbers;
  }

  public void setBinNumbers(String binNumbers) {
    this.binNumbers = binNumbers;
  }

  public Boolean getIsTaxable() {
    return this.isTaxable;
  }

  public void setIsTaxable(Boolean isTaxable) {
    this.isTaxable = isTaxable;
  }

  public RecordRef getShipAddress() {
    return this.shipAddress;
  }

  public void setShipAddress(RecordRef shipAddress) {
    this.shipAddress = shipAddress;
  }

  public RecordRef getShipMethod() {
    return this.shipMethod;
  }

  public void setShipMethod(RecordRef shipMethod) {
    this.shipMethod = shipMethod;
  }

  public RecordRef getTaxCode() {
    return this.taxCode;
  }

  public void setTaxCode(RecordRef taxCode) {
    this.taxCode = taxCode;
  }

  public CustomFieldList getOptions() {
    return this.options;
  }

  public void setOptions(CustomFieldList options) {
    this.options = options;
  }

  public Double getTaxRate1() {
    return this.taxRate1;
  }

  public void setTaxRate1(Double taxRate1) {
    this.taxRate1 = taxRate1;
  }

  public Double getTax1Amt() {
    return this.tax1Amt;
  }

  public void setTax1Amt(Double tax1Amt) {
    this.tax1Amt = tax1Amt;
  }

  public Double getGrossAmt() {
    return this.grossAmt;
  }

  public void setGrossAmt(Double grossAmt) {
    this.grossAmt = grossAmt;
  }

  public RecordRef getDepartment() {
    return this.department;
  }

  public void setDepartment(RecordRef department) {
    this.department = department;
  }

  public Double getTaxRate2() {
    return this.taxRate2;
  }

  public void setTaxRate2(Double taxRate2) {
    this.taxRate2 = taxRate2;
  }

  public RecordRef getLocation() {
    return this.location;
  }

  public void setLocation(RecordRef location) {
    this.location = location;
  }

  public RecordRef get_class() {
    return this._class;
  }

  public void set_class(RecordRef _class) {
    this._class = _class;
  }

  public Double getCostEstimate() {
    return this.costEstimate;
  }

  public void setCostEstimate(Double costEstimate) {
    this.costEstimate = costEstimate;
  }

  public Double getVsoeAllocation() {
    return this.vsoeAllocation;
  }

  public void setVsoeAllocation(Double vsoeAllocation) {
    this.vsoeAllocation = vsoeAllocation;
  }

  public String getTaxDetailsReference() {
    return this.taxDetailsReference;
  }

  public void setTaxDetailsReference(String taxDetailsReference) {
    this.taxDetailsReference = taxDetailsReference;
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

  public Long getRevRecTermInMonths() {
    return this.revRecTermInMonths;
  }

  public void setRevRecTermInMonths(Long revRecTermInMonths) {
    this.revRecTermInMonths = revRecTermInMonths;
  }

  public Calendar getRevRecEndDate() {
    return this.revRecEndDate;
  }

  public void setRevRecEndDate(Calendar revRecEndDate) {
    this.revRecEndDate = revRecEndDate;
  }

  public RecordRef getUnits() {
    return this.units;
  }

  public void setUnits(RecordRef units) {
    this.units = units;
  }

  public String getSerialNumbers() {
    return this.serialNumbers;
  }

  public void setSerialNumbers(String serialNumbers) {
    this.serialNumbers = serialNumbers;
  }

  public Boolean getDeferRevRec() {
    return this.deferRevRec;
  }

  public void setDeferRevRec(Boolean deferRevRec) {
    this.deferRevRec = deferRevRec;
  }

  public String getGiftCertFrom() {
    return this.giftCertFrom;
  }

  public void setGiftCertFrom(String giftCertFrom) {
    this.giftCertFrom = giftCertFrom;
  }

  public String getGiftCertRecipientName() {
    return this.giftCertRecipientName;
  }

  public void setGiftCertRecipientName(String giftCertRecipientName) {
    this.giftCertRecipientName = giftCertRecipientName;
  }

  public String getGiftCertMessage() {
    return this.giftCertMessage;
  }

  public void setGiftCertMessage(String giftCertMessage) {
    this.giftCertMessage = giftCertMessage;
  }

  public String getGiftCertNumber() {
    return this.giftCertNumber;
  }

  public void setGiftCertNumber(String giftCertNumber) {
    this.giftCertNumber = giftCertNumber;
  }

  public Boolean getVsoeIsEstimate() {
    return this.vsoeIsEstimate;
  }

  public void setVsoeIsEstimate(Boolean vsoeIsEstimate) {
    this.vsoeIsEstimate = vsoeIsEstimate;
  }

  public Double getVsoePrice() {
    return this.vsoePrice;
  }

  public void setVsoePrice(Double vsoePrice) {
    this.vsoePrice = vsoePrice;
  }

  public String getGiftCertRecipientEmail() {
    return this.giftCertRecipientEmail;
  }

  public void setGiftCertRecipientEmail(String giftCertRecipientEmail) {
    this.giftCertRecipientEmail = giftCertRecipientEmail;
  }

  public Boolean getVsoeDelivered() {
    return this.vsoeDelivered;
  }

  public void setVsoeDelivered(Boolean vsoeDelivered) {
    this.vsoeDelivered = vsoeDelivered;
  }

  public RecordRef getCatchUpPeriod() {
    return this.catchUpPeriod;
  }

  public void setCatchUpPeriod(RecordRef catchUpPeriod) {
    this.catchUpPeriod = catchUpPeriod;
  }

  public Double getVsoeAmount() {
    return this.vsoeAmount;
  }

  public void setVsoeAmount(Double vsoeAmount) {
    this.vsoeAmount = vsoeAmount;
  }

  public Double getTaxAmount() {
    return this.taxAmount;
  }

  public void setTaxAmount(Double taxAmount) {
    this.taxAmount = taxAmount;
  }

  public RecordRef getChargeType() {
    return this.chargeType;
  }

  public void setChargeType(RecordRef chargeType) {
    this.chargeType = chargeType;
  }

  public RecordRef getSubscriptionLine() {
    return this.subscriptionLine;
  }

  public void setSubscriptionLine(RecordRef subscriptionLine) {
    this.subscriptionLine = subscriptionLine;
  }

  public CustomFieldList getCustomFieldList() {
    return this.customFieldList;
  }

  public void setCustomFieldList(CustomFieldList customFieldList) {
    this.customFieldList = customFieldList;
  }

}
