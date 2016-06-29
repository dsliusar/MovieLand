package com.dsliusar.services.cache.invalidator;

import javax.management.MXBean;

@MXBean
public class CacheInvalidatorController implements CacheInvalidatorMBean {

    private boolean enableGenreCacheInvalidator;
    private boolean enableCountryCacheInvalidator;
    private boolean enableReviewCacheInvalidator;

    public boolean isEnableGenreCacheInvalidator() {
        return enableGenreCacheInvalidator;
    }

    public void setEnableGenreCacheInvalidator(boolean enableGenreCacheInvalidator) {
        this.enableGenreCacheInvalidator = enableGenreCacheInvalidator;
    }

    public boolean isEnableCountryCacheInvalidator() {
        return enableCountryCacheInvalidator;
    }

    public void setEnableCountryCacheInvalidator(boolean enableCounryCacheInvalidator) {
        this.enableCountryCacheInvalidator = enableCounryCacheInvalidator;
    }

    public boolean isEnableReviewCacheInvalidator() {
        return enableReviewCacheInvalidator;
    }

    public void setEnableReviewCacheInvalidator(boolean enableReviewCacheInvalidator) {
        this.enableReviewCacheInvalidator = enableReviewCacheInvalidator;
    }
}
