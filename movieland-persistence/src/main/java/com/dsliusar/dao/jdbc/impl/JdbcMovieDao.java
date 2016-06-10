package com.dsliusar.dao.jdbc.impl;

import com.dsliusar.dao.MovieDao;
import com.dsliusar.dao.jdbc.mapper.AllMovieMapper;
import com.dsliusar.dao.jdbc.mapper.SingleMovieMapper;
import com.dsliusar.entity.Movie;
import com.dsliusar.dao.files.MovieFileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Red1 on 6/7/2016.
 */
@Repository
public class JdbcMovieDao implements MovieDao {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieFileParser movieFileParser;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String insertMovieSQL;

    @Autowired
    private String getAllMoviesSQL;

    @Autowired
    private String getMovieById;

    @Override
    public void insert() {
        truncateCommon(jdbcTemplate,"movie");

        LOGGER.info("Start inserting into Movie table ");

        movieFileParser.ParseMoviesIntoList();
        Map<String,Movie> moviesMap = movieFileParser.getParsedMovieMap();

        for (Map.Entry<String, Movie> arrMovie : moviesMap.entrySet()) {
                jdbcTemplate.update(insertMovieSQL,new Object[]{
                                        arrMovie.getValue().getMovieId(),
                                        arrMovie.getValue().getMovieNameRus(),
                                        arrMovie.getValue().getMovieNameeEng(),
                                        arrMovie.getValue().getYear(),
                                        arrMovie.getValue().getDesciprtion(),
                                        arrMovie.getValue().getRating(),
                                        arrMovie.getValue().getPrice()
                                        });
              LOGGER.info("Next rows were inserted into Movies " + arrMovie);
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
