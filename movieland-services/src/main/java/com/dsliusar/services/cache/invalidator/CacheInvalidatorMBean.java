package com.dsliusar.services.cache.invalidator;


public interface CacheInvalidatorMBean {
     void invalidateCountryCache();
     void invalidateGenreCache();
     void invalidateReviewCache();
}
