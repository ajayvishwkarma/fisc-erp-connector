package com.atlassian.fisc.erp.connector.util;

import com.atlassian.finance.bdm.lookup.SpokeSystemItemMap;

import java.util.Map;

public class LookupTestDataUtil {
  private LookupTestDataUtil() {
    throw new IllegalStateException("Utility class");
  }

  static Map<String, String> spokeItemMap =
      Map.of(
          "15195|87", "Cloud (incremental):CFLU",
          "14899|87", "Cloud (monthly):CFLU",
          "15195|115", "Cloud (incremental):Statuspage",
          "14898|115", "Cloud (annual):Statuspage",
          "14898|102", "Cloud (annual):JIRA",
          "15195|102", "Cloud (incremental):JIRA",
          "15631|102", "Cloud (monthly incremental):JIRA",
          "14899|102", "Cloud (monthly):JIRA",
          "14667", "US Sales Tax Collected",
          "14672", "Australian Sales Tax Collected");

  public static SpokeSystemItemMap[] testDataSpokeItemMapArray() {
    var map1 = new SpokeSystemItemMap();
    map1.setItem("Cloud (incremental):CFLU");
    map1.setSpokeSystemItem("15195|87");
    var map2 = new SpokeSystemItemMap();
    map2.setItem("US Sales Tax Collected");
    map2.setSpokeSystemItem("14667");
    var data = new SpokeSystemItemMap[] {map1, map2, map2}; // map2 repeated for duplicate data
    return data;
  }

  public static Map<String, String> testDatBySpokeItem() {
    return spokeItemMap;
  }

  public static Map<String, String> testDataByItem() {
    return CommonUtil.invertMap(spokeItemMap);
  }
}
