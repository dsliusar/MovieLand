package com.dsliusar.persistence.dao.jdbc.impl;

import com.dsliusar.persistence.dao.GenreDao;
import com.dsliusar.persistence.entity.Genre;
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
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-persistence-config.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class JdbcGenreDaoTest {

    @Autowired
    private GenreDao jdbcGenreDao;

    @Rollback
    @Test
    public void insertTest(){
        Map<String,Genre> map = new HashMap<>();
        Genre genre = new Genre();
        genre.setGenreId(new Random().nextInt());
        genre.setName("comics");
        map.put("comics",genre);
        jdbcGenreDao.insert(map);
    }

    @Test
    public void getAllGenresTest(){
        Map<String, Integer> allGenres = jdbcGenreDao.getAllGenres();
        Assert.assertNotNull(allGenres);
    }

    @Test
    public void getGenreByMovieIdTest(){
        List<Genre> genresByMovieId = jdbcGenreDao.getGenresByMovieId(1);
        Assert.assertNotNull(genresByMovieId);
    }

    @Test
    public void getGenreWithMovieIdTest(){
        Map<Integer, List<Genre>> genreWithMovieId = jdbcGenreDao.getGenreWithMovieId();
        Assert.assertNotNull(genreWithMovieId);
    }

}
