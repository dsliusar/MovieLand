package com.dsliusar.dao.jdbc.mapper;

import com.dsliusar.entity.Genre;
import com.dsliusar.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class GenresMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        Genre genre = new Genre();
        genre.setGenreId(resultSet.getInt("genre_id"));
        genre.setName(resultSet.getString("description"));
        return genre;
    }
}
