package com.dsliusar.service.impl;

import com.dsliusar.dao.MovieDao;
import com.dsliusar.entity.Movie;
import com.dsliusar.httpEntities.MovieSearchRequestDto;
import com.dsliusar.service.CountryService;
import com.dsliusar.service.GenreService;
import com.dsliusar.service.MovieService;
import com.dsliusar.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private GenreService cacheableGenreService;


    @Override
    public List<Movie> getAllMovies(String ratingOrder,String priceOrder) {
        List<Movie> movieList = movieDao.getAllMovies(ratingOrder,priceOrder);
        for(Movie movie : movieList){
            movie.setGenreList(simpleGenreService.getGenresByMovieId(movie.getMovieId()));
        }
        cacheableGenreService.getAllGenres();
        return movieList;
    }

    @Override
    public List<Movie> getAllSearchedMovies(MovieSearchRequestDto movieSearchRequest) {
        Map<String,Integer> allCounties = null;
        Map<String,Integer> allGenres = null;

        if(movieSearchRequest.getCountry() != null){
            allCounties = simpleCountryService.getAllCountries();
            movieSearchRequest.setCountryId(allCounties.get(movieSearchRequest.getCountry()));
        }
        if(movieSearchRequest.getGenreName() != null) {
            allGenres = simpleGenreService.getAllGenres();
            movieSearchRequest.setGenreId(allGenres.get(movieSearchRequest.getGenreName()));
        }
        List<Movie> movieList = movieDao.getSearchedMovies(movieSearchRequest);
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
