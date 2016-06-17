package com.dsliusar.cache.impl;

import com.dsliusar.constants.Constant;
import com.dsliusar.cache.CacheService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by DSliusar on 16.06.2016.
 */
@Service
public class ConcurrentHashMapService implements CacheService {

    private ConcurrentHashMap<String, ConcurrentHashMap<?, ?>> mapOfCacheMaps;
    @Override
    public ConcurrentHashMap<?,?> getCacheById(String cacheId) {
        return mapOfCacheMaps.get(cacheId);
    }

    @Override
    public void addCache(ConcurrentHashMap<?, ?> cacheMap) {
        mapOfCacheMaps.put(Constant.GENRE_CONCURRENT_CACHE_MAP, cacheMap);
    }
}
