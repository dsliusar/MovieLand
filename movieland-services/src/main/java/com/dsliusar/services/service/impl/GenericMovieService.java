package com.dsliusar.services.service.impl;

import com.dsliusar.http.entities.MovieSearchRequest;
import com.dsliusar.http.entities.MovieSortRequest;
import com.dsliusar.persistence.dao.MovieDao;
import com.dsliusar.persistence.entity.Movie;
import com.dsliusar.services.service.CountryService;
import com.dsliusar.services.service.GenreService;
import com.dsliusar.services.service.MovieService;
import com.dsliusar.services.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GenericMovieService implements MovieService {

    @Autowired
    private MovieDao jdbcMovieDao;

    @Autowired
    private GenreService cacheableGenreService;

    @Autowired
    private CountryService cacheableCountryService;

    @Autowired
    private ReviewService cacheableReviewService;


    @Override
    public List<Movie> getAllMovies(MovieSortRequest movieSortRequest) {

        List<Movie> movieList = jdbcMovieDao.getAllMovies(movieSortRequest);
        for(Movie movie : movieList){
            movie.setGenreList(cacheableGenreService.getGenresByMovieId(movie.getMovieId()));
        }
        return movieList;

    }

    @Override
    public List<Movie> getAllSearchedMovies(MovieSearchRequest movieSearchRequest) {

        if(movieSearchRequest.getCountry() != null){
            movieSearchRequest.setCountryId(cacheableCountryService.getAllCountries().
                    get(movieSearchRequest.getCountry()));
        }
        if(movieSearchRequest.getGenreName() != null) {
            movieSearchRequest.setGenreId( cacheableGenreService.getAllGenres().
                    get(movieSearchRequest.getGenreName()));
        }
        List<Movie> movieList = jdbcMovieDao.getSearchedMovies(movieSearchRequest);

        for(Movie movie : movieList){
            movie.setGenreList(cacheableGenreService.getGenresByMovieId(movie.getMovieId()));
        }

        return movieList;
    }

    @Override
    public Movie getMovieById(int id) {
        Movie movie = jdbcMovieDao.getById(id);
        movie.setGenreList(cacheableGenreService.getGenresByMovieId(movie.getMovieId()));
        movie.setCountryList(cacheableCountryService.getAllCountriesByMovieId(movie.getMovieId()));
        movie.setReviewText(cacheableReviewService.getAllReviewByMovieId(movie.getMovieId()));
        return movie;


    }
}
