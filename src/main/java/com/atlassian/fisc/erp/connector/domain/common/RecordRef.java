package com.atlassian.fisc.erp.connector.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecordRef extends BaseRef implements Serializable {
  @JsonProperty("internalId")
  private String internalId;

  @JsonProperty("externalId")
  private String externalId;

  @JsonProperty("type")
  private RecordType type;

  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public RecordRef() {}

  public RecordRef(String name, String internalId, String externalId, RecordType type) {
    super(name);
    this.internalId = internalId;
    this.externalId = externalId;
    this.type = type;
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

  public RecordType getType() {
    return this.type;
  }

  public void setType(RecordType type) {
    this.type = type;
  }

}
