package com.dsliusar.service.impl;

import com.dsliusar.dao.MovieDao;
import com.dsliusar.entity.Movie;
import com.dsliusar.service.CountryService;
import com.dsliusar.service.GenreService;
import com.dsliusar.service.MovieService;
import com.dsliusar.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SimpleMovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    @Qualifier("simpleGenreService")
    private GenreService simpleGenreService;

    @Autowired
    private CountryService simpleCountryService;

    @Autowired
    private ReviewService simpleReviewService;

    @Override
    public List<Movie> getAllMovies(String ratingOrder,String priceOrder) {
        List<Movie> movieList = movieDao.getAllMovies(ratingOrder,priceOrder);
        for(Movie movie : movieList){
            movie.setGenreList(simpleGenreService.getGenresByMovieId(movie.getMovieId()));
        }
        return movieList;
    }

    @Override
    public Movie getMovieById(int id) {
        Movie movie = movieDao.getById(id);
        movie.setGenreList(simpleGenreService.getGenresByMovieId(movie.getMovieId()));
        movie.setCountryList(simpleCountryService.getAllCountriesByMovieId(movie.getMovieId()));
        movie.setReviewText(simpleReviewService.getAllReviewByMovieId(movie.getMovieId()));
        return movie;


    }
}
