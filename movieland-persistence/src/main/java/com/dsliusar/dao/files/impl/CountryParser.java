package com.dsliusar.dao.files.impl;

import com.dsliusar.entity.Country;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CountryParser {

    private Map<String,Country> countryMap = new HashMap<>();
    private int countryIdGen;

    public Map<String,Country> saveCountriesIntoList(String countryName, int movieId) {
         if(!countryMap.containsKey(countryName))
                {
                    addCountry(countryName);
                }

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
