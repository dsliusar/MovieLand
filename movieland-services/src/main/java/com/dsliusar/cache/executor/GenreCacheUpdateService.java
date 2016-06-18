package com.dsliusar.cache.executor;

import com.dsliusar.cache.CacheService;
import com.dsliusar.constants.Constant;
import com.dsliusar.dao.GenreDao;
import com.dsliusar.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

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

    //Executor every 1 minute
    @PostConstruct
    public void updateAllGenresCache() {
        LOGGER.info("Updating cache of all genres");
        Map<String, Integer> genresCache = jdbcGenreDao.getAllGenres();
        addCache(Constant.ALL_GENRE_CACHE,genresCache);
        LOGGER.info("Updating cache with all genres finished");

    }

    //Executor every 1 minute
    @PostConstruct
    public void updateAllGenresWithMovieIdCache() {
        LOGGER.info("Updating cache of all genres with mapped MovieId");
        Map<Integer,List<Genre>> genreMovieIdCache = jdbcGenreDao.getGenreWithMovieId();
        addCache(Constant.ALL_MOVIES_GENRE_CACHE,genreMovieIdCache);
        LOGGER.info("Updating cache with all genres and movie id finished");

    }
}




