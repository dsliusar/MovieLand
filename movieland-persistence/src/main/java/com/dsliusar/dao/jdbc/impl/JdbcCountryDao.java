package com.dsliusar.dao.jdbc.impl;

import com.dsliusar.dao.CountryDao;
import com.dsliusar.entity.Country;
import com.dsliusar.dao.files.impl.CountryParser;
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
    public void insert( Map<String,Country> countryMap) {
        long startTime = System.currentTimeMillis();
        LOGGER.info("Start populating Countries Table");
        for (Map.Entry<String, Country> country : countryMap.entrySet()) {
                jdbcTemplate.update(insertCountrySQL,country.getValue().getCountryId(),
                                                     country.getValue().getCountryName());
          LOGGER.info("Next rows {} were inserted into counties, it took {} ",country, System.currentTimeMillis() - startTime);
        }
    }

   public void setCountriesParser(CountryParser countriesParser) {
        this.countriesParser = countriesParser;
    }
}
