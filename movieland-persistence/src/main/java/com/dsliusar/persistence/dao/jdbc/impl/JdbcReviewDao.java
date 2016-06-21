package com.dsliusar.persistence.dao.jdbc.impl;

import com.dsliusar.http.entities.ReviewAddRequestEntity;
import com.dsliusar.persistence.dao.ReviewDao;
import com.dsliusar.persistence.dao.jdbc.mapper.ReviewMapRowMapper;
import com.dsliusar.persistence.dao.jdbc.mapper.ReviewMapper;
import com.dsliusar.persistence.entity.Review;
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

    @Autowired
    private String deleteReviewSQL;

    @Autowired
    private String getReviewByReviewId;

    @Override
    public void insert(List<Review> reviewList) {
        LOGGER.info("Start populating Review Table");
        for (Review arrReview : reviewList) {
                jdbcTemplate.update(insertReviewSQL,
                      //  arrReview.getReviewId(),
                        arrReview.getUserId(),
                        arrReview.getMovieId(),
                        arrReview.getReviewText()) ;
            LOGGER.info("All rows in to Review were inserted");

        }

    }

    @Override
    public void insert(ReviewAddRequestEntity reviewAddRequest) {
        LOGGER.info("Start populating Review Table");
        jdbcTemplate.update(insertReviewSQL,reviewAddRequest.getUserId(),
                                            reviewAddRequest.getUserId(),
                                            reviewAddRequest.getReview());
        LOGGER.info("All rows were successfully inserted");
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Next Rows inserted into Review table : " + reviewAddRequest);
        }
    }

    @Override
    public void remove(int reviewId) {
        LOGGER.info("Start deleting review by id, {} ", reviewId);
        jdbcTemplate.update(deleteReviewSQL, new Object[]{reviewId});
        LOGGER.info("Review was deleted successfully {} ", reviewId);
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
    public Review getReviewByReviewId(int reviewId) {
        LOGGER.info("Start getting Review by Id");
        long startTime = System.currentTimeMillis();
        Review review = jdbcTemplate.queryForObject(getReviewByReviewId, new Object[]{reviewId}, reviewMapper);
        LOGGER.info("Reviews by Movie Id was received, it took {}", System.currentTimeMillis() - startTime);
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("Next review was received {} ", review);
        }
        return review;
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
