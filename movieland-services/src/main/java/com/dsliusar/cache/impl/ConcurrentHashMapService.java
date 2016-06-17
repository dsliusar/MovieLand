package com.dsliusar.cache.impl;

import com.dsliusar.cache.CacheService;
import com.dsliusar.constants.Constant;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by DSliusar on 16.06.2016.
 */
@Service
public class ConcurrentHashMapService implements CacheService {

    private Map<String, Map<?, ?>> mapOfCacheMaps = new ConcurrentHashMap<>();

    @Override
    public Map<?,?> getCacheById(String cacheId) {
        return mapOfCacheMaps.get(cacheId);
    }

    @Override
    public void addCache(Map<?, ?> cacheMap) {
        mapOfCacheMaps.put(Constant.GENRE_CONCURRENT_CACHE_MAP, cacheMap);
    }
}
