package com.dsliusar.dao.jdbc.mapper;

import com.dsliusar.entity.Country;
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
public class SingleMovieMapper implements RowMapper<Movie> {

@Override
public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setMovieNameOrigin(resultSet.getString("movie_name_eng"));
        movie.setMovieNameRus(resultSet.getString("movie_name_rus"));
        movie.setMovieId(resultSet.getInt("movie_id"));
        movie.setYear(resultSet.getInt("year"));
        movie.setRating(resultSet.getDouble("rating"));
        //Add genres list
        Genre genre = new Genre();
        genre.setName(resultSet.getString("genre_description"));
        List<Genre> genresList = new ArrayList<>();
        genresList.add(genre);
        movie.setGenreList(genresList);
        Country country = new Country();
        country.setCountryName(resultSet.getString("country_names"));
        List<Country> countryList = new ArrayList<>();
        countryList.add(country);
        movie.setCountryList(countryList);
        return movie;
        }
}
