package com.dsliusar.services.cache.executor;

import com.dsliusar.tools.constants.Constant;
import com.dsliusar.persistence.dao.CountryDao;
import com.dsliusar.services.cache.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
@DependsOn("initDb")
public class CountryCacheUpdateService implements CacheService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private CountryDao jdbcCountryDao;

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
    public void updateAllCountriesCache(){
        LOGGER.info("Updating All Countries Cache");
        invalidateCache();
        LOGGER.info("Finished Updating All Countries Cache");
    }

    /**
     * Method is executing by scheduler
     * Scheduled details are in the service-context.xml file
     */
    @PostConstruct
    public void updateAllMoviesCountriesCache(){
        LOGGER.info("Updating All Countries Cache");
        invalidateCache();
        LOGGER.info("Finished Updating All Countries Cache");
    }

    /**
     * Invalidating the cache - update the map from DB
     */
    public void invalidateCache(){
        addCache(Constant.ALL_COUNTRIES_CACHE,jdbcCountryDao.getAllCountries());
        addCache(Constant.ALL_MOVIES_COUNTRIES_CACHE,jdbcCountryDao.getAllMoviesCounties());
    }
}
