package com.dsliusar.cache.executor;

import com.dsliusar.cache.CacheService;
import com.dsliusar.constants.Constant;
import com.dsliusar.dao.CountryDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Map;

public class CountryCacheUpdateService implements CacheService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    CountryDao jdbcCountryDao;

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
    public void updateAllCountriesCache(){
        LOGGER.info("Updating All Countries Cache");
        addCache(Constant.ALL_COUNTRIES_CACHE,jdbcCountryDao.getAllCountries());
        LOGGER.info("Finished Updating All Countries Cache");
    }

    @PostConstruct
    public void updateAllMoviesCountriesCache(){
        LOGGER.info("Updating All Countries Cache");
        addCache(Constant.ALL_MOVIES_COUNTRIES_CACHE,jdbcCountryDao.getAllMoviesCounties());
        LOGGER.info("Finished Updating All Countries Cache");
    }
}
