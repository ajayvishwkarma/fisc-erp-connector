package com.atlassian.fisc.erp.connector.domain.refund;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerRefundApplyList implements Serializable {

  private CustomerRefundApply[] apply;
  private boolean replaceAll;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public CustomerRefundApplyList() {}

  public CustomerRefundApplyList(CustomerRefundApply[] apply, boolean replaceAll) {
    this.apply = apply;
    this.replaceAll = replaceAll;
  }

  public CustomerRefundApply[] getApply() {
    return this.apply;
  }

  public void setApply(CustomerRefundApply[] apply) {
    this.apply = apply;
  }

  public CustomerRefundApply getApply(int i) {
    return this.apply[i];
  }

  public void setApply(int i, CustomerRefundApply _value) {
    this.apply[i] = _value;
  }

  public boolean isReplaceAll() {
    return this.replaceAll;
  }

  public void setReplaceAll(boolean replaceAll) {
    this.replaceAll = replaceAll;
  }
}
