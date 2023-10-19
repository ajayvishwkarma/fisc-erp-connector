package com.atlassian.fisc.erp.connector.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonUtilTest {

  @Test
  @DisplayName("test map inversion utility")
  void invertedMap() {
    var map = Map.of("1", "a");
    var invertedMap = CommonUtil.invertMap(map);

    assertEquals(map.size(), invertedMap.size());
    assertEquals("1", invertedMap.get("a"));
  }

  @Test
  @DisplayName("test map inversion utility with duplicate data")
  void invertedMapWithDuplicates() {
    var map = Map.of("1", "a", "2", "a");
    var invertedMap = CommonUtil.invertMap(map);

    assertTrue(map.size() > invertedMap.size());
    var value = invertedMap.get("a");
    assertTrue("1".equals(value) || "2".equals(value));
  }
}
