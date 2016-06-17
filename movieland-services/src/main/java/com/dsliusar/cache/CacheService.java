package com.dsliusar.cache;

import java.util.Map;

/**
 * Created by DSliusar on 16.06.2016.
 */
public interface CacheService {

      Map<?, ?> getCacheById(String cacheId);
      void addCache(Map<?,?> cacheMap);
}
