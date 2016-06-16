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

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SimpleMovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Autowired
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
    public List<Movie> getAllSearchedMovies(String movieNameRus, String movieNameOrigin
                                           ,String country, Integer year, String genreName) {
        Map<String,Integer> allCounties = null;
        Map<String,Integer> allGenres = null;

        if(country != null){
            allCounties = simpleCountryService.getAllCountries();
        }
        if(genreName != null) {
            allGenres = simpleGenreService.getAllGenres();
        }
        List<Movie> movieList = movieDao.getSearchedMovies(movieNameRus,movieNameOrigin,year,allGenres.get(genreName), allCounties.get(country));
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
