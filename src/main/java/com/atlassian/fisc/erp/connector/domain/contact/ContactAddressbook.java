package com.atlassian.fisc.erp.connector.domain.contact;

import com.atlassian.fisc.erp.connector.domain.common.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactAddressbook {

  private Boolean defaultShipping;
  private Boolean defaultBilling;
  private String label;
  private Address addressbookAddress;
  private String internalId;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public ContactAddressbook() {}

  public ContactAddressbook(
      Boolean defaultShipping,
      Boolean defaultBilling,
      String label,
      Address addressbookAddress,
      String internalId) {
    this.defaultShipping = defaultShipping;
    this.defaultBilling = defaultBilling;
    this.label = label;
    this.addressbookAddress = addressbookAddress;
    this.internalId = internalId;
  }

  public Boolean getDefaultShipping() {
    return this.defaultShipping;
  }

  public void setDefaultShipping(Boolean defaultShipping) {
    this.defaultShipping = defaultShipping;
  }

  public Boolean getDefaultBilling() {
    return this.defaultBilling;
  }

  public void setDefaultBilling(Boolean defaultBilling) {
    this.defaultBilling = defaultBilling;
  }

  public String getLabel() {
    return this.label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public Address getAddressbookAddress() {
    return this.addressbookAddress;
  }

  public void setAddressbookAddress(Address addressbookAddress) {
    this.addressbookAddress = addressbookAddress;
  }

  public String getInternalId() {
    return this.internalId;
  }

  public void setInternalId(String internalId) {
    this.internalId = internalId;
  }

}
