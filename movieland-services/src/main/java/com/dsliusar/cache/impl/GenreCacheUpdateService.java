package com.dsliusar.cache.impl;

import com.dsliusar.cache.CacheService;
import com.dsliusar.dao.GenreDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by DSliusar on 16.06.2016.
 */

public class GenreCacheUpdateService implements CacheService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private Map<String, Integer> genreNameWithIdCache = new ConcurrentHashMap<>();

    @Autowired
    private GenreDao jdbcGenreDao;

    @Autowired
    private CacheService concurrentHashMapService;

    @Override
    public Map<?, ?> getCacheById(String cacheId) {
        return null;
    }

    @Override
    public void addCache(Map<?, ?> cacheMap) { concurrentHashMapService.addCache(cacheMap); }

    @Scheduled(fixedDelay = 5000)
    private void addCache() {
        LOGGER.info("Updating the cache of genres");
        genreNameWithIdCache = jdbcGenreDao.getAllGenres();
        addCache(genreNameWithIdCache);

    }

    public void setJdbcGenreDao(GenreDao jdbcGenreDao) {
        this.jdbcGenreDao = jdbcGenreDao;
    }



}




