package com.atlassian.fisc.erp.connector.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListOrRecordRef implements Serializable {

  private String name;
  private String internalId;
  private String externalId;
  private String typeId;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public ListOrRecordRef() {}

  public ListOrRecordRef(String name, String internalId, String externalId, String typeId) {
    this.name = name;
    this.internalId = internalId;
    this.externalId = externalId;
    this.typeId = typeId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getInternalId() {
    return this.internalId;
  }

  public void setInternalId(String internalId) {
    this.internalId = internalId;
  }

  public String getExternalId() {
    return this.externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }

  public String getTypeId() {
    return this.typeId;
  }

  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }

}
