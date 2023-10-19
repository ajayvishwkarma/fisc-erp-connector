package com.atlassian.fisc.erp.connector.domain.creditmemo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditMemoApplyList implements Serializable {

  private CreditMemoApply[] apply;
  private boolean replaceAll;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public CreditMemoApplyList() {}

  public CreditMemoApplyList(CreditMemoApply[] apply, boolean replaceAll) {
    this.apply = apply;
    this.replaceAll = replaceAll;
  }

  public CreditMemoApply[] getApply() {
    return this.apply;
  }

  public void setApply(CreditMemoApply[] apply) {
    this.apply = apply;
  }

  public CreditMemoApply getApply(int i) {
    return this.apply[i];
  }

  public void setApply(int i, CreditMemoApply _value) {
    this.apply[i] = _value;
  }

  public boolean isReplaceAll() {
    return this.replaceAll;
  }

  public void setReplaceAll(boolean replaceAll) {
    this.replaceAll = replaceAll;
  }

}
