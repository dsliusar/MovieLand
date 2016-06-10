package com.dsliusar.dao.jdbc.mapper;

import com.dsliusar.entity.Genre;
import com.dsliusar.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DSliusar on 09.06.2016.
 */
public class AllMovieMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();

        movie.setMovieNameeEng(resultSet.getString("movie_name_eng"));
        movie.setMovieNameRus(resultSet.getString("movie_name_rus"));
        movie.setMovieId(resultSet.getInt("movie_id"));
        movie.setYear(resultSet.getInt("year"));
        movie.setRating(resultSet.getDouble("rating"));
        //Add genres list
        Genre genre = new Genre();
        genre.setDescprtion(resultSet.getString("genre_description"));
        List<Genre> genresList = new ArrayList<>();
        genresList.add(genre);
        movie.setGenreList(genresList);
        return movie;
    }
}