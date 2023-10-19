package com.atlassian.fisc.erp.connector.service;

import com.atlassian.fisc.erp.connector.dto.lookup.SpokeItemMapping;

public interface LookupService {
  SpokeItemMapping getHamsSpokeItemMap();
}
