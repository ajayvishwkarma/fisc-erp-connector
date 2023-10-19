package com.atlassian.fisc.erp.connector.domain.contact;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactAddressbookList {

  private ContactAddressbook[] addressbook;
  private boolean replaceAll;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public ContactAddressbookList() {}

  public ContactAddressbookList(ContactAddressbook[] addressbook, boolean replaceAll) {
    this.addressbook = addressbook;
    this.replaceAll = replaceAll;
  }

  public ContactAddressbook[] getAddressbook() {
    return this.addressbook;
  }

  public void setAddressbook(ContactAddressbook[] addressbook) {
    this.addressbook = addressbook;
  }

  public ContactAddressbook getAddressbook(int i) {
    return this.addressbook[i];
  }

  public void setAddressbook(int i, ContactAddressbook _value) {
    this.addressbook[i] = _value;
  }

  public boolean isReplaceAll() {
    return this.replaceAll;
  }

  public void setReplaceAll(boolean replaceAll) {
    this.replaceAll = replaceAll;
  }

}
