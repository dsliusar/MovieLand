package com.dsliusar.persistence.dao.jdbc.impl;

import com.dsliusar.persistence.dao.CountryDao;
import com.dsliusar.persistence.entity.Country;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-persistence-config.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class JdbcCountryDaoTest {

    @Autowired
    private CountryDao jdbcCountryDao;

    @Rollback
    @Test
    public void insertTest(){
        Country country = new Country();
        Map<String,Country> map = new HashMap<>();
        country.setCountryId(Integer.MAX_VALUE);
        country.setCountryName("My Country");
        map.put("My Country", country);
        jdbcCountryDao.insert(map);
    }

    @Test
    public void getAllCountriesByMovieId(){
        List<Country> allCountriesByMovieId = jdbcCountryDao.getAllCountriesByMovieId(1);
        Assert.assertNotNull(allCountriesByMovieId);
    }

    @Test
    public void getAllCountriesTest(){
        Map<String, Integer> allCountries = jdbcCountryDao.getAllCountries();
        Assert.assertNotNull(allCountries);
    }

    @Test
    public void getAllMoviesCountriesTest(){
        Map<Integer, List<Country>> allMoviesCounties = jdbcCountryDao.getAllMoviesCounties();
        Assert.assertNotNull(allMoviesCounties);
    }

}
