package com.atlassian.fisc.erp.connector.domain.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Calendar;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerPaymentApply implements Serializable {

  private Boolean apply;
  private Long doc;
  private Long line;
  private Calendar applyDate;
  private String job;
  private String type;
  private String refNum;
  private Double total;
  private Double due;
  private String currency;
  private Calendar discDate;
  private Double discAmt;
  private Double disc;
  private Double amount;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public CustomerPaymentApply() {}

  public CustomerPaymentApply(
      Boolean apply,
      Long doc,
      Long line,
      Calendar applyDate,
      String job,
      String type,
      String refNum,
      Double total,
      Double due,
      String currency,
      Calendar discDate,
      Double discAmt,
      Double disc,
      Double amount) {
    this.apply = apply;
    this.doc = doc;
    this.line = line;
    this.applyDate = applyDate;
    this.job = job;
    this.type = type;
    this.refNum = refNum;
    this.total = total;
    this.due = due;
    this.currency = currency;
    this.discDate = discDate;
    this.discAmt = discAmt;
    this.disc = disc;
    this.amount = amount;
  }

  public Boolean getApply() {
    return this.apply;
  }

  public void setApply(Boolean apply) {
    this.apply = apply;
  }

  public Long getDoc() {
    return this.doc;
  }

  public void setDoc(Long doc) {
    this.doc = doc;
  }

  public Long getLine() {
    return this.line;
  }

  public void setLine(Long line) {
    this.line = line;
  }

  public Calendar getApplyDate() {
    return this.applyDate;
  }

  public void setApplyDate(Calendar applyDate) {
    this.applyDate = applyDate;
  }

  public String getJob() {
    return this.job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getRefNum() {
    return this.refNum;
  }

  public void setRefNum(String refNum) {
    this.refNum = refNum;
  }

  public Double getTotal() {
    return this.total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public Double getDue() {
    return this.due;
  }

  public void setDue(Double due) {
    this.due = due;
  }

  public String getCurrency() {
    return this.currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Calendar getDiscDate() {
    return this.discDate;
  }

  public void setDiscDate(Calendar discDate) {
    this.discDate = discDate;
  }

  public Double getDiscAmt() {
    return this.discAmt;
  }

  public void setDiscAmt(Double discAmt) {
    this.discAmt = discAmt;
  }

  public Double getDisc() {
    return this.disc;
  }

  public void setDisc(Double disc) {
    this.disc = disc;
  }

  public Double getAmount() {
    return this.amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

}
