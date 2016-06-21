package com.dsliusar.persistence.dao.jdbc.impl;

import com.dsliusar.persistence.dao.MovieDao;
import com.dsliusar.persistence.dao.jdbc.builder.SearchQueryBuilder;
import com.dsliusar.persistence.dao.jdbc.builder.SortingQueryBuilder;
import com.dsliusar.persistence.dao.jdbc.mapper.MovieMapper;
import com.dsliusar.persistence.entity.Country;
import com.dsliusar.persistence.entity.Genre;
import com.dsliusar.persistence.entity.Movie;
import com.dsliusar.http.entities.MovieSearchRequestDto;
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

    @Override
    public void insert(Map<String, Movie> movieMap, Map<String, Country> countryMap, Map<String, Genre> mapGenre) {
        LOGGER.info("Start inserting into Movie table ");
        for (Map.Entry<String, Movie> arrMovie : movieMap.entrySet()) {
            jdbcTemplate.update(insertMovieSQL, arrMovie.getValue().getMovieId(),
                    arrMovie.getValue().getMovieNameRus(),
                    arrMovie.getValue().getMovieNameOrigin(),
                    arrMovie.getValue().getYear(),
                    arrMovie.getValue().getDescription(),
                    arrMovie.getValue().getRating(),
                    arrMovie.getValue().getPrice());
            insertCountryMovie(countryMap, arrMovie.getValue().getCountryList(), arrMovie.getValue().getMovieId());
            insertGenreMovie(mapGenre, arrMovie.getValue().getGenreList(), arrMovie.getValue().getMovieId());
            if (LOGGER.isDebugEnabled()){
                LOGGER.info("Next rows were inserted to movie " + arrMovie);
            }
        }

        LOGGER.info("All rows to movie were inserted");

    }

    private void insertCountryMovie(Map<String, Country> countryMap, List<Country> countryList, int movieId) {
        LOGGER.info("Start populating countries_movie_mapper table");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        for (Country country : countryList) {
            parameterSource.addValue("country_id", countryMap.get(country.getCountryName()).getCountryId());
            parameterSource.addValue("movie_id", movieId);
            namedJdbcTemplate.update(insertCountryMovieSQL, parameterSource);
            if (LOGGER.isDebugEnabled()){
                LOGGER.info("Next rows were inserted to country_movie " + country);
            }
        }

        LOGGER.info("All rows to country_movie were inserted");
    }

    private void insertGenreMovie(Map<String, Genre> mapGenre, List<Genre> movieGenreList, int movieId) {
        LOGGER.info("Start inserting into genre_movies table");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        for (Genre genre : movieGenreList) {
            parameterSource.addValue("genre_id", mapGenre.get(genre.getName()).getGenreId());
            parameterSource.addValue("movie_id", movieId);
            namedJdbcTemplate.update(insertGenreMovieSQL, parameterSource);
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("Next rows were inserted to genre_movie " + genre);
            }
        }

        LOGGER.info("All rows to genre_movie were inserted");
    }

    @Override
    public List<Movie> getAllMovies(String ratingOrder, String priceOrder) {
        LOGGER.info("Start getting all movies from DB");
        long startTime = System.currentTimeMillis();
        List<Movie> allMovieList = jdbcTemplate.query(new SortingQueryBuilder(getAllMoviesSQL, ratingOrder, priceOrder).movieSortingQueryBuilder(),movieMapper);
        LOGGER.info("Finish getting all rows from Movie, it took {} ms ", System.currentTimeMillis() - startTime);
        return allMovieList;
    }

    @Override
    public List<Movie> getSearchedMovies(MovieSearchRequestDto movieSearchRequest) {
        LOGGER.info("Start getting all movies from by next search criteria {}", movieSearchRequest);
        long startTime = System.currentTimeMillis();
        List<Movie> allMovieList = jdbcTemplate.query(new SearchQueryBuilder(getAllMoviesSQL, movieSearchRequest).movieSearchQueryBuilder(), movieMapper);
        LOGGER.info("Finish getting all rows from Movie, it took {} ms ", System.currentTimeMillis() - startTime);
        return allMovieList;
    }

    @Override
    public Movie getById(int id) {
        LOGGER.info("Start getting all movies from DB");
        long startTime = System.currentTimeMillis();
        Movie movie = jdbcTemplate.queryForObject(getMovieById, new Object[]{id}, movieMapper);
        LOGGER.info("Finish getting all rows from Movie, it took {} ms ", System.currentTimeMillis() - startTime);
        return movie;
    }
}
