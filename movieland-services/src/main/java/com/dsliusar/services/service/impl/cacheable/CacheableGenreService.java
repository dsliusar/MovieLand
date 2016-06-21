package com.dsliusar.services.service.impl.cacheable;

import com.dsliusar.services.cache.CacheService;
import com.dsliusar.constants.Constant;
import com.dsliusar.persistence.entity.Genre;
import com.dsliusar.services.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CacheableGenreService implements GenreService {

    @Autowired
    private CacheService concurrentHashMapService;

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
