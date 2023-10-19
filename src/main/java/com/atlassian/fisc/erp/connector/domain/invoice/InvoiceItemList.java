package com.atlassian.fisc.erp.connector.domain.invoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceItemList implements Serializable {
  private InvoiceItem[] item;
  private boolean replaceAll;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public InvoiceItemList() {}

  public InvoiceItemList(InvoiceItem[] item, boolean replaceAll) {
    this.item = item;
    this.replaceAll = replaceAll;
  }

  public InvoiceItem[] getItem() {
    return this.item;
  }

  public void setItem(InvoiceItem[] item) {
    this.item = item;
  }

  public InvoiceItem getItem(int i) {
    return this.item[i];
  }

  public void setItem(int i, InvoiceItem _value) {
    this.item[i] = _value;
  }

  public boolean isReplaceAll() {
    return this.replaceAll;
  }

  public void setReplaceAll(boolean replaceAll) {
    this.replaceAll = replaceAll;
  }

}
