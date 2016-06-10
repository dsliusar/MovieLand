package com.dsliusar.dao.jdbc.impl;

import com.dsliusar.dao.CountryMovieDao;
import com.dsliusar.entity.CountriesMovie;
import com.dsliusar.dao.files.CountryMovieParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Red1 on 6/8/2016.
 */

@Repository
public class JdbcCountryMovieDao implements CountryMovieDao {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private CountryMovieParser countryMovieParser;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String insertCountryMovieSQL;

    @Override
    public void insert() {
        truncateCommon(jdbcTemplate, "countries_movie_mapper");

        LOGGER.info("Start populating countries_movie_mapper table");

        List<CountriesMovie> countriesMovieList = countryMovieParser.returnMovieCountryList();
        for (CountriesMovie countriesMovie : countriesMovieList) {
                jdbcTemplate.update(insertCountryMovieSQL, new Object[]{
                countriesMovie.getCountryId(),
                countriesMovie.getMovieId()});
           LOGGER.info("Next rows were inserted " + countriesMovie);
        }
    }

    public void setCountryMovieParser(CountryMovieParser countryMovieParser) {
        this.countryMovieParser = countryMovieParser;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setInsertCountryMovieSQL(String insertCountryMovieSQL) {
        this.insertCountryMovieSQL = insertCountryMovieSQL;
    }
}
