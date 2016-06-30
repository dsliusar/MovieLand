package com.dsliusar.services.cache;

import java.util.Map;

public interface CacheService {

      Map<?, ?> getCacheById(String cacheId);
      void addCache(String cacheId, Map<?,?> cacheMap);
}
