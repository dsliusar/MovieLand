package com.dsliusar.services.service;

import com.dsliusar.persistence.entity.Country;

import java.util.List;
import java.util.Map;

public interface CountryService {

    List<Country> getAllCountriesByMovieId(int movieId);
    Map<String,Integer> getAllCountries();
    Map<Integer,List<Country>> getAllMoviesCountries();
}
