package com.dsliusar.service.impl;

import com.dsliusar.cache.CacheService;
import com.dsliusar.entity.Genre;
import com.dsliusar.service.GenreService;
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
    SimpleGenreService simpleGenreService;

    @Autowired
    CacheService concurrentHashMapService;

    @Override
    public List<Genre> getGenresByMovieId(int movieId) {
        return null;
    }

    @Override
    public Map<String, Integer> getAllGenres() {
    //    System.out.println("In Cacheable");
      //  return (Map<String, Integer>) concurrentHashMapService.getCacheById(Constant.GENRE_CONCURRENT_CACHE_MAP);
return null;

    }


}
