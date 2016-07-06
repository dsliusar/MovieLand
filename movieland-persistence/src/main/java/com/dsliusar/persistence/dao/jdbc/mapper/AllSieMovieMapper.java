package com.dsliusar.persistence.dao.jdbc.mapper;

import com.dsliusar.tools.entities.report.AllSiteMovies;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AllSieMovieMapper implements RowMapper<AllSiteMovies> {
    @Override
    public AllSiteMovies mapRow(ResultSet rs, int rowNum) throws SQLException {
        AllSiteMovies allSiteMovies = new AllSiteMovies();
        allSiteMovies.setMovieId(rs.getInt("movie_id"));
        allSiteMovies.setMovieNameRus(rs.getString("movie_name_rus"));
        allSiteMovies.setMovieNameOrigin(rs.getString("movie_name_eng"));
        allSiteMovies.setDescription(rs.getString("description"));
        allSiteMovies.setGenres(rs.getString("genre_list"));
        allSiteMovies.setPrice(rs.getDouble("price"));
        allSiteMovies.setAddDate(rs.getTimestamp("add_time"));
        allSiteMovies.setModifiedDate(rs.getTimestamp("update_time"));
        allSiteMovies.setRating(rs.getDouble("rating"));
        allSiteMovies.setReviewCount(rs.getInt("review_count"));
        return allSiteMovies;
    }
}
