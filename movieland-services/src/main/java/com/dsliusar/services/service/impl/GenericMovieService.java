package com.dsliusar.services.service.impl;

import com.dsliusar.persistence.dao.MovieDao;
import com.dsliusar.persistence.entity.Movie;
import com.dsliusar.services.cache.executor.CountryCacheUpdateService;
import com.dsliusar.services.cache.executor.GenreCacheUpdateService;
import com.dsliusar.services.cache.executor.ReviewCacheUpdateService;
import com.dsliusar.services.service.CountryService;
import com.dsliusar.services.service.GenreService;
import com.dsliusar.services.service.MovieService;
import com.dsliusar.services.service.ReviewService;
import com.dsliusar.tools.entities.report.AllSiteMovies;
import com.dsliusar.tools.exceptions.RequestException;
import com.dsliusar.tools.entities.http.MovieAddRequest;
import com.dsliusar.tools.entities.http.MovieSearchRequest;
import com.dsliusar.tools.entities.http.AllMoviesRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GenericMovieService implements MovieService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieDao jdbcMovieDao;

    @Autowired
    private GenreService cacheableGenreService;

    @Autowired
    private CountryService cacheableCountryService;

    @Autowired
    private ReviewService cacheableReviewService;

    @Autowired
    private CountryCacheUpdateService countryCacheUpdateService;

    @Autowired
    private GenreCacheUpdateService genreCacheUpdateService;

    @Autowired
    private ReviewCacheUpdateService reviewCacheUpdateService;


    /**
     * Get All movies from database
     * of Sort Criteria exists then on DAO layer generate sort criteria including pagination
     *
     * @param movieSortRequest
     * @return
     */
    @Override
    public List<Movie> getAllMovies(AllMoviesRequestDto movieSortRequest) {
        List<Movie> movieList = jdbcMovieDao.getAllMovies(movieSortRequest);
        for (Movie movie : movieList) {
            movie.setGenreList(cacheableGenreService.getGenresByMovieId(movie.getMovieId()));
        }
        return movieList;
    }

    @Override
    public List<AllSiteMovies> getAllSiteMovies() {
      return jdbcMovieDao.getAllSiteMovies();
    }

    /**
     * Get All movies by Search Criteria
     * All movies are stored in the List
     *
     * @param movieSearchRequest
     * @return
     */
    @Override
    public List<Movie> getAllSearchedMovies(MovieSearchRequest movieSearchRequest) {

        if (movieSearchRequest.getCountry() != null) {
            movieSearchRequest.setCountryId(cacheableCountryService.getAllCountries().
                    get(movieSearchRequest.getCountry()));
        }
        if (movieSearchRequest.getGenreName() != null) {
            movieSearchRequest.setGenreId(cacheableGenreService.getAllGenres().
                    get(movieSearchRequest.getGenreName()));
        }
        List<Movie> movieList = jdbcMovieDao.getSearchedMovies(movieSearchRequest);

        for (Movie movie : movieList) {
            movie.setGenreList(cacheableGenreService.getGenresByMovieId(movie.getMovieId()));
        }

        return movieList;
    }

    /**
     * Getting movie object by ID
     *
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
     * Getting specific rating set by user for movie
     *
     * @param userId
     * @param movieId
     * @return
     */
    @Override
    public Double getUserRating(int userId, int movieId) {
        return jdbcMovieDao.getUserMovieRating(userId, movieId);
    }

    /**
     * Updating current flag row in movie table to N
     *
     * @param movieId
     */
    @Override
    public void updateCurrentFlag(int movieId) {
        jdbcMovieDao.updateCurrentFlag(movieId);
    }

    /**
     * Add movie to DB
     * @param movie
     */
    private void addMovie(Movie movie) {
        jdbcMovieDao.addMovie(movie);
    }

    /**
     * Adding movie to database
     * User provde movie with genres,countries and all movie details and movie is inserted to DB in
     * all relevant tables where movie mapped to the another entities
     *
     * @param movieAddRequest
     */
    @Override
    public void addMovie(MovieAddRequest movieAddRequest) {
        LOGGER.info("Checking if movie already exists in the DB");
        Movie movie = jdbcMovieDao.getById(movieAddRequest.getMovieId());

        if (movie == null) {
            //Set movie entity (constructor set) and add movie to the DB
            addMovie(new Movie(movieAddRequest));

            //Add Movie Genres to mapper table
            addMovieGenres(movieAddRequest.getMovieId(), movieAddRequest.getGenres());

            //Add Movie Countries to mapper table
            addMovieCountries(movieAddRequest.getMovieId(), movieAddRequest.getCountries());

            //Update all movies Related caches
            updateMoviesRelatedCaches();

        } else {
            LOGGER.info("Movie already exists and cannot be added");
            throw new RequestException("Requested movie already exists in DB, movieID = " + movieAddRequest.getMovieId());
        }

    }

    /**
     * Updating movie in DB
     *
     * @param movieAddRequest
     */
    @Override
    public void updateMovie(MovieAddRequest movieAddRequest) {
        LOGGER.info("Checking if movie already exists in the DB");
        Movie movie = jdbcMovieDao.getById(movieAddRequest.getMovieId());

        if (movie == null) {
            LOGGER.info("Movie do not exists in the DB, cannot update the movie");
            throw new RequestException("Requested movie do not exists in the DB, movieID = " + movieAddRequest.getMovieId());
            //Set movie entity (constructor set) and add movie to the DB

        } else {
            //Update current movie flag to N
            updateCurrentFlag(movieAddRequest.getMovieId());

            //Set movie entity (constructor set) and add movie to the DB
            addMovie(new Movie(movieAddRequest));

            //Add/update Movie Genres to mapper table
            addMovieGenres(movieAddRequest.getMovieId(), movieAddRequest.getGenres());

            //Add/update Movie Countries to mapper table
            addMovieCountries(movieAddRequest.getMovieId(), movieAddRequest.getCountries());

            //Update all movies Related caches
            updateMoviesRelatedCaches();
        }
    }

    /**
     * Updating cache once movie inserted/updated
     */
    private void updateMoviesRelatedCaches() {
        countryCacheUpdateService.invalidateCache();
        reviewCacheUpdateService.invalidateCache();
        genreCacheUpdateService.invalidateCache();
    }


    /**
     * Common method to add the movie genres to the DB
     *
     * @param movieId
     * @param genreId
     */
    private void addMovieGenres(int movieId, int genreId) {
        jdbcMovieDao.addMovieGenres(movieId, genreId);
    }

    /**
     * Parsing the list of genres and call method addMovieGenres
     *
     * @param movieId
     * @param genreList
     */
    private void addMovieGenres(int movieId, List<Integer> genreList) {
        for (Integer genreId : genreList) {
            addMovieGenres(movieId, genreId);
        }
    }

    /**
     * Parsing the list of countiesIds and call method addMovieCountries
     *
     * @param movieId
     * @param countryList
     */
    private void addMovieCountries(int movieId, List<Integer> countryList) {
        for (Integer countryId : countryList) {
            addMovieCountries(movieId, countryId);
        }
    }

    /**
     * Common method to insert into countries movie mapper table
     *
     * @param movieId
     * @param countryId
     */
    private void addMovieCountries(int movieId, int countryId) {
        jdbcMovieDao.addMovieCountries(movieId, countryId);
    }

    /**
     * Updating current row of the movie id to flag N - invalidation row
     * Inserting new row with movied with new calculated rating.
     *
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
     * Auditing movie table - saving all not valid movies into movie_audit table
     */
    public void performAuditOfMovies() {
        LOGGER.info("Start auditing movie table");
        int auditMovieCounter = 0;
        List<Movie> movies = jdbcMovieDao.getAllInvalidMovies();
        for (Movie movie : movies) {
            jdbcMovieDao.auditMovies(movie);
            auditMovieCounter++;
            LOGGER.info("Movie with next id {} were audited", movie.getMovieId());
        }
        jdbcMovieDao.deleteNotCurrentMovies();
        LOGGER.info("All invalid movies were audited, totally = {}", auditMovieCounter);
    }

    @Override
    public byte[] getMoviePoster(Integer movieId) {
        return jdbcMovieDao.getMoviePoster(movieId);
    }

    /**
     * Calculating average rating for the movie based on users ratings
     *
     * @param usersRatings
     * @return
     */
    private double calculateAvgRating(List<Double> usersRatings) {
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
