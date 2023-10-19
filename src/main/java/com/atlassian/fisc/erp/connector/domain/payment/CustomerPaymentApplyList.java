package com.atlassian.fisc.erp.connector.domain.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerPaymentApplyList implements Serializable {

  private CustomerPaymentApply[] apply;
  private boolean replaceAll;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public CustomerPaymentApplyList() {}

  public CustomerPaymentApplyList(CustomerPaymentApply[] apply, boolean replaceAll) {
    this.apply = apply;
    this.replaceAll = replaceAll;
  }

  public CustomerPaymentApply[] getApply() {
    return this.apply;
  }

  public void setApply(CustomerPaymentApply[] apply) {
    this.apply = apply;
  }

  public CustomerPaymentApply getApply(int i) {
    return this.apply[i];
  }

  public void setApply(int i, CustomerPaymentApply _value) {
    this.apply[i] = _value;
  }

  public boolean isReplaceAll() {
    return this.replaceAll;
  }

  public void setReplaceAll(boolean replaceAll) {
    this.replaceAll = replaceAll;
  }

}
