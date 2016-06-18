package com.dsliusar.dao.jdbc.mapper;

import com.dsliusar.entity.Review;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class ReviewMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        Review review = new Review();
        review.setReviewId(resultSet.getInt("review_id"));
        review.setMovieId(resultSet.getInt("movie_id"));
        review.setUserId(resultSet.getInt("user_id"));
        review.setReviewText(resultSet.getString("review_text"));
        return review;
    }
}
