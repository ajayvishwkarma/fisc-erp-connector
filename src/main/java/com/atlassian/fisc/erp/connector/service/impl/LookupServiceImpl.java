package com.atlassian.fisc.erp.connector.service.impl;

import com.atlassian.finance.bdm.lookup.SpokeSystemItemMap;
import com.atlassian.finsys.micros.logging.JsonLogV2;
import com.atlassian.fisc.erp.connector.client.ErpServiceClient;
import com.atlassian.fisc.erp.connector.constant.Constant;
import com.atlassian.fisc.erp.connector.dto.lookup.SpokeItemMapping;
import com.atlassian.fisc.erp.connector.service.LookupService;
import com.atlassian.fisc.erp.connector.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LookupServiceImpl implements LookupService {
  //  @Autowired through constructor
  private final ErpServiceClient erpServiceClient;

  @Override
  @JsonLogV2(
      logOutputAdditionalProperties = "mapWithItemAsKey.size()",
      suppressErrorLogToWarning = false)
  @Cacheable(
      value = Constant.CACHE_NAME_SPOKE_ITEM_MAP,
      key = "#root.methodName",
      unless = "#result == null")
  public SpokeItemMapping getHamsSpokeItemMap() {
    var spokeSystemItemMaps = erpServiceClient.getHamsSpokeItemMap();

    var map =
        spokeSystemItemMaps.stream()
            .collect(
                Collectors.toMap(
                    SpokeSystemItemMap::getSpokeSystemItem,
                    SpokeSystemItemMap::getItem,
                    (oldValue, newValue) -> oldValue)); // TODO revisit duplicate handling

    SpokeItemMapping spokeItemMapping = new SpokeItemMapping();
    spokeItemMapping.setMapWithSpokeSystemItemAsKey(map);
    spokeItemMapping.setMapWithItemAsKey(CommonUtil.invertMap(map));

    return spokeItemMapping;
  }
}
