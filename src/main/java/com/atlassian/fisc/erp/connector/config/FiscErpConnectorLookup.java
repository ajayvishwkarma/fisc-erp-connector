package com.atlassian.fisc.erp.connector.config;

import com.atlassian.fisc.erp.connector.service.LookupService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
@Setter
@RequiredArgsConstructor
@Slf4j
public class FiscErpConnectorLookup {

  private final LookupService lookupService;

  public Map<String, String> byItem() {
    var mappingData = lookupService.getHamsSpokeItemMap();
    return null != mappingData ? mappingData.getMapWithItemAsKey() : null;
  }

  public Map<String, String> bySpokeSystemItem() {
    var mappingData = lookupService.getHamsSpokeItemMap();
    return null != mappingData ? mappingData.getMapWithSpokeSystemItemAsKey() : null;
  }

  @PostConstruct
  private void postConstruct() {
    //  warm-up cache
    lookupService.getHamsSpokeItemMap();
    log.info("lookup caches warmed");
  }
}
