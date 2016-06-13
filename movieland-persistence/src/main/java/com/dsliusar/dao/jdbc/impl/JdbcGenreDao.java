package com.dsliusar.dao.jdbc.impl;

import com.dsliusar.dao.GenreDao;
import com.dsliusar.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public void insert(Map<String,Genre> genreMap) {
        LOGGER.info("Start inserting into Genre table");
        for (Map.Entry<String, Genre> arrGenre : genreMap.entrySet()) {
            jdbcTemplate.update(insertGenreSQL, arrGenre.getValue().getGenreId(),
                                                arrGenre.getValue().getName());
            LOGGER.info("Next rows were inserted " + arrGenre);
        }
    }

    @Override
    public Map<Integer, String> getAllGenres() {
        LOGGER.info("Getting All Genres from DB ");
        long startTime = System.currentTimeMillis();

        Map<Integer,String> genresMap = jdbcTemplate.query(getAllGenresSQL, new ResultSetExtractor<Map<Integer, String>>() {
            @Override
            public Map<Integer, String> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                HashMap<Integer, String> mapRet = new HashMap<>();
                while(resultSet.next()){
                    mapRet.put(resultSet.getInt("genre_id"), resultSet.getString("description"));
                }
                return mapRet;
            }
        });

        LOGGER.info("All Genres were extracted from DB, it took {} ms ", System.currentTimeMillis() - startTime );
        return genresMap;
    }

   public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setInsertGenreSQL(String insertGenreSQL) {
        this.insertGenreSQL = insertGenreSQL;
    }
}
