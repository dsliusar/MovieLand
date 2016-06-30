package com.dsliusar.services.cache.executor;

import com.dsliusar.persistence.dao.GenreDao;
import com.dsliusar.persistence.entity.Genre;
import com.dsliusar.services.cache.CacheService;
import com.dsliusar.tools.constants.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
@DependsOn("initDb")
public class GenreCacheUpdateService implements CacheService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private GenreDao jdbcGenreDao;

    @Autowired
    private CacheService concurrentHashMapService;

    @Override
    public Map<?, ?> getCacheById(String cacheId) {
        return null;
    }

    @Override
    public void addCache(String cacheId,Map<?, ?> cacheMap) { concurrentHashMapService.addCache(cacheId,cacheMap); }

    /**
     * Method is executing by scheduler
     * Scheduled details are in the service-context.xml file
     */
    @PostConstruct
    public void updateAllGenresCache() {
        LOGGER.info("Updating cache of all genres");
        invalidateCache();
        LOGGER.info("Updating cache with all genres finished");

    }

    /**
     * Method is executing by scheduler
     * Scheduled details are in the service-context.xml file
     */
    @PostConstruct
    public void updateAllGenresWithMovieIdCache() {
        LOGGER.info("Updating cache of all genres with mapped MovieId");
        invalidateCache();
        LOGGER.info("Updating cache with all genres and movie id finished");
    }

    /**
     * Invalidating the cache - update the map from DB
     */
    public void invalidateCache(){
        addCache(Constant.ALL_GENRE_CACHE,jdbcGenreDao.getAllGenres());
        addCache(Constant.ALL_MOVIES_GENRE_CACHE,jdbcGenreDao.getGenreWithMovieId());
    }

}




