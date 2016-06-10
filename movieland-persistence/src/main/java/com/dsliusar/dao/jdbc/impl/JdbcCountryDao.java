package com.dsliusar.dao.jdbc.impl;

import com.dsliusar.dao.CountryDao;
import com.dsliusar.entity.Country;
import com.dsliusar.dao.files.CountryParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by Red1 on 6/8/2016.
 */

@Repository
public class JdbcCountryDao implements CountryDao {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private CountryParser countriesParser;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String insertCountrySQL;

    @Override
    public void insert() {
        truncateCommon(jdbcTemplate, "countries");
        LOGGER.info("Start populating Countries Table");
        Map<String,Country> countryMap = countriesParser.getCountryMap();
        for (Map.Entry<String, Country> country : countryMap.entrySet()) {
                jdbcTemplate.update(insertCountrySQL, new Object[]{
                                                    country.getValue().getCountryId(),
                                                    country.getValue().getCountryName()});
          LOGGER.info("Next rows were inserted into counties" + country);
        }
    }

   public void setCountriesParser(CountryParser countriesParser) {
        this.countriesParser = countriesParser;
    }
}
