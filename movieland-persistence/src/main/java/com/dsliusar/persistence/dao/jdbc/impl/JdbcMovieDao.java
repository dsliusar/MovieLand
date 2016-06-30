package com.dsliusar.persistence.dao.jdbc.impl;

import com.dsliusar.tools.http.entities.MovieSortRequest;
import com.dsliusar.persistence.dao.MovieDao;
import com.dsliusar.persistence.dao.jdbc.builder.SearchQueryBuilder;
import com.dsliusar.persistence.dao.jdbc.builder.SortingQueryBuilder;
import com.dsliusar.persistence.dao.jdbc.mapper.MovieMapper;
import com.dsliusar.persistence.entity.Country;
import com.dsliusar.persistence.entity.Genre;
import com.dsliusar.persistence.entity.Movie;
import com.dsliusar.tools.http.entities.MovieSearchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private SortingQueryBuilder sortingQueryBuilder;

    @Autowired
    private SearchQueryBuilder searchQueryBuilder;

    @Autowired
    private String updateMovieCurrentFlagByIdSQL;

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

    @Override
    public List<Movie> getAllMovies(MovieSortRequest movieSortRequest) {
        LOGGER.info("Start getting all movies from DB");
        long startTime = System.currentTimeMillis();
        List<Movie> allMovieList = jdbcTemplate.query(sortingQueryBuilder.movieSortingQueryBuilder
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
        Movie movie = jdbcTemplate.queryForObject(getMovieById, new Object[]{movieId}, movieMapper);
        LOGGER.info("Finish getting all rows from Movie, it took {} ms ", System.currentTimeMillis() - startTime);
        return movie;
    }

    @Override
    public void updateCurrentFlag(int movieId) {
        LOGGER.info("Start updating average Rating");
        long startTime = System.currentTimeMillis();
        jdbcTemplate.update(updateMovieCurrentFlagByIdSQL, movieId);
        LOGGER.info("Average Rating has been updated, it took {}", System.currentTimeMillis() - startTime);
    }
}
