package com.dsliusar.cache.executor;

import com.dsliusar.cache.CacheService;
import com.dsliusar.constants.Constant;
import com.dsliusar.dao.ReviewDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Map;

public class ReviewCacheUpdateService implements CacheService{
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    ReviewDao jdbcReviewDao;

    @Autowired
    CacheService concurrentHashMapService;


    @Override
    public Map<?, ?> getCacheById(String cacheId) {
        return null;
    }

    @Override
    public void addCache(String cacheId, Map<?, ?> cacheMap) {
        concurrentHashMapService.addCache(cacheId,cacheMap);
    }

    @PostConstruct
    public void updateAllMoviesReviewsCache(){
        LOGGER.info("Updating All Movies Review Cache");
        addCache(Constant.ALL_MOVIES_REVIEWS_CACHE, jdbcReviewDao.getAllMoviesReviews());
        LOGGER.info("Updating All Movies Reviews cache finished");

    }
}
