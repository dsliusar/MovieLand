package com.dsliusar.dao.jdbc.impl;

import com.dsliusar.dao.GenreDao;
import com.dsliusar.dao.jdbc.mapper.GenresMapper;
import com.dsliusar.entity.Genre;
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
    private String getAllGenresByMovieIdSQL;

    ConcurrentHashMap<String,Integer> genresMap;

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
    public Map<String,Integer> getAllGenres() {
        LOGGER.info("Getting All Genres from DB ");
        long startTime = System.currentTimeMillis();

       genresMap = jdbcTemplate.query(getAllGenresSQL, resultSet -> {
            ConcurrentHashMap<String,Integer> mapRet = new ConcurrentHashMap<>();
            while(resultSet.next()){
                mapRet.put(resultSet.getString("description"), resultSet.getInt("genre_id") );
            }
            return mapRet;
        });

        LOGGER.info("All Genres were extracted from DB, it took {} ms ", System.currentTimeMillis() - startTime );
        return genresMap;
    }



    @Override
    public List<Genre> getGenresByMovieId(int movieId) {
        LOGGER.info("Get All Genres by Movie Id");
        long startTime = System.currentTimeMillis();
        List<Genre> genreList = jdbcTemplate.query(getAllGenresByMovieIdSQL, new Object[]{movieId}, new GenresMapper());
        LOGGER.info("All genres by movie ID was received, it took {}", System.currentTimeMillis() - startTime );
        return genreList;
    }

    @Override
    public ConcurrentHashMap<String, Integer> getGenreMovieCache() {
        return genresMap;
    }


}
