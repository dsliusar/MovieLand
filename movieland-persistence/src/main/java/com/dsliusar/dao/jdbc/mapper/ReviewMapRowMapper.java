package com.dsliusar.dao.jdbc.mapper;

import com.dsliusar.entity.Review;
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
public class ReviewMapRowMapper implements ResultSetExtractor<Map<Integer,List<Review>>> {
    @Override
    public Map<Integer, List<Review>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        ConcurrentHashMap<Integer, List<Review>> mapRet = new ConcurrentHashMap<>();
        while (rs.next()) {
            Review review = new Review();
            List<Review> reviewList = new ArrayList<>();
            review.setReviewText(rs.getString("review_text"));
            review.setUserId(rs.getInt("user_id"));
            review.setReviewId(rs.getInt("review_id"));
            reviewList.add(review);
            mapRet.put(rs.getInt("movie_id"),reviewList);
        }
        return mapRet;
    }
}
