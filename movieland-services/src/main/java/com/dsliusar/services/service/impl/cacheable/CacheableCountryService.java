package com.dsliusar.services.service.impl.cacheable;

import com.dsliusar.services.cache.CacheService;
import com.dsliusar.tools.constants.Constant;
import com.dsliusar.persistence.entity.Country;
import com.dsliusar.services.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CacheableCountryService implements CountryService {

    @Autowired
    private CacheService concurrentHashMapService;

    @Override
    public List<Country> getAllCountriesByMovieId(int movieId) {

        return getAllMoviesCountries().get(movieId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Integer> getAllCountries() {
        return (HashMap<String, Integer>) concurrentHashMapService.getCacheById(Constant.ALL_COUNTRIES_CACHE);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<Integer, List<Country>> getAllMoviesCountries() {
        return (Map<Integer, List<Country>>) concurrentHashMapService.getCacheById(Constant.ALL_MOVIES_COUNTRIES_CACHE);
    }
}
