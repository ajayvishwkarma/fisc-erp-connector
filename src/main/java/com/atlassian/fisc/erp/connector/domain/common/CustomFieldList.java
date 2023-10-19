package com.atlassian.fisc.erp.connector.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomFieldList implements Serializable {
  private CustomFieldRef[] customField;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public CustomFieldList() {}

  public CustomFieldList(CustomFieldRef[] customField) {
    this.customField = customField;
  }

  public CustomFieldRef[] getCustomField() {
    return this.customField;
  }

  public void setCustomField(CustomFieldRef[] customField) {
    this.customField = customField;
  }

  public CustomFieldRef getCustomField(int i) {
    return this.customField[i];
  }

  public void setCustomField(int i, CustomFieldRef _value) {
    this.customField[i] = _value;
  }

}
