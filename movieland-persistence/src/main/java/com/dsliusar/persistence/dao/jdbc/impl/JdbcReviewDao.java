package com.dsliusar.persistence.dao.jdbc.impl;

import com.dsliusar.persistence.dao.ReviewDao;
import com.dsliusar.persistence.dao.jdbc.mapper.DoubleRowMapper;
import com.dsliusar.persistence.dao.jdbc.mapper.ReviewMapRowMapper;
import com.dsliusar.persistence.dao.jdbc.mapper.ReviewMapper;
import com.dsliusar.persistence.entity.Review;
import com.dsliusar.tools.http.entities.MovieRatingChangeRequest;
import com.dsliusar.tools.http.entities.ReviewAddRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Autowired
    private String insertUserMovieRatingSQL;

    @Autowired
    private String updateUserRatingSQL;

    @Autowired
    private String getAllMovieRatings;

    @Autowired
    private DoubleRowMapper doubleRowMapper;

    @Autowired
    private String getUserMovieRatingId;

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
    public void insert(ReviewAddRequest reviewAddRequest) {
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
        Review review = null;
        try {
            review = jdbcTemplate.queryForObject(getReviewByReviewId, new Object[]{reviewId}, reviewMapper);
        }catch (EmptyResultDataAccessException e){
            LOGGER.warn("Error happen in getReviewByReviewId method ", e);
        }
        LOGGER.info("Reviews by Movie Id was received, it took {}", System.currentTimeMillis() - startTime);
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

    @Override
    public void addRating(MovieRatingChangeRequest movieRatingChangeRequest) {
        LOGGER.info("Start inserting rating for movieId {}", movieRatingChangeRequest.getMovieId());
        long startTime = System.currentTimeMillis();
        jdbcTemplate.update(insertUserMovieRatingSQL, movieRatingChangeRequest.getMovieId(),
                                                       movieRatingChangeRequest.getUserId(),
                                                       movieRatingChangeRequest.getRating());
        LOGGER.info("Rating for the movie was inserted it took {}", System.currentTimeMillis() - startTime);
    }

    @Override
    public void updateRating(int userRateId) {
        LOGGER.info("Start Updating Rating with id {}", userRateId);
        long startTime = System.currentTimeMillis();
        jdbcTemplate.update(updateUserRatingSQL,userRateId);
        LOGGER.info("Rating for the movie were updated, it took {}", System.currentTimeMillis() - startTime);

    }

    @Override
    public List<Double> getAllUsersMovieRating(int movieID) {
        LOGGER.info("Start getting all Ratings for movie {}", movieID);
        long startTime = System.currentTimeMillis();
        List<Double> ratingList = jdbcTemplate.query(getAllMovieRatings, new Object[]{movieID}, doubleRowMapper);
        LOGGER.info("All users Rating was retrieved, it took {}", System.currentTimeMillis() - startTime);
        return ratingList;
    }

    @Override
    public int getUserMovieRatingId(int movieID, int userId) {
        LOGGER.info("get user movie rating id for movie {} and user {}",movieID, userId);
        long startTime = System.currentTimeMillis();
        Integer userMovieRatingId = 0;
        try {
            userMovieRatingId = jdbcTemplate.queryForObject(getUserMovieRatingId, new Object[]{movieID, userId}, Integer.class);
        } catch (EmptyResultDataAccessException e){
            LOGGER.warn("Exception : ", e);
        }
        LOGGER.info("user movie id was retrieved, it took {}", System.currentTimeMillis() - startTime);
        return userMovieRatingId;
    }
}
