package com.atlassian.fisc.erp.connector.util;

import com.atlassian.fisc.erp.connector.buildppln.JacocoCoverageExcludeGenerated;

import java.util.Map;
import java.util.stream.Collectors;

public class CommonUtil {
  @JacocoCoverageExcludeGenerated
  private CommonUtil() {
    throw new IllegalStateException("Utility class");
  }

  public static <V, K> Map<V, K> invertMap(Map<K, V> map) {
    return map.entrySet().stream()
        .collect(
            Collectors.toMap(
                Map.Entry::getValue,
                Map.Entry::getKey,
                (oldValue, newValue) -> oldValue)); // TODO revisit duplicate handling
  }
}
