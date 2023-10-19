package com.atlassian.fisc.erp.connector.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomFieldRef implements Serializable {

  @JsonProperty("internalId")
  private String internalId;

  @JsonProperty("scriptId")
  private String scriptId;

  @JsonProperty("value")
  private Object value;

  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public CustomFieldRef() {}

  public CustomFieldRef(String internalId, String scriptId, Object value) {
    this.internalId = internalId;
    this.scriptId = scriptId;
    this.value = value;
  }

  public String getInternalId() {
    return this.internalId;
  }

  public void setInternalId(String internalId) {
    this.internalId = internalId;
  }

  public String getScriptId() {
    return this.scriptId;
  }

  public void setScriptId(String scriptId) {
    this.scriptId = scriptId;
  }

  public Object getValue() {
    return this.value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

}
