package com.dsliusar.dao.jdbc.impl;

import com.dsliusar.dao.ReviewDao;
import com.dsliusar.dao.jdbc.mapper.ReviewMapper;
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
    private String insertReviewSQL;

    @Autowired
    private String getReviewById;

    @Override
    public void insert(List<Review> reviewList) {
        LOGGER.info("Start populating Review Table");
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
    public List<Review> getReviewsByMovieId(int movieId) {
        LOGGER.info("Start getting Review by Id");
        long startTime = System.currentTimeMillis();
        List<Review> allReviewList = jdbcTemplate.query(getReviewById, new Object[]{movieId},new ReviewMapper())  ;
        LOGGER.info("Reviews by Movie Id was received, it took {}", System.currentTimeMillis() - startTime);
        return allReviewList;
    }
}
