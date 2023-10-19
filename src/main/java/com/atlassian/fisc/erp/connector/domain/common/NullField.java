package com.atlassian.fisc.erp.connector.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NullField implements Serializable {

  private String[] name;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public NullField() {}

  public NullField(String[] name) {
    this.name = name;
  }

  public String[] getName() {
    return this.name;
  }

  public void setName(String[] name) {
    this.name = name;
  }

  public String getName(int i) {
    return this.name[i];
  }

  public void setName(int i, String _value) {
    this.name[i] = _value;
  }

}
