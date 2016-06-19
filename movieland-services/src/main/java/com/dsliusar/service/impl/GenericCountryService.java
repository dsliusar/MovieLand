package com.dsliusar.service.impl;

import com.dsliusar.dao.CountryDao;
import com.dsliusar.entity.Country;
import com.dsliusar.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GenericCountryService implements CountryService {

    @Autowired
    CountryDao jdbcCountryDao;

    @Override
    public List<Country> getAllCountriesByMovieId(int movieId) {
        return jdbcCountryDao.getAllCountriesByMovieId(movieId);
    }

    @Override
    public Map<String, Integer> getAllCountries() {
        return jdbcCountryDao.getAllCountries();
    }

    @Override
    public Map<Integer, List<Country>> getAllMoviesCountries() {
        return jdbcCountryDao.getAllMoviesCounties();
    }
}