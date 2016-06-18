package com.dsliusar.dao.jdbc.impl;

import com.dsliusar.dao.ReviewDao;
import com.dsliusar.dao.jdbc.mapper.ReviewMapRowMapper;
import com.dsliusar.dao.jdbc.mapper.ReviewMapper;
import com.dsliusar.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private String getAllMoviesReviews;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private ReviewMapRowMapper reviewMapRowMapper;

    @Override
    public void insert(List<Review> reviewList) {
        LOGGER.info("Start populating Review Table");
        for (Review arrReview : reviewList) {
                jdbcTemplate.update(insertReviewSQL, arrReview.getReviewId(),
                        arrReview.getUserId(),
                        arrReview.getMovieId(),
                        arrReview.getReviewText()) ;
           if(LOGGER.isDebugEnabled()) {
               LOGGER.debug("Next Rows inserted into Review table : " + arrReview);
           }
        }
        LOGGER.info("All rows in to Review were inserted");
    }

    @Override
    public List<Review> getReviewsByMovieId(int movieId) {
        LOGGER.info("Start getting Review by Id");
        long startTime = System.currentTimeMillis();
        List<Review> allReviewList = jdbcTemplate.query(getReviewById, new Object[]{movieId},reviewMapper)  ;
        LOGGER.info("Reviews by Movie Id was received, it took {}", System.currentTimeMillis() - startTime);
        return allReviewList;
    }

    @Override
    public Map<Integer, List<Review>> getAllMoviesReviews() {
        LOGGER.info("Getting All Movies Review from DB ");
        long startTime = System.currentTimeMillis();
        Map<Integer, List<Review>> reviewsMap = jdbcTemplate.query(getAllMoviesReviews,reviewMapRowMapper);
        LOGGER.info("All Movies Review were extracted from DB, it took {} ms ", System.currentTimeMillis() - startTime);
        return reviewsMap;
    }
}
