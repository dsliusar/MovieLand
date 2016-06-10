package com.dsliusar.dao.jdbc.impl;

import com.dsliusar.dao.GenreMovieDao;
import com.dsliusar.entity.GenreMovie;
import com.dsliusar.dao.files.MovieFileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by DSliusar on 07.06.2016.
 */
@Repository
public class JdbcGenreMovieDao implements GenreMovieDao {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieFileParser movieFileParser;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String insertGenreMovieSQL;

    @Override
    public void insert() {
        truncateCommon(jdbcTemplate, "genre_movies");

        LOGGER.info("Start inserting into genre_movies table");

        List<GenreMovie> listGenreMovies = movieFileParser.getGenreMoviesList();
        for (GenreMovie genreMovie : listGenreMovies) {

                jdbcTemplate.update(insertGenreMovieSQL, new Object[]{
                        genreMovie.getGenreId(),
                        genreMovie.getMovieId()
                });
            LOGGER.info("Next rows were inserted " + genreMovie);
        }
    }

    public void setMovieFileParser(MovieFileParser movieFileParser) {
        this.movieFileParser = movieFileParser;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setInsertGenreMovieSQL(String insertGenreMovieSQL) {
        this.insertGenreMovieSQL = insertGenreMovieSQL;
    }
}
