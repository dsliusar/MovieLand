package com.dsliusar.file.services;

import com.dsliusar.dao.*;
import com.dsliusar.dao.jdbc.impl.*;
import com.dsliusar.entity.Movie;
import com.dsliusar.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MovieLandService {

    @Autowired
    private UserDao jdbcUserDao;

    @Autowired
    private GenreDao jdbcGenreDao;

    @Autowired
    private GenreMovieDao jdbcGenreMovieDao;

    @Autowired
    private MovieDao jdbcMovieDao;

    @Autowired
    private ReviewDao jdbcReviewDao;

    @Autowired
    private CountryDao jdbcCountryDao;

    @Autowired
    private CountryMovieDao jdbcCountryMovieDao;

    public void performFillMovieLand() {
        jdbcUserDao.insert();
        jdbcGenreDao.insert();
        jdbcMovieDao.insert();
        jdbcGenreMovieDao.insert();
        jdbcReviewDao.insert();
        jdbcCountryDao.insert();
        jdbcCountryMovieDao.insert();


        Movie movie = new Movie();
        movie = jdbcMovieDao.getById(1);
        System.out.println(movie);

        List<Review> reviewList = new ArrayList<>();
        //reviewList.add(jdbcReviewDao.getReviewsByMovieId(movie.getMovieId()));
        System.out.println(jdbcReviewDao.getReviewsByMovieId(movie.getMovieId()));

    }

    public void setJdbcUserDao(JdbcUserDao jdbcUserDao) {
        this.jdbcUserDao = jdbcUserDao;
    }

    public void setJdbcGenreDao(JdbcGenreDao jdbcGenreDao) {
        this.jdbcGenreDao = jdbcGenreDao;
    }

    public void setJdbcMovieDao(JdbcMovieDao jdbcMovieDao) {
        this.jdbcMovieDao = jdbcMovieDao;
    }

    public void setJdbcGenreMovieDao(JdbcGenreMovieDao jdbcGenreMovieDao) {
        this.jdbcGenreMovieDao = jdbcGenreMovieDao;
    }

    public void setJdbcReviewDao(JdbcReviewDao jdbcReviewDao) {
        this.jdbcReviewDao = jdbcReviewDao;
    }

    public void setJdbcCountryDao(JdbcCountryDao jdbcCountryDao) {
        this.jdbcCountryDao = jdbcCountryDao;
    }

    public void setJdbcCountryMovieDao(JdbcCountryMovieDao jdbcCountryMovieDao) {
        this.jdbcCountryMovieDao = jdbcCountryMovieDao;
    }
}

