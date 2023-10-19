package com.atlassian.fisc.erp.connector.domain.creditmemo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Calendar;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditMemoApply implements Serializable {

  private Boolean apply;
  private Long doc;
  private Calendar applyDate;
  private String job;
  private String type;
  private String refNum;
  private Double total;
  private Double due;
  private String currency;
  private Double amount;
  private Long line;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public CreditMemoApply() {}

  public CreditMemoApply(
      Boolean apply,
      Long doc,
      Calendar applyDate,
      String job,
      String type,
      String refNum,
      Double total,
      Double due,
      String currency,
      Double amount,
      Long line) {
    this.apply = apply;
    this.doc = doc;
    this.applyDate = applyDate;
    this.job = job;
    this.type = type;
    this.refNum = refNum;
    this.total = total;
    this.due = due;
    this.currency = currency;
    this.amount = amount;
    this.line = line;
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

  public Double getDue() {
    return this.due;
  }

  public void setDue(Double due) {
    this.due = due;
  }

  public Double getAmount() {
    return this.amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Calendar getApplyDate() {
    return this.applyDate;
  }

  public void setApplyDate(Calendar applyDate) {
    this.applyDate = applyDate;
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

  public String getJob() {
    return this.job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public Double getTotal() {
    return this.total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public String getCurrency() {
    return this.currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Long getLine() {
    return this.line;
  }

  public void setLine(Long line) {
    this.line = line;
  }

}
