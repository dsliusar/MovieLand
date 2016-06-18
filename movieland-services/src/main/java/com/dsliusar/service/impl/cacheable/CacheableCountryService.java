package com.dsliusar.service.impl.cacheable;

import com.dsliusar.dao.CountryDao;
import com.dsliusar.entity.Country;
import com.dsliusar.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CacheableCountryService implements CountryService {

    @Autowired
    private CountryDao jdbCountryDao;

    @Override
    public List<Country> getAllCountriesByMovieId(int movieId) {
        return null;
    }

    @Override
    public Map<String, Integer> getAllCountries() {
        return jdbCountryDao.getAllCountries();
    }

    @Override
    public Map<Integer, List<Country>> getAllMoviesCountries() {
        return jdbCountryDao.getAllMoviesCounties();
    }
}
