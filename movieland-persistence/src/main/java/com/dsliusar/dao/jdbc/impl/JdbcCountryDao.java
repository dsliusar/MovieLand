package com.dsliusar.dao.jdbc.impl;

import com.dsliusar.dao.CountryDao;
import com.dsliusar.dao.jdbc.mapper.CountryMapRowMapper;
import com.dsliusar.dao.jdbc.mapper.CountryMapper;
import com.dsliusar.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Red1 on 6/8/2016.
 */

@Repository
public class JdbcCountryDao implements CountryDao {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String insertCountrySQL;

    @Autowired
    private String getAllCountriesByMovieId;

    @Autowired
    private String getAllCountiesSQL;

    @Autowired
    private String getAllMoviesCountries;

    @Autowired
    private CountryMapRowMapper countryMapRowMapper;

    @Override
    public void insert( Map<String,Country> countryMap) {
        long startTime = System.currentTimeMillis();
        LOGGER.info("Start populating Countries Table");
        for (Map.Entry<String, Country> country : countryMap.entrySet()) {
                jdbcTemplate.update(insertCountrySQL,country.getValue().getCountryId(),
                                                     country.getValue().getCountryName());
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Next rows {} were inserted into counties, it took {} ", country, System.currentTimeMillis() - startTime);
            }
            LOGGER.info("All rows were inserted to counties table");
        }
    }

    @Override
    public List<Country> getAllCountriesByMovieId(int movieId) {
        LOGGER.info("Start getting Review by Id");
        long startTime = System.currentTimeMillis();
        List<Country> allCountryList = jdbcTemplate.query(getAllCountriesByMovieId, new Object[]{movieId},new CountryMapper())  ;
        LOGGER.info("Reviews by Movie Id was received, it took {}", System.currentTimeMillis() - startTime);
        return allCountryList;
    }

    @Override
    public Map<String,Integer> getAllCountries(){
        LOGGER.info("Getting All Countries from DB ");
        long startTime = System.currentTimeMillis();
        Map<String,Integer> countiesMap = jdbcTemplate.query(getAllCountiesSQL, resultSet -> {
            HashMap<String,Integer> mapRet = new HashMap<>();
            while(resultSet.next()){
                mapRet.put( resultSet.getString("country_name"),resultSet.getInt("country_id"));
            }
            return mapRet;
        });

        LOGGER.info("All Countries were extracted from DB, it took {} ms ", System.currentTimeMillis() - startTime );
        return countiesMap;
    }

    @Override
    public Map<Integer, List<Country>> getAllMoviesCounties() {
        LOGGER.info("Get All Countries Map with Movie Id");
        long startTime = System.currentTimeMillis();
        Map<Integer, List<Country>> countriesMap = jdbcTemplate.query(getAllMoviesCountries, countryMapRowMapper);
        LOGGER.info("Finished getting all countries with movie id, it took {}", System.currentTimeMillis() - startTime);
        return countriesMap;
    }

}


