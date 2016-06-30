package com.dsliusar.services.cache;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ConcurrentHashMapService implements CacheService {

    private Map<String, Map<?, ?>> mapOfCacheMaps = new ConcurrentHashMap<>();

    @Override
    public Map<?,?> getCacheById(String cacheId) {
        return new ConcurrentHashMap<>(mapOfCacheMaps.get(cacheId));
    }

    @Override
    public void addCache(String cacheId,Map<?, ?> cacheMap) {
        mapOfCacheMaps.put(cacheId,cacheMap);
    }
}
