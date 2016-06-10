package com.dsliusar.dao.files;

import com.dsliusar.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class CountryParser {

    @Autowired
    private CountryMovieParser countryMovieParser;

    private Map<String,Country> countryHashMap = new HashMap<>();
    private int countryIdGen;


    public void saveCountriesIntoList(String countryName, int movieId) {
        ArrayList<String> countriesNamesList = new ArrayList<>(Arrays.asList(countryName.split(",")));
        for (String countryRow : countriesNamesList) {
            countryRow = countryRow.trim();
             if(!countryHashMap.containsKey(countryRow))
             {
                addCountry(countryRow);
            }
            countryMovieParser.saveCountryMoviesIntoList(movieId, countryHashMap, countryRow);
        }
    }

    public Map<String,Country> getCountryMap() {
        return countryHashMap;
    }

    private void addCountry(String countryName) {
        countryIdGen++;
        Country country = getNewCountry();
        country.setCountryId(countryIdGen);
        country.setCountryName(countryName);
        countryHashMap.put(countryName, country);
    }

    private static Country getNewCountry() {
        return new Country();
    }
}
