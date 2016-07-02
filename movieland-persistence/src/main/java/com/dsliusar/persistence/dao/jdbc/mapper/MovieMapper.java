package com.dsliusar.persistence.dao.jdbc.mapper;

import com.dsliusar.persistence.entity.Movie;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MovieMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setMovieNameOrigin(resultSet.getString("movie_name_eng"));
        movie.setMovieNameRus(resultSet.getString("movie_name_rus"));
        movie.setMovieId(resultSet.getInt("movie_id"));
        movie.setYear(resultSet.getInt("year"));
        movie.setRating(resultSet.getDouble("rating"));
        movie.setDescription(resultSet.getString("description"));
        movie.setPrice(resultSet.getDouble("price"));
        movie.setLastUpdateTs(resultSet.getTimestamp("last_upd_ts"));
        return movie;
    }
}