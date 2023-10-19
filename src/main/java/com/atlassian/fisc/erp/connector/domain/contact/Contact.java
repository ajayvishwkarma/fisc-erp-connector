package com.atlassian.fisc.erp.connector.domain.contact;

import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.Record;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Calendar;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contact extends Record implements Serializable {

  private RecordRef customForm;
  private String entityId;
  private RecordRef contactSource;
  private RecordRef company;
  private String salutation;
  private String firstName;
  private String middleName;
  private String lastName;
  private String title;
  private String phone;
  private String fax;
  private String email;
  private String defaultAddress;
  private Boolean isPrivate;
  private Boolean isInactive;
  private RecordRef subsidiary;
  private String phoneticName;
  private String altEmail;
  private String officePhone;
  private String homePhone;
  private String mobilePhone;
  private RecordRef supervisor;
  private String supervisorPhone;
  private RecordRef assistant;
  private String assistantPhone;
  private String comments;
  private RecordRef image;
  private Boolean billPay;
  private Calendar dateCreated;
  private Calendar lastModifiedDate;
  private ContactAddressbookList addressbookList;
  private CustomFieldList customFieldList;
  private String internalId;
  private String externalId;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public Contact() {}

  public Contact(
      String internalId,
      String externalId,
      RecordRef customForm,
      String entityId,
      RecordRef contactSource,
      RecordRef company,
      String salutation,
      String firstName,
      String middleName,
      String lastName,
      String title,
      String phone,
      String fax,
      String email,
      String defaultAddress,
      Boolean isPrivate,
      Boolean isInactive,
      RecordRef subsidiary,
      String phoneticName,
      String altEmail,
      String officePhone,
      String homePhone,
      String mobilePhone,
      RecordRef supervisor,
      String supervisorPhone,
      RecordRef assistant,
      String assistantPhone,
      String comments,
      RecordRef image,
      Boolean billPay,
      Calendar dateCreated,
      Calendar lastModifiedDate,
      ContactAddressbookList addressbookList,
      CustomFieldList customFieldList) {
    this.internalId = internalId;
    this.externalId = externalId;
    this.customForm = customForm;
    this.entityId = entityId;
    this.contactSource = contactSource;
    this.company = company;
    this.salutation = salutation;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.title = title;
    this.phone = phone;
    this.fax = fax;
    this.email = email;
    this.defaultAddress = defaultAddress;
    this.isPrivate = isPrivate;
    this.isInactive = isInactive;
    this.subsidiary = subsidiary;
    this.phoneticName = phoneticName;
    this.altEmail = altEmail;
    this.officePhone = officePhone;
    this.homePhone = homePhone;
    this.mobilePhone = mobilePhone;
    this.supervisor = supervisor;
    this.supervisorPhone = supervisorPhone;
    this.assistant = assistant;
    this.assistantPhone = assistantPhone;
    this.comments = comments;
    this.image = image;
    this.billPay = billPay;
    this.dateCreated = dateCreated;
    this.lastModifiedDate = lastModifiedDate;
    this.addressbookList = addressbookList;
    this.customFieldList = customFieldList;
  }

  public RecordRef getCustomForm() {
    return this.customForm;
  }

  public void setCustomForm(RecordRef customForm) {
    this.customForm = customForm;
  }

  public String getEntityId() {
    return this.entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  public RecordRef getContactSource() {
    return this.contactSource;
  }

  public void setContactSource(RecordRef contactSource) {
    this.contactSource = contactSource;
  }

  public RecordRef getCompany() {
    return this.company;
  }

  public void setCompany(RecordRef company) {
    this.company = company;
  }

  public String getSalutation() {
    return this.salutation;
  }

  public void setSalutation(String salutation) {
    this.salutation = salutation;
  }

  public String getMiddleName() {
    return this.middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getFax() {
    return this.fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getDefaultAddress() {
    return this.defaultAddress;
  }

  public void setDefaultAddress(String defaultAddress) {
    this.defaultAddress = defaultAddress;
  }

  public Boolean getIsPrivate() {
    return this.isPrivate;
  }

  public void setIsPrivate(Boolean isPrivate) {
    this.isPrivate = isPrivate;
  }

  public Boolean getIsInactive() {
    return this.isInactive;
  }

  public void setIsInactive(Boolean isInactive) {
    this.isInactive = isInactive;
  }

  public RecordRef getSubsidiary() {
    return this.subsidiary;
  }

  public void setSubsidiary(RecordRef subsidiary) {
    this.subsidiary = subsidiary;
  }

  public String getPhoneticName() {
    return this.phoneticName;
  }

  public void setPhoneticName(String phoneticName) {
    this.phoneticName = phoneticName;
  }

  public String getAltEmail() {
    return this.altEmail;
  }

  public void setAltEmail(String altEmail) {
    this.altEmail = altEmail;
  }

  public String getOfficePhone() {
    return this.officePhone;
  }

  public void setOfficePhone(String officePhone) {
    this.officePhone = officePhone;
  }

  public String getHomePhone() {
    return this.homePhone;
  }

  public void setHomePhone(String homePhone) {
    this.homePhone = homePhone;
  }

  public String getMobilePhone() {
    return this.mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  public RecordRef getSupervisor() {
    return this.supervisor;
  }

  public void setSupervisor(RecordRef supervisor) {
    this.supervisor = supervisor;
  }

  public String getSupervisorPhone() {
    return this.supervisorPhone;
  }

  public void setSupervisorPhone(String supervisorPhone) {
    this.supervisorPhone = supervisorPhone;
  }

  public RecordRef getAssistant() {
    return this.assistant;
  }

  public void setAssistant(RecordRef assistant) {
    this.assistant = assistant;
  }

  public String getAssistantPhone() {
    return this.assistantPhone;
  }

  public void setAssistantPhone(String assistantPhone) {
    this.assistantPhone = assistantPhone;
  }

  public String getComments() {
    return this.comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public RecordRef getImage() {
    return this.image;
  }

  public void setImage(RecordRef image) {
    this.image = image;
  }

  public Boolean getBillPay() {
    return this.billPay;
  }

  public void setBillPay(Boolean billPay) {
    this.billPay = billPay;
  }

  public Calendar getDateCreated() {
    return this.dateCreated;
  }

  public void setDateCreated(Calendar dateCreated) {
    this.dateCreated = dateCreated;
  }

  public Calendar getLastModifiedDate() {
    return this.lastModifiedDate;
  }

  public void setLastModifiedDate(Calendar lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public ContactAddressbookList getAddressbookList() {
    return this.addressbookList;
  }

  public void setAddressbookList(ContactAddressbookList addressbookList) {
    this.addressbookList = addressbookList;
  }

  public CustomFieldList getCustomFieldList() {
    return this.customFieldList;
  }

  public void setCustomFieldList(CustomFieldList customFieldList) {
    this.customFieldList = customFieldList;
  }

  public String getExternalId() {
    return this.externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }

  public String getInternalId() {
    return this.internalId;
  }

  public void setInternalId(String internalId) {
    this.internalId = internalId;
  }

}
