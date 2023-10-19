package com.atlassian.fisc.erp.connector.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address extends Record implements Serializable {

  private String internalId;
  private Country country;
  private String attention;
  private String addressee;
  private String addrPhone;
  private String addr1;
  private String addr2;
  private String addr3;
  private String city;
  private String state;
  private String zip;
  private String addrText;
  private Boolean override;
  private CustomFieldList customFieldList;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public Address() {}

  public Address(
      NullField nullFieldList,
      String internalId,
      Country country,
      String attention,
      String addressee,
      String addrPhone,
      String addr1,
      String addr2,
      String addr3,
      String city,
      String state,
      String zip,
      String addrText,
      Boolean override,
      CustomFieldList customFieldList) {
    super(nullFieldList);
    this.internalId = internalId;
    this.country = country;
    this.attention = attention;
    this.addressee = addressee;
    this.addrPhone = addrPhone;
    this.addr1 = addr1;
    this.addr2 = addr2;
    this.addr3 = addr3;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.addrText = addrText;
    this.override = override;
    this.customFieldList = customFieldList;
  }

  public String getInternalId() {
    return this.internalId;
  }

  public void setInternalId(String internalId) {
    this.internalId = internalId;
  }

  public Country getCountry() {
    return this.country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public String getAttention() {
    return this.attention;
  }

  public void setAttention(String attention) {
    this.attention = attention;
  }

  public String getAddressee() {
    return this.addressee;
  }

  public void setAddressee(String addressee) {
    this.addressee = addressee;
  }

  public String getAddrPhone() {
    return this.addrPhone;
  }

  public void setAddrPhone(String addrPhone) {
    this.addrPhone = addrPhone;
  }

  public String getAddr1() {
    return this.addr1;
  }

  public void setAddr1(String addr1) {
    this.addr1 = addr1;
  }

  public String getAddr2() {
    return this.addr2;
  }

  public void setAddr2(String addr2) {
    this.addr2 = addr2;
  }

  public String getAddr3() {
    return this.addr3;
  }

  public void setAddr3(String addr3) {
    this.addr3 = addr3;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZip() {
    return this.zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getAddrText() {
    return this.addrText;
  }

  public void setAddrText(String addrText) {
    this.addrText = addrText;
  }

  public Boolean getOverride() {
    return this.override;
  }

  public void setOverride(Boolean override) {
    this.override = override;
  }

  public CustomFieldList getCustomFieldList() {
    return this.customFieldList;
  }

  public void setCustomFieldList(CustomFieldList customFieldList) {
    this.customFieldList = customFieldList;
  }

}
