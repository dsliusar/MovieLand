package com.dsliusar.dao.files.impl;

import com.dsliusar.entity.CountriesMovie;
import com.dsliusar.entity.Country;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CountryMovieParser {

    private List<CountriesMovie> countyMoviesList = new ArrayList<>();

    public void saveCountryMoviesIntoList(int movieId, Map<String,Country> countryMap, String counryName){
        CountriesMovie countriesMovie = getNewCountryMovie();
        countriesMovie.setCountryId(countryMap.get(counryName).getCountryId());
        countriesMovie.setMovieId(movieId);
        countyMoviesList.add(countriesMovie);
    }

    private static CountriesMovie getNewCountryMovie(){
        return new CountriesMovie();
    }
    public List<CountriesMovie> returnMovieCountryList(){
        return countyMoviesList;
    }
}
