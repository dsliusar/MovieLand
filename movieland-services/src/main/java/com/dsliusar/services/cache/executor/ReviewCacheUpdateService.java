package com.dsliusar.services.cache.executor;

import com.dsliusar.persistence.dao.ReviewDao;
import com.dsliusar.services.cache.CacheService;
import com.dsliusar.tools.constants.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
@DependsOn("initDb")
public class ReviewCacheUpdateService implements CacheService{
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReviewDao jdbcReviewDao;

    @Autowired
    private CacheService concurrentHashMapService;

    @Override
    public Map<?, ?> getCacheById(String cacheId) {
        return null;
    }

    @Override
    public void addCache(String cacheId, Map<?, ?> cacheMap) {
        concurrentHashMapService.addCache(cacheId,cacheMap);
    }

    /**
     * Method is executing by scheduler
     * Scheduled details are in the service-context.xml file
     */
    @PostConstruct
    public void updateAllMoviesReviewsCache(){
        LOGGER.info("Updating All Movies Review Cache");
        invalidateCache();
        LOGGER.info("Updating All Movies Reviews cache finished");
    }
    /**
     * Invalidating the cache - update the map from DB
     */
    public void invalidateCache(){
        addCache(Constant.ALL_MOVIES_REVIEWS_CACHE, jdbcReviewDao.getAllMoviesReviews());
    }
}
