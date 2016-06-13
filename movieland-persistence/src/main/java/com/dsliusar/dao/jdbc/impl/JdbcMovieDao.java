package com.dsliusar.dao.jdbc.impl;

import com.dsliusar.dao.MovieDao;
import com.dsliusar.dao.files.impl.MovieFileParser;
import com.dsliusar.dao.jdbc.mapper.AllMovieMapper;
import com.dsliusar.dao.jdbc.mapper.SingleMovieMapper;
import com.dsliusar.entity.Country;
import com.dsliusar.entity.Genre;
import com.dsliusar.entity.Movie;
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
    private MovieFileParser movieFileParser;

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


    @Override
    public void insert(Map<String, Movie> movieMap, Map<String,Country> countryMap, Map<String, Genre> mapGenre) {
        LOGGER.info("Start inserting into Movie table ");
        movieFileParser.parseMoviesIntoList();
        Map<String, Movie> moviesMap = movieFileParser.getParsedMovieMap();

        for (Map.Entry<String, Movie> arrMovie : moviesMap.entrySet()) {
            jdbcTemplate.update(insertMovieSQL, arrMovie.getValue().getMovieId(),
                    arrMovie.getValue().getMovieNameRus(),
                    arrMovie.getValue().getMovieNameOrigin(),
                    arrMovie.getValue().getYear(),
                    arrMovie.getValue().getDesciprtion(),
                    arrMovie.getValue().getRating(),
                    arrMovie.getValue().getPrice());
            insertCountryMovie(countryMap, arrMovie.getValue().getCountryList(),arrMovie.getValue().getMovieId());
            insertGenreMovie(mapGenre, arrMovie.getValue().getGenreList(), arrMovie.getValue().getMovieId());
            LOGGER.info("Next row was inserted into Movies " + arrMovie);
        }


    }

    private void insertCountryMovie(Map<String,Country> countryMap, List<Country> countryList, int movieId) {
        LOGGER.info("Start populating countries_movie_mapper table");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        for (Country country : countryList) {
        //    jdbcTemplate.update(insertCountryMovieSQL,new Object[] { countryMap.get(country.getCountryName()), movieId});
            parameterSource.addValue("country_id", countryMap.get(country.getCountryName()).getCountryId());
            parameterSource.addValue("movie_id", movieId);
            namedJdbcTemplate.update(insertCountryMovieSQL,parameterSource);
            LOGGER.info("Next rows were inserted, country = {} , movie_id =  {} ", countryMap.get(country.getCountryName()).getCountryId(), movieId);
        }
    }

    private void insertGenreMovie(Map<String, Genre> mapGenre, List<Genre> movieGenreList, int movieId) {
        LOGGER.info("Start inserting into genre_movies table");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        for (Genre genre : movieGenreList) {
            //namedJdbcTemplate.update(insertGenreMovieSQL, mapGenre.get(genre).getGenreId(), movieId);
            parameterSource.addValue("genre_id",mapGenre.get(genre.getName()).getGenreId());
            parameterSource.addValue("movie_id", movieId);
            namedJdbcTemplate.update(insertGenreMovieSQL, parameterSource);
            LOGGER.info("Next rows were inserted, genreId = {}, movieId = {} ", mapGenre.get(genre.getName()).getGenreId(), movieId);
        }
    }

    @Override
    public List<Movie> getAllMovies() {
        LOGGER.info("Start getting all movies from DB");
        long startTime = System.currentTimeMillis();
        List<Movie> allMovieList = jdbcTemplate.query(getAllMoviesSQL, new AllMovieMapper());
        LOGGER.info("Finish getting all rows from Movie, it took {} ms ", System.currentTimeMillis() - startTime);
        return allMovieList;
    }

    @Override
    public Movie getById(int id) {
        LOGGER.info("Start getting all movies from DB");
        long startTime = System.currentTimeMillis();
        Movie movie = jdbcTemplate.queryForObject(getMovieById, new Object[]{id}, new SingleMovieMapper());
        LOGGER.info("Finish getting all rows from Movie, it took {} ms ", System.currentTimeMillis() - startTime);
        return movie;
    }

    public void setGetAllMoviesSQL(String getAllMoviesSQL) {
        this.getAllMoviesSQL = getAllMoviesSQL;
    }

    public void setInsertMovieSQL(String insertMovieSQL) {
        this.insertMovieSQL = insertMovieSQL;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setMovieFileParser(MovieFileParser movieFileParser) {
        this.movieFileParser = movieFileParser;
    }
}
