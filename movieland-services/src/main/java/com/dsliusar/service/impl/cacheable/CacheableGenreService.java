package com.dsliusar.service.impl.cacheable;

import com.dsliusar.cache.CacheService;
import com.dsliusar.constants.Constant;
import com.dsliusar.entity.Genre;
import com.dsliusar.service.GenreService;
import com.dsliusar.service.impl.GenericGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by DSliusar on 16.06.2016.
 */

@Service
public class CacheableGenreService implements GenreService {

    @Autowired
    GenericGenreService simpleGenreService;

    @Autowired
    CacheService concurrentHashMapService;

    @Override
    public List<Genre> getGenresByMovieId(int movieId) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Integer,List<Genre>> getAllMoviesGenres() {
            return (Map<Integer,List<Genre>>) concurrentHashMapService.getCacheById(Constant.ALL_MOVIES_GENRE_CACHE);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Integer> getAllGenres() {
        return (Map<String, Integer>) concurrentHashMapService.getCacheById(Constant.ALL_GENRE_CACHE);
    }


}
