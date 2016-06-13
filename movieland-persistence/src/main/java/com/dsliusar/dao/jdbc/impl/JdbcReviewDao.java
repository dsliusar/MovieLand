package com.dsliusar.dao.jdbc.impl;

import com.dsliusar.dao.ReviewDao;
import com.dsliusar.entity.Review;
import com.dsliusar.dao.files.impl.ReviewFileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by DSliusar on 07.06.2016.
 */
@Repository
public class JdbcReviewDao implements ReviewDao {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ReviewFileParser reviewFileParser;

    @Autowired
    private String insertReviewSQL;

    @Autowired
    private String getReviewById;

    @Override
    public void insert() {
        LOGGER.info("Start populating Review Table");
        reviewFileParser.parseReviewIntoList();
        List<Review> reviewList = reviewFileParser.reviewList();
        for (Review arrReview : reviewList) {
                jdbcTemplate.update(insertReviewSQL, new Object[]{
                        arrReview.getReviewId(),
                        arrReview.getUserId(),
                        arrReview.getMovieId(),
                        arrReview.getReviewText()}) ;

          LOGGER.info("Next Rows inserted into Review table : " + arrReview);
        }
    }

    @Override
    public List<Review> getReviewsByMovieId(int reviewId) {
        LOGGER.info("Start getting Review by Id");
        List<Review> allReviewList = jdbcTemplate.query(getReviewById, new Object[]{reviewId}, new RowMapper<Review>(){
            public Review mapRow(ResultSet rs, int rowNum) throws SQLException
            {
                Review review = new Review();
                review.setMovieId(rs.getInt("movie_id"));
                review.setReviewId(rs.getInt("review_id"));
                review.setUserId(rs.getInt("user_id"));
                review.setReviewText(rs.getString("review_text"));
                return review;
            }
        })  ;
        return allReviewList;
    }

    public void setReviewFileParser(ReviewFileParser reviewFileParser) {
        this.reviewFileParser = reviewFileParser;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setInsertReviewSQL(String insertReviewSQL) {
        this.insertReviewSQL = insertReviewSQL;
    }
}
