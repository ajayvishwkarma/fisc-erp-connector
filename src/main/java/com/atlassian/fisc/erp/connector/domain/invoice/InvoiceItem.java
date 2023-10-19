package com.atlassian.fisc.erp.connector.domain.invoice;

import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Calendar;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceItem {

  private RecordRef job;
  private RecordRef item;
  private Long line;
  private String description;
  private Double amount;
  private Boolean isTaxable;
  private CustomFieldList options;
  private RecordRef subscriptionLine;
  private Boolean deferRevRec;
  private Double quantity;
  private Double currentPercent;
  private RecordRef units;
  private String serialNumbers;
  private String binNumbers;
  private RecordRef price;
  private String rate;
  private Double percentComplete;
  private Double quantityOnHand;
  private Double quantityAvailable;
  private Double quantityOrdered;
  private Double quantityRemaining;
  private Double quantityFulfilled;
  private Double amountOrdered;
  private RecordRef department;
  private Long orderLine;
  private String licenseCode;
  private RecordRef _class;
  private RecordRef location;
  private RecordRef revRecSchedule;
  private Calendar revRecStartDate;
  private Calendar revRecEndDate;
  private Double grossAmt;
  private Double costEstimate;
  private String taxDetailsReference;
  private Boolean excludeFromRateRequest;
  private RecordRef catchUpPeriod;
  private Double tax1Amt;
  private RecordRef taxCode;
  private Double taxRate1;
  private Double taxRate2;
  private String giftCertFrom;
  private String giftCertRecipientName;
  private String giftCertRecipientEmail;
  private String giftCertMessage;
  private Double taxAmount;
  private String giftCertNumber;
  private Long shipGroup;
  private Boolean itemIsFulfilled;
  private RecordRef shipAddress;
  private RecordRef shipMethod;
  private Boolean vsoeIsEstimate;
  private Double vsoePrice;
  private Double vsoeAmount;
  private Double vsoeAllocation;
  private Boolean vsoeDelivered;
  private RecordRef chargeType;
  private CustomFieldList customFieldList;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public InvoiceItem() {}

  public InvoiceItem(
      RecordRef job,
      RecordRef item,
      Long line,
      String description,
      Double amount,
      Boolean isTaxable,
      CustomFieldList options,
      RecordRef subscriptionLine,
      Boolean deferRevRec,
      Double quantity,
      Double currentPercent,
      RecordRef units,
      String serialNumbers,
      String binNumbers,
      RecordRef price,
      String rate,
      Double percentComplete,
      Double quantityOnHand,
      Double quantityAvailable,
      Double quantityOrdered,
      Double quantityRemaining,
      Double quantityFulfilled,
      Double amountOrdered,
      RecordRef department,
      Long orderLine,
      String licenseCode,
      RecordRef _class,
      RecordRef location,
      RecordRef revRecSchedule,
      Calendar revRecStartDate,
      Calendar revRecEndDate,
      Double grossAmt,
      Double costEstimate,
      String taxDetailsReference,
      Boolean excludeFromRateRequest,
      RecordRef catchUpPeriod,
      Double tax1Amt,
      RecordRef taxCode,
      Double taxRate1,
      Double taxRate2,
      String giftCertFrom,
      String giftCertRecipientName,
      String giftCertRecipientEmail,
      String giftCertMessage,
      Double taxAmount,
      String giftCertNumber,
      Long shipGroup,
      Boolean itemIsFulfilled,
      RecordRef shipAddress,
      RecordRef shipMethod,
      Boolean vsoeIsEstimate,
      Double vsoePrice,
      Double vsoeAmount,
      Double vsoeAllocation,
      Boolean vsoeDelivered,
      RecordRef chargeType,
      CustomFieldList customFieldList) {
    this.job = job;
    this.item = item;
    this.line = line;
    this.description = description;
    this.amount = amount;
    this.isTaxable = isTaxable;
    this.options = options;
    this.subscriptionLine = subscriptionLine;
    this.deferRevRec = deferRevRec;
    this.quantity = quantity;
    this.currentPercent = currentPercent;
    this.units = units;
    this.serialNumbers = serialNumbers;
    this.binNumbers = binNumbers;
    this.price = price;
    this.rate = rate;
    this.percentComplete = percentComplete;
    this.quantityOnHand = quantityOnHand;
    this.quantityAvailable = quantityAvailable;
    this.quantityOrdered = quantityOrdered;
    this.quantityRemaining = quantityRemaining;
    this.quantityFulfilled = quantityFulfilled;
    this.amountOrdered = amountOrdered;
    this.department = department;
    this.orderLine = orderLine;
    this.licenseCode = licenseCode;
    this._class = _class;
    this.location = location;
    this.revRecSchedule = revRecSchedule;
    this.revRecStartDate = revRecStartDate;
    this.revRecEndDate = revRecEndDate;
    this.grossAmt = grossAmt;
    this.costEstimate = costEstimate;
    this.taxDetailsReference = taxDetailsReference;
    this.excludeFromRateRequest = excludeFromRateRequest;
    this.catchUpPeriod = catchUpPeriod;
    this.tax1Amt = tax1Amt;
    this.taxCode = taxCode;
    this.taxRate1 = taxRate1;
    this.taxRate2 = taxRate2;
    this.giftCertFrom = giftCertFrom;
    this.giftCertRecipientName = giftCertRecipientName;
    this.giftCertRecipientEmail = giftCertRecipientEmail;
    this.giftCertMessage = giftCertMessage;
    this.taxAmount = taxAmount;
    this.giftCertNumber = giftCertNumber;
    this.shipGroup = shipGroup;
    this.itemIsFulfilled = itemIsFulfilled;
    this.shipAddress = shipAddress;
    this.shipMethod = shipMethod;
    this.vsoeIsEstimate = vsoeIsEstimate;
    this.vsoePrice = vsoePrice;
    this.vsoeAmount = vsoeAmount;
    this.vsoeAllocation = vsoeAllocation;
    this.vsoeDelivered = vsoeDelivered;
    this.chargeType = chargeType;
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

  public Long getLine() {
    return this.line;
  }

  public void setLine(Long line) {
    this.line = line;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getAmount() {
    return this.amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Boolean getIsTaxable() {
    return this.isTaxable;
  }

  public void setIsTaxable(Boolean isTaxable) {
    this.isTaxable = isTaxable;
  }

  public CustomFieldList getOptions() {
    return this.options;
  }

  public void setOptions(CustomFieldList options) {
    this.options = options;
  }

  public RecordRef getSubscriptionLine() {
    return this.subscriptionLine;
  }

  public void setSubscriptionLine(RecordRef subscriptionLine) {
    this.subscriptionLine = subscriptionLine;
  }

  public Boolean getDeferRevRec() {
    return this.deferRevRec;
  }

  public void setDeferRevRec(Boolean deferRevRec) {
    this.deferRevRec = deferRevRec;
  }

  public Double getQuantity() {
    return this.quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public Double getCurrentPercent() {
    return this.currentPercent;
  }

  public void setCurrentPercent(Double currentPercent) {
    this.currentPercent = currentPercent;
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

  public String getBinNumbers() {
    return this.binNumbers;
  }

  public void setBinNumbers(String binNumbers) {
    this.binNumbers = binNumbers;
  }

  public RecordRef getPrice() {
    return this.price;
  }

  public void setPrice(RecordRef price) {
    this.price = price;
  }

  public String getRate() {
    return this.rate;
  }

  public void setRate(String rate) {
    this.rate = rate;
  }

  public Double getPercentComplete() {
    return this.percentComplete;
  }

  public void setPercentComplete(Double percentComplete) {
    this.percentComplete = percentComplete;
  }

  public Double getQuantityOnHand() {
    return this.quantityOnHand;
  }

  public void setQuantityOnHand(Double quantityOnHand) {
    this.quantityOnHand = quantityOnHand;
  }

  public Double getQuantityAvailable() {
    return this.quantityAvailable;
  }

  public void setQuantityAvailable(Double quantityAvailable) {
    this.quantityAvailable = quantityAvailable;
  }

  public Double getQuantityOrdered() {
    return this.quantityOrdered;
  }

  public void setQuantityOrdered(Double quantityOrdered) {
    this.quantityOrdered = quantityOrdered;
  }

  public Double getQuantityRemaining() {
    return this.quantityRemaining;
  }

  public void setQuantityRemaining(Double quantityRemaining) {
    this.quantityRemaining = quantityRemaining;
  }

  public Double getQuantityFulfilled() {
    return this.quantityFulfilled;
  }

  public void setQuantityFulfilled(Double quantityFulfilled) {
    this.quantityFulfilled = quantityFulfilled;
  }

  public Double getAmountOrdered() {
    return this.amountOrdered;
  }

  public void setAmountOrdered(Double amountOrdered) {
    this.amountOrdered = amountOrdered;
  }

  public RecordRef getDepartment() {
    return this.department;
  }

  public void setDepartment(RecordRef department) {
    this.department = department;
  }

  public Long getOrderLine() {
    return this.orderLine;
  }

  public void setOrderLine(Long orderLine) {
    this.orderLine = orderLine;
  }

  public String getLicenseCode() {
    return this.licenseCode;
  }

  public void setLicenseCode(String licenseCode) {
    this.licenseCode = licenseCode;
  }

  public RecordRef get_class() {
    return this._class;
  }

  public void set_class(RecordRef _class) {
    this._class = _class;
  }

  public RecordRef getLocation() {
    return this.location;
  }

  public void setLocation(RecordRef location) {
    this.location = location;
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

  public Double getGrossAmt() {
    return this.grossAmt;
  }

  public void setGrossAmt(Double grossAmt) {
    this.grossAmt = grossAmt;
  }

  public Double getCostEstimate() {
    return this.costEstimate;
  }

  public void setCostEstimate(Double costEstimate) {
    this.costEstimate = costEstimate;
  }

  public String getTaxDetailsReference() {
    return this.taxDetailsReference;
  }

  public void setTaxDetailsReference(String taxDetailsReference) {
    this.taxDetailsReference = taxDetailsReference;
  }

  public Boolean getExcludeFromRateRequest() {
    return this.excludeFromRateRequest;
  }

  public void setExcludeFromRateRequest(Boolean excludeFromRateRequest) {
    this.excludeFromRateRequest = excludeFromRateRequest;
  }

  public RecordRef getCatchUpPeriod() {
    return this.catchUpPeriod;
  }

  public void setCatchUpPeriod(RecordRef catchUpPeriod) {
    this.catchUpPeriod = catchUpPeriod;
  }

  public Double getTax1Amt() {
    return this.tax1Amt;
  }

  public void setTax1Amt(Double tax1Amt) {
    this.tax1Amt = tax1Amt;
  }

  public RecordRef getTaxCode() {
    return this.taxCode;
  }

  public void setTaxCode(RecordRef taxCode) {
    this.taxCode = taxCode;
  }

  public Double getTaxRate1() {
    return this.taxRate1;
  }

  public void setTaxRate1(Double taxRate1) {
    this.taxRate1 = taxRate1;
  }

  public Double getTaxRate2() {
    return this.taxRate2;
  }

  public void setTaxRate2(Double taxRate2) {
    this.taxRate2 = taxRate2;
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

  public String getGiftCertRecipientEmail() {
    return this.giftCertRecipientEmail;
  }

  public void setGiftCertRecipientEmail(String giftCertRecipientEmail) {
    this.giftCertRecipientEmail = giftCertRecipientEmail;
  }

  public String getGiftCertMessage() {
    return this.giftCertMessage;
  }

  public void setGiftCertMessage(String giftCertMessage) {
    this.giftCertMessage = giftCertMessage;
  }

  public Double getTaxAmount() {
    return this.taxAmount;
  }

  public void setTaxAmount(Double taxAmount) {
    this.taxAmount = taxAmount;
  }

  public String getGiftCertNumber() {
    return this.giftCertNumber;
  }

  public void setGiftCertNumber(String giftCertNumber) {
    this.giftCertNumber = giftCertNumber;
  }

  public Long getShipGroup() {
    return this.shipGroup;
  }

  public void setShipGroup(Long shipGroup) {
    this.shipGroup = shipGroup;
  }

  public Boolean getItemIsFulfilled() {
    return this.itemIsFulfilled;
  }

  public void setItemIsFulfilled(Boolean itemIsFulfilled) {
    this.itemIsFulfilled = itemIsFulfilled;
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

  public Double getVsoeAmount() {
    return this.vsoeAmount;
  }

  public void setVsoeAmount(Double vsoeAmount) {
    this.vsoeAmount = vsoeAmount;
  }

  public Double getVsoeAllocation() {
    return this.vsoeAllocation;
  }

  public void setVsoeAllocation(Double vsoeAllocation) {
    this.vsoeAllocation = vsoeAllocation;
  }

  public Boolean getVsoeDelivered() {
    return this.vsoeDelivered;
  }

  public void setVsoeDelivered(Boolean vsoeDelivered) {
    this.vsoeDelivered = vsoeDelivered;
  }

  public RecordRef getChargeType() {
    return this.chargeType;
  }

  public void setChargeType(RecordRef chargeType) {
    this.chargeType = chargeType;
  }

  public CustomFieldList getCustomFieldList() {
    return this.customFieldList;
  }

  public void setCustomFieldList(CustomFieldList customFieldList) {
    this.customFieldList = customFieldList;
  }

}
