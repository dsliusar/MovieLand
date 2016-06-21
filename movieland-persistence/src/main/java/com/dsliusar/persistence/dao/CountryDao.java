package com.dsliusar.persistence.dao;

import com.dsliusar.persistence.entity.Country;

import java.util.List;
import java.util.Map;

/**
 * Created by Red1 on 6/8/2016.
 */
public interface CountryDao{

    void insert( Map<String,Country> countryMap);
    List<Country> getAllCountriesByMovieId(int movieId);
    Map<String,Integer> getAllCountries();
    Map<Integer,List<Country>> getAllMoviesCounties();
}
