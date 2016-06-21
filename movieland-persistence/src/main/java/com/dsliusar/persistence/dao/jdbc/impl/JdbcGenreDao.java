package com.dsliusar.persistence.dao.jdbc.impl;

import com.dsliusar.persistence.dao.GenreDao;
import com.dsliusar.persistence.dao.jdbc.mapper.GenreMapRowMapper;
import com.dsliusar.persistence.dao.jdbc.mapper.GenresMapper;
import com.dsliusar.persistence.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by DSliusar on 06.06.2016.
 */
@Repository
public class JdbcGenreDao implements GenreDao {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String insertGenreSQL;

    @Autowired
    private String getAllGenresSQL;

    @Autowired
    private GenreMapRowMapper genreMapRowMapper;

    @Autowired
    private String getAllGenresByMovieIdSQL;

    @Autowired
    private String getAllGenresWithMovieIdSQL;

    @Override
    public void insert(Map<String, Genre> genreMap) {
        LOGGER.info("Start inserting into Genre table");
        for (Map.Entry<String, Genre> arrGenre : genreMap.entrySet()) {
            jdbcTemplate.update(insertGenreSQL, arrGenre.getValue().getGenreId(),
                    arrGenre.getValue().getName());
            if(LOGGER.isDebugEnabled()){
                LOGGER.debug("Next rows were inserted to genre table {} ", arrGenre);
            }

        }
        LOGGER.info("All rows were inserted to genres");
    }

    @Override
    public Map<String, Integer> getAllGenres() {
        LOGGER.info("Getting All Genres from DB ");
        long startTime = System.currentTimeMillis();

        Map<String, Integer> genresMap = jdbcTemplate.query(getAllGenresSQL, resultSet -> {
            ConcurrentHashMap<String, Integer> mapRet = new ConcurrentHashMap<>();
            while (resultSet.next()) {
                mapRet.put(resultSet.getString("description"), resultSet.getInt("genre_id"));
            }
            return mapRet;
        });

        LOGGER.info("All Genres were extracted from DB, it took {} ms ", System.currentTimeMillis() - startTime);
        return genresMap;
    }


    @Override
    public List<Genre> getGenresByMovieId(int movieId) {
        LOGGER.info("Get All Genres by Movie Id");
        long startTime = System.currentTimeMillis();
        List<Genre> genreList = jdbcTemplate.query(getAllGenresByMovieIdSQL, new Object[]{movieId}, new GenresMapper());
        LOGGER.info("All genres by movie ID was received, it took {}", System.currentTimeMillis() - startTime);
        return genreList;
    }

    @Override
    public Map<Integer, List<Genre>> getGenreWithMovieId() {
        LOGGER.info("Get All Genres Map with Movie Id");
        long startTime = System.currentTimeMillis();
        Map<Integer, List<Genre>> genresMap = jdbcTemplate.query(getAllGenresWithMovieIdSQL, genreMapRowMapper);
        LOGGER.info("Finished getting all genres with movie id, it took {}", System.currentTimeMillis() - startTime);
        return genresMap;
    }

}
