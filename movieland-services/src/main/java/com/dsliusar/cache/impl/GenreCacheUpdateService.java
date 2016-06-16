package com.dsliusar.cache.impl;

import com.dsliusar.cache.CacheService;
import com.dsliusar.dao.GenreDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by DSliusar on 16.06.2016.
 */

public class GenreCacheUpdateService implements CacheService{


    private static final int NUM_THREADS = 1;
    private final ScheduledExecutorService fScheduler;
    @Autowired
    GenreDao genreDao;

    @Autowired
    CacheService concurrentHashMapService;

    GenreCacheUpdateService() {
        fScheduler = Executors.newScheduledThreadPool(NUM_THREADS);
    }

    @Override
    public ConcurrentHashMap<?, ?> getCacheById(String cacheId) {
        return null;
    }

    @Override
    public void addCache(ConcurrentHashMap<?, ?> cacheMap) {
        concurrentHashMapService.addCache(cacheMap);
    }

    public void afterPropertiesSet() throws Exception {
        GenreCacheUpdateService updateCache = new GenreCacheUpdateService();
        updateCache.runCacheUpdate();
    }


    private final class IntervalUpdateCache implements Runnable {
        @Override
        public void run() {
            System.out.println("beep !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            genreDao.getAllGenres();
        }
    }

    void runCacheUpdate() {
        Runnable periodicalCacheUpdate = new IntervalUpdateCache();
        fScheduler.scheduleAtFixedRate(periodicalCacheUpdate, 0, 20, TimeUnit.SECONDS);
    }

}




