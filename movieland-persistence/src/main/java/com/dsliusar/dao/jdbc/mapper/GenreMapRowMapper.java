package com.dsliusar.dao.jdbc.mapper;

import com.dsliusar.entity.Genre;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GenreMapRowMapper implements ResultSetExtractor<Map<Integer, List<Genre>>> {
    @Override
    public Map<Integer, List<Genre>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, List<Genre>> mapRet = new ConcurrentHashMap<>();
        List<Genre> genresList = null;
        while (rs.next()) {
            Genre genre = new Genre();
            Integer movieId = rs.getInt("movie_id");
            genre.setGenreId(rs.getInt("genre_id"));
            genre.setName(rs.getString("description"));
            if (mapRet.containsKey(movieId)) {
                mapRet.get(movieId).add(genre);
            } else {
                genresList = new ArrayList<>();
                genresList.add(genre);
                mapRet.put(movieId, genresList);
            }
        }
        return mapRet;

    }
}
