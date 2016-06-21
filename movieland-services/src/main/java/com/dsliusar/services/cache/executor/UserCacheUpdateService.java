package com.dsliusar.services.cache.executor;

import com.dsliusar.services.cache.CacheService;
import com.dsliusar.constants.Constant;
import com.dsliusar.persistence.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Map;

public class UserCacheUpdateService implements CacheService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao jdbcUserDao;

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

    @PostConstruct
    public void updateAllUsersReviewsCache(){
        LOGGER.info("Updating All Movies Review Cache");
        addCache(Constant.ALL_USERS_CACHE, jdbcUserDao.getAllUsers());
        LOGGER.info("Updating All Movies Reviews cache finished");

    }

}
