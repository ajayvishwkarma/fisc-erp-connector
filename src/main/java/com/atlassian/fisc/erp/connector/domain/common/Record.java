package com.atlassian.fisc.erp.connector.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Record implements Serializable {

  @JsonIgnore private NullField nullFieldList;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public Record() {}

  public Record(NullField nullFieldList) {
    this.nullFieldList = nullFieldList;
  }

  public NullField getNullFieldList() {
    return this.nullFieldList;
  }

  public void setNullFieldList(NullField nullFieldList) {
    this.nullFieldList = nullFieldList;
  }

}
