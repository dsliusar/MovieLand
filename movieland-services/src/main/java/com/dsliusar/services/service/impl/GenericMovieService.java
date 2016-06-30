package com.dsliusar.services.service.impl;

import com.dsliusar.tools.http.entities.MovieSearchRequest;
import com.dsliusar.tools.http.entities.MovieSortRequest;
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


    /**
     * Get All movies from database
     * of Sort Criteria exists then on DAO layer generate sort criteria
     * @param movieSortRequest
     * @return
     */
    @Override
    public List<Movie> getAllMovies(MovieSortRequest movieSortRequest) {

        List<Movie> movieList = jdbcMovieDao.getAllMovies(movieSortRequest);
        for(Movie movie : movieList){
            movie.setGenreList(cacheableGenreService.getGenresByMovieId(movie.getMovieId()));
        }
        return movieList;

    }

    /**
     * Get All movies by Search Criteria
     * All movies are stored in the List
     * @param movieSearchRequest
     * @return
     */
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

    /**
     * Getting movie object by ID
     * @param movieId
     * @return
     */
    @Override
    public Movie getMovieById(int movieId) {
        Movie movie = jdbcMovieDao.getById(movieId);
        movie.setGenreList(cacheableGenreService.getGenresByMovieId(movie.getMovieId()));
        movie.setCountryList(cacheableCountryService.getAllCountriesByMovieId(movie.getMovieId()));
        movie.setReviewText(cacheableReviewService.getAllReviewByMovieId(movie.getMovieId()));
        return movie;
    }

    /**
     * Updating current flag row in movie table to N
     * @param movieId
     */
    @Override
    public void updateCurrentFlag(int movieId) {
        jdbcMovieDao.updateCurrentFlag(movieId);
    }

    /**
     * Adding movie to database
     * @param movie
     */
    @Override
    public void addMovie(Movie movie) {
       jdbcMovieDao.addMovie(movie);
    }


    /**
     * Updating current row of the movie id to flag N - invalidation row
     * Inserting new row with movied with new calculated rating.
     * @param movieId
     * @param usersRating
     * @return
     */
    @Override
    public double updateAverageRating(int movieId, List<Double> usersRating) {
        double avgRating = calculateAvgRating(usersRating);
        Movie movie = jdbcMovieDao.getById(movieId);
        updateCurrentFlag(movieId);
        movie.setRating(avgRating);
        addMovie(movie);
        return avgRating;
    }


    /**
     * Calculating average rating for the movie based on users ratings
     * @param usersRatings
     * @return
     */
    private double calculateAvgRating(List<Double> usersRatings){
        double avgRating = 0;
        int userCount = 0;
        for (Double usersRating : usersRatings) {
             avgRating += usersRating;
             userCount++;
        }
        avgRating /= userCount;
        return avgRating;
    }


}
