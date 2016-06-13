package com.dsliusar.dao.jdbc.mapper;

import com.dsliusar.entity.Movie;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DSliusar on 09.06.2016.
 */
@Deprecated
public class MovieMapExtractor implements ResultSetExtractor<Map<Integer, Movie>>{

//    private Map<Integer,Movie> allMovieMap;
//
//    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
//        Movie movie = new Movie();
//        movie.setMovieNameOrigin(resultSet.getString("movie_name_eng"));
//        movie.setMovieNameRus(resultSet.getString("movie_name_rus"));
//        movie.setMovieId(resultSet.getInt("movie_id"));
//        movie.setYear(resultSet.getInt("year"));
//        movie.setRating(resultSet.getDouble("rating"));
//        return movie;
//    }


    @Override
    public Map<Integer, Movie> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        HashMap<Integer,Movie> mapResult = new HashMap<>();
        while (resultSet.next()){
            Movie movie = new Movie();
            movie.setMovieNameOrigin(resultSet.getString("movie_name_eng"));
            movie.setMovieNameRus(resultSet.getString("movie_name_rus"));
            movie.setMovieId(resultSet.getInt("movie_id"));
            movie.setYear(resultSet.getInt("year"));
            movie.setRating(resultSet.getDouble("rating"));
            mapResult.put(resultSet.getInt("movie_id"),movie);
        }
        return mapResult;
    }
}
