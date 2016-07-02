package com.dsliusar.persistence.dao.jdbc.impl;

import com.dsliusar.tools.http.entities.AllMoviesRequestDto;
import com.dsliusar.persistence.dao.MovieDao;
import com.dsliusar.persistence.dao.jdbc.builder.SearchQueryBuilder;
import com.dsliusar.persistence.dao.jdbc.builder.SortingPaginationQueryBuilder;
import com.dsliusar.persistence.dao.jdbc.mapper.MovieMapper;
import com.dsliusar.persistence.entity.Country;
import com.dsliusar.persistence.entity.Genre;
import com.dsliusar.persistence.entity.Movie;
import com.dsliusar.tools.http.entities.MovieSearchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class JdbcMovieDao implements MovieDao {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String insertMovieSQL;

    @Autowired
    private String getAllMoviesSQL;

    @Autowired
    private String getMovieById;

    @Autowired
    private String insertCountryMovieSQL;

    @Autowired
    private String insertGenreMovieSQL;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private SortingPaginationQueryBuilder sortingQueryBuilder;

    @Autowired
    private SearchQueryBuilder searchQueryBuilder;

    @Autowired
    private String updateMovieCurrentFlagByIdSQL;

    @Autowired
    private String getUserMovieRating;

    @Autowired
    private String insertMovieAuditSQL;

    @Autowired
    private String deleteInvalidMoviesSQL;

    @Autowired
    private String getAllInvalidMoviesSQL;

    /**
     * Adding new movies to movie table
     * @param movieMap
     * @param countryMap
     * @param mapGenre
     */
    @Override
    public void addMovie(Map<String, Movie> movieMap, Map<String, Country> countryMap, Map<String, Genre> mapGenre) {
        LOGGER.info("Start inserting into Movie table ");
        long startTime = System.currentTimeMillis();
        for (Map.Entry<String, Movie> arrMovie : movieMap.entrySet()) {
            jdbcTemplate.update(insertMovieSQL, arrMovie.getValue().getMovieId(),
                    arrMovie.getValue().getMovieNameRus(),
                    arrMovie.getValue().getMovieNameOrigin(),
                    arrMovie.getValue().getYear(),
                    arrMovie.getValue().getDescription(),
                    arrMovie.getValue().getRating(),
                    arrMovie.getValue().getPrice());
            addMovieCountries(countryMap, arrMovie.getValue().getCountryList(), arrMovie.getValue().getMovieId());
            addMovieGenres(mapGenre, arrMovie.getValue().getGenreList(), arrMovie.getValue().getMovieId());
        }
        LOGGER.info("All rows to movie were inserted");
        LOGGER.info("Finished inserting into Movie table, it took {} ", System.currentTimeMillis() - startTime);

    }

    /**
     * Adding new movie to the movie table
     * @param movie
     */
    @Override
    public void addMovie(Movie movie) {
        LOGGER.info("Start inserting into Movie table ");
        long startTime = System.currentTimeMillis();
        jdbcTemplate.update(insertMovieSQL, movie.getMovieId(),
                movie.getMovieNameRus(),
                movie.getMovieNameOrigin(),
                movie.getYear(),
                movie.getDescription(),
                movie.getRating(),
                movie.getPrice());
        LOGGER.info("Finished inserting into Movie table, it took {} ", System.currentTimeMillis() - startTime);
    }

    /**
     * Populate movie_country_mapper table
     * @param countryMap
     * @param countryList
     * @param movieId
     */
    private void addMovieCountries(Map<String, Country> countryMap, List<Country> countryList, int movieId) {
        LOGGER.info("Start populating countries_movie_mapper table");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        long startTime = System.currentTimeMillis();
        for (Country country : countryList) {
            parameterSource.addValue("country_id", countryMap.get(country.getCountryName()).getCountryId());
            parameterSource.addValue("movie_id", movieId);
            namedJdbcTemplate.update(insertCountryMovieSQL, parameterSource);
        }
        LOGGER.info("All rows to country_movie were inserted, it took {} ", System.currentTimeMillis() - startTime);
    }

    /**
     * Populate movie_genre table
     * @param mapGenre
     * @param movieGenreList
     * @param movieId
     */
    private void addMovieGenres(Map<String, Genre> mapGenre, List<Genre> movieGenreList, int movieId) {
        LOGGER.info("Start inserting into genre_movies table");
        long startTime = System.currentTimeMillis();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        for (Genre genre : movieGenreList) {
            parameterSource.addValue("genre_id", mapGenre.get(genre.getName()).getGenreId());
            parameterSource.addValue("movie_id", movieId);
            namedJdbcTemplate.update(insertGenreMovieSQL, parameterSource);
        }
        LOGGER.info("All rows to genre_movie were inserted, it took {}", System.currentTimeMillis() - startTime);
    }

    /**
     * Populate movie_genre table
     * @param movieId
     * @param genreId
     */
    @Override
    public void addMovieGenres(int movieId, int genreId) {
        LOGGER.info("Start inserting into genre_movies table");
        long startTime = System.currentTimeMillis();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("genre_id", genreId);
        parameterSource.addValue("movie_id", movieId);
        namedJdbcTemplate.update(insertGenreMovieSQL, parameterSource);
        LOGGER.info("All rows to genre_movie were inserted, it took {}", System.currentTimeMillis() - startTime);
    }

    /**
     * Populate values to Movie_county_mapper table
     * @param movieId
     * @param countryId
     */
    @Override
    public void addMovieCountries(int movieId, int countryId) {
        LOGGER.info("Start populating countries_movie_mapper table");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        long startTime = System.currentTimeMillis();
        parameterSource.addValue("country_id", countryId);
        parameterSource.addValue("movie_id", movieId);
        namedJdbcTemplate.update(insertCountryMovieSQL, parameterSource);
        LOGGER.info("All rows to country_movie were inserted, it took {} ", System.currentTimeMillis() - startTime);
    }

    /**
     * Get all not valid movies
     * @return list of all invalid movies
     */
    public List<Movie> getAllInvalidMovies() {
        LOGGER.info("Start getting all movies from DB");
        long startTime = System.currentTimeMillis();
        List<Movie> allMovieList = jdbcTemplate.query(getAllInvalidMoviesSQL, movieMapper);
        LOGGER.info("Finish getting all rows from Movie, it took {} ms ", System.currentTimeMillis() - startTime);
        return allMovieList;
    }

    /**
     * Audit all not valid movies to movie_audit table
     * @param movie
     */
    @Override
    public void auditMovies(Movie movie) {
        LOGGER.info("Start auditing invalidated movies row to movie_audit table ");
        long startTime = System.currentTimeMillis();
        jdbcTemplate.update(insertMovieAuditSQL,
                movie.getMovieId(),
                movie.getMovieNameRus(),
                movie.getMovieNameOrigin(),
                movie.getYear(),
                movie.getDescription(),
                movie.getRating(),
                movie.getPrice(),
                movie.getLastUpdateTs());
        LOGGER.info("Finished inserting into Movie_audit table, it took {} ", System.currentTimeMillis() - startTime);
    }

    /**
     * Deleting movies which are not valid where current_flag = N
     */
    @Override
    public void deleteNotCurrentMovies() {
        LOGGER.info("Start deleting invalid movies");
        long startTime = System.currentTimeMillis();
        jdbcTemplate.update(deleteInvalidMoviesSQL);
        LOGGER.info("All invalid movies were deleted, it took {}",System.currentTimeMillis() - startTime );
    }

    @Override
    public List<Movie> getAllMovies(AllMoviesRequestDto movieSortRequest) {
        LOGGER.info("Start getting all movies from DB");
        long startTime = System.currentTimeMillis();
        List<Movie> allMovieList = jdbcTemplate.query(sortingQueryBuilder.movieSortingPaginationQueryBuilder
                (getAllMoviesSQL, movieSortRequest), movieMapper);
        LOGGER.info("Finish getting all rows from Movie, it took {} ms ", System.currentTimeMillis() - startTime);
        return allMovieList;
    }

    @Override
    public List<Movie> getSearchedMovies(MovieSearchRequest movieSearchRequest) {
        LOGGER.info("Start getting all movies from by next search criteria {}", movieSearchRequest);
        long startTime = System.currentTimeMillis();
        List<Movie> allMovieList = jdbcTemplate.query(searchQueryBuilder.movieSearchQueryBuilder
                (getAllMoviesSQL, movieSearchRequest), movieMapper);
        LOGGER.info("Finish getting all rows from Movie, it took {} ms ", System.currentTimeMillis() - startTime);
        return allMovieList;
    }

    @Override
    public Movie getById(int movieId) {
        LOGGER.info("Start getting all movies from DB");
        long startTime = System.currentTimeMillis();
        Movie movie = null;
        try {
            movie = jdbcTemplate.queryForObject(getMovieById, new Object[]{movieId}, movieMapper);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.info("No data found in movie table by movie Id {}", movieId);
        }
        LOGGER.info("Finish getting all rows from Movie, it took {} ms ", System.currentTimeMillis() - startTime);
        return movie;
    }

    @Override
    public Double getUserMovieRating(int userId, int movieId) {
        LOGGER.info("Start getting user movie with id {} raring from DB for user {}",movieId ,userId);
        long startTime = System.currentTimeMillis();
        Double userMovieRating = null;
        try {
            userMovieRating = jdbcTemplate.queryForObject(getUserMovieRating, new Object[]{userId, movieId}, Double.class);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.info("No rating found for user {} for movieId {}", userId, movieId);
        }
        LOGGER.info("User movie rating got from db for user {}, it took {}",
                                                        userId,
                                                        System.currentTimeMillis() - startTime);
        return userMovieRating;

    }

    @Override
    public void updateCurrentFlag(int movieId) {
        LOGGER.info("Start updating average Rating");
        long startTime = System.currentTimeMillis();
        jdbcTemplate.update(updateMovieCurrentFlagByIdSQL, movieId);
        LOGGER.info("Average Rating has been updated, it took {}", System.currentTimeMillis() - startTime);
    }
}
