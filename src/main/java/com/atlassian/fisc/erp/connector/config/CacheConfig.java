package com.atlassian.fisc.erp.connector.config;

import com.atlassian.fisc.erp.connector.constant.Constant;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

  @Value("${cache.lookup.spokeItemMap.ttlHours}")
  private Long spokeItemTtlHours;

  @Bean
  public CacheManager cacheManager() {
    SimpleCacheManager cacheManager = new SimpleCacheManager();
    cacheManager.setCaches(constructCacheList());
    return cacheManager;
  }

  private List<Cache> constructCacheList() {
    return Arrays.asList(buildCache(Constant.CACHE_NAME_SPOKE_ITEM_MAP, spokeItemTtlHours));
  }
  private CaffeineCache buildCache(String name, long ttlHrs) {
    return new CaffeineCache(
        name, Caffeine.newBuilder().expireAfterWrite(ttlHrs, TimeUnit.HOURS).build());
  }
}
