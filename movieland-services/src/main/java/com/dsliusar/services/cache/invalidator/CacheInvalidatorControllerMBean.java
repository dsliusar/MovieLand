package com.dsliusar.services.cache.invalidator;

import com.dsliusar.services.cache.executor.CountryCacheUpdateService;
import com.dsliusar.services.cache.executor.GenreCacheUpdateService;
import com.dsliusar.services.cache.executor.ReviewCacheUpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

@Service
@ManagedResource(objectName = "CacheInvalidator:type=JMX,name=Invalidate")
public class CacheInvalidatorControllerMBean implements CacheInvalidatorMBean {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private CountryCacheUpdateService countryCacheUpdateService;

    @Autowired
    private GenreCacheUpdateService genreCacheUpdateService;

    @Autowired
    private ReviewCacheUpdateService reviewCacheUpdateService;

    @Override
    @ManagedOperation
    public void invalidateCountryCache() {
        LOGGER.info("Invalidating of the cache started");
        long startTime = System.currentTimeMillis();
        countryCacheUpdateService.invalidateCache();
        LOGGER.info("Cache has been invalidated and updated, it took {} ", System.currentTimeMillis() - startTime);
    }

    @Override
    @ManagedOperation
    public void invalidateGenreCache() {
        LOGGER.info("Invalidating of the cache started");
        long startTime = System.currentTimeMillis();
        genreCacheUpdateService.invalidateCache();
        LOGGER.info("Cache has been invalidated and updated, it took {} ", System.currentTimeMillis() - startTime);
    }

    @Override
    @ManagedOperation
    public void invalidateReviewCache() {
        LOGGER.info("Invalidating of the cache started");
        long startTime = System.currentTimeMillis();
        reviewCacheUpdateService.invalidateCache();
        LOGGER.info("Cache has been invalidated and updated, it took {} ", System.currentTimeMillis() - startTime);
    }
}
