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
public class GenericMovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private GenreService cacheableGenreService;

    @Autowired
    private CountryService cacheableCountryService;

    @Autowired
    private ReviewService cacheableReviewService;


    @Override
    public List<Movie> getAllMovies(String ratingOrder,String priceOrder) {
        List<Movie> movieList = movieDao.getAllMovies(ratingOrder,priceOrder);
        for(Movie movie : movieList){
            movie.setGenreList(cacheableGenreService.getAllMoviesGenres().get(movie.getMovieId()));
        }
        cacheableGenreService.getAllGenres();
        return movieList;
    }

    @Override
    public List<Movie> getAllSearchedMovies(MovieSearchRequestDto movieSearchRequest) {
        Map<String,Integer> allCounties;
        Map<String,Integer> allGenres;

        if(movieSearchRequest.getCountry() != null){
            allCounties = cacheableCountryService.getAllCountries();
            movieSearchRequest.setCountryId(allCounties.get(movieSearchRequest.getCountry()));
        }
        if(movieSearchRequest.getGenreName() != null) {
            allGenres = cacheableGenreService.getAllGenres();
            movieSearchRequest.setGenreId(allGenres.get(movieSearchRequest.getGenreName()));
        }
        List<Movie> movieList = movieDao.getSearchedMovies(movieSearchRequest);
        for(Movie movie : movieList){
            movie.setGenreList(cacheableGenreService.getAllMoviesGenres().get(movie.getMovieId()));
        }
        return movieList;
    }

    @Override
    public Movie getMovieById(int id) {
        Movie movie = movieDao.getById(id);
        movie.setGenreList(cacheableGenreService.getAllMoviesGenres().get(movie.getMovieId()));
        movie.setCountryList(cacheableCountryService.getAllMoviesCountries().get(movie.getMovieId()));
        movie.setReviewText(cacheableReviewService.getAllMoviesReviews().get(movie.getMovieId()));
        return movie;


    }
}
