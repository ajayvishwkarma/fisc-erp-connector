package com.atlassian.fisc.erp.connector.dto.lookup;

import lombok.Data;

import java.util.Map;

@Data
public class SpokeItemMapping {
  private Map<String, String> mapWithItemAsKey;
  private Map<String, String> mapWithSpokeSystemItemAsKey;
}
