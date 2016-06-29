package com.dsliusar.services.cache.invalidator;


public interface CacheInvalidatorMBean {
     void setEnableCountryCacheInvalidator(boolean enableCountryCacheInvalidator);
     void setEnableGenreCacheInvalidator(boolean enableGenreCacheInvalidator);
     void setEnableReviewCacheInvalidator(boolean enableReviewCacheInvalidator);
}
