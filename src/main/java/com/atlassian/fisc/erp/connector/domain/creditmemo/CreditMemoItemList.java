package com.atlassian.fisc.erp.connector.domain.creditmemo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditMemoItemList implements Serializable {

  private CreditMemoItem[] item;
  private boolean replaceAll;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public CreditMemoItemList() {}

  public CreditMemoItemList(CreditMemoItem[] item, boolean replaceAll) {
    this.item = item;
    this.replaceAll = replaceAll;
  }

  public CreditMemoItem[] getItem() {
    return this.item;
  }

  public void setItem(CreditMemoItem[] item) {
    this.item = item;
  }

  public CreditMemoItem getItem(int i) {
    return this.item[i];
  }

  public void setItem(int i, CreditMemoItem _value) {
    this.item[i] = _value;
  }

  public boolean isReplaceAll() {
    return this.replaceAll;
  }

  public void setReplaceAll(boolean replaceAll) {
    this.replaceAll = replaceAll;
  }

}
