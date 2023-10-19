package com.atlassian.fisc.erp.connector.domain.customer;

import com.atlassian.fisc.erp.connector.domain.common.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerAddressbook implements Serializable {

  private Boolean defaultShipping;
  private Boolean defaultBilling;
  private Boolean isResidential;
  private String label;
  private Address addressbookAddress;
  private String internalId;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public CustomerAddressbook() {}

  public CustomerAddressbook(
      Boolean defaultShipping,
      Boolean defaultBilling,
      Boolean isResidential,
      String label,
      Address addressbookAddress,
      String internalId) {
    this.defaultShipping = defaultShipping;
    this.defaultBilling = defaultBilling;
    this.isResidential = isResidential;
    this.label = label;
    this.addressbookAddress = addressbookAddress;
    this.internalId = internalId;
  }

  public Boolean getDefaultBilling() {
    return this.defaultBilling;
  }

  public void setDefaultBilling(Boolean defaultBilling) {
    this.defaultBilling = defaultBilling;
  }

  public String getInternalId() {
    return this.internalId;
  }

  public void setInternalId(String internalId) {
    this.internalId = internalId;
  }

  public Boolean getIsResidential() {
    return this.isResidential;
  }

  public void setIsResidential(Boolean isResidential) {
    this.isResidential = isResidential;
  }

  public String getLabel() {
    return this.label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public Boolean getDefaultShipping() {
    return this.defaultShipping;
  }

  public void setDefaultShipping(Boolean defaultShipping) {
    this.defaultShipping = defaultShipping;
  }

  public Address getAddressbookAddress() {
    return this.addressbookAddress;
  }

  public void setAddressbookAddress(Address addressbookAddress) {
    this.addressbookAddress = addressbookAddress;
  }

}
