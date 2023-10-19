package com.atlassian.fisc.erp.connector.client.dto;

import lombok.Data;

@Data
@java.lang.SuppressWarnings("unused")
public class ErpServiceClientReq {
  private String path;
  private String requestBody;
  private String source;
}
