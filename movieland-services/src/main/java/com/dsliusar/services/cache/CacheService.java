package com.dsliusar.services.cache;

import java.util.Map;

/**
 * Created by DSliusar on 16.06.2016.
 */
public interface CacheService {

      Map<?, ?> getCacheById(String cacheId);
      void addCache(String cacheId, Map<?,?> cacheMap);
}