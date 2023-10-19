package com.atlassian.fisc.erp.connector.domain.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerAddressbookList implements Serializable {

  private CustomerAddressbook[] addressbook;
  private boolean replaceAll;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public CustomerAddressbookList() {}

  public CustomerAddressbookList(CustomerAddressbook[] addressbook, boolean replaceAll) {
    this.addressbook = addressbook;
    this.replaceAll = replaceAll;
  }

  public CustomerAddressbook[] getAddressbook() {
    return this.addressbook;
  }

  public void setAddressbook(CustomerAddressbook[] addressbook) {
    this.addressbook = addressbook;
  }

  public CustomerAddressbook getAddressbook(int i) {
    return this.addressbook[i];
  }

  public void setAddressbook(int i, CustomerAddressbook _value) {
    this.addressbook[i] = _value;
  }

  public boolean isReplaceAll() {
    return this.replaceAll;
  }

  public void setReplaceAll(boolean replaceAll) {
    this.replaceAll = replaceAll;
  }

}
