package com.dsliusar.dao.files.impl;

import com.dsliusar.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CountryParser {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private Map<String,Country> countryMap = new HashMap<>();
    private int countryIdGen;

//    private List<CountriesMovie> countyMoviesList = new ArrayList<>();
//
//    public void saveCountryMoviesIntoList(int movieId, Map<String,Country> countryMap, String counryName){
//        CountriesMovie countriesMovie = getNewCountryMovie();
//        countriesMovie.setCountryId(countryMap.get(counryName).getCountryId());
//        countriesMovie.setMovieId(movieId);
//        countyMoviesList.add(countriesMovie);
//    }
//
//    private static CountriesMovie getNewCountryMovie(){
//        return new CountriesMovie();
//    }
//
//    public List<CountriesMovie> returnMovieCountryList(){
//        return countyMoviesList;
//    }

    public Map<String,Country> saveCountriesIntoList(String countryName, int movieId) {
        LOGGER.info("Start parsing array of countries = {}", countryName);
         if(!countryMap.containsKey(countryName))
                {
                    addCountry(countryName);
                }

        LOGGER.info("Next array of countries =  {}, successfully parsed", countryName);
        return countryMap;
    }

    public Map<String,Country> getCountryMap() {
        return countryMap;
    }

    private void addCountry(String countryName) {
        countryIdGen++;
        Country country = new Country();
        country.setCountryId(countryIdGen);
        country.setCountryName(countryName);
        countryMap.put(countryName, country);
    }
}
