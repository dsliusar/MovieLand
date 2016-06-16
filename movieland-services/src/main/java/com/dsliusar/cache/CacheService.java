package com.dsliusar.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by DSliusar on 16.06.2016.
 */
public interface CacheService {

      ConcurrentHashMap<?, ?> getCacheById(String cacheId);
      void addCache(ConcurrentHashMap<?,?> cacheMap);
}
