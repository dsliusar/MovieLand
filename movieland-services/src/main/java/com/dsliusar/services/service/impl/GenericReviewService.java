package com.dsliusar.services.service.impl;

import com.dsliusar.exceptions.MovieLandSecurityException;
import com.dsliusar.exceptions.NotFoundException;
import com.dsliusar.http.entities.ReviewAddRequest;
import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.persistence.dao.ReviewDao;
import com.dsliusar.persistence.entity.Review;
import com.dsliusar.services.security.ReviewSecurity;
import com.dsliusar.services.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GenericReviewService implements ReviewService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReviewDao jdbcReviewDao;

    @Autowired
    private ReviewSecurity reviewSecurity;

    @Override
    public List<Review> getAllReviewByMovieId(int movieId) {
        return jdbcReviewDao.getReviewsByMovieId(movieId);
    }

    @Override
    public Map<Integer, List<Review>> getAllMoviesReviews() {
        return jdbcReviewDao.getAllMoviesReviews();
    }

    @Override
    public void addReview(ReviewAddRequest reviewAddRequest) {
        jdbcReviewDao.insert(reviewAddRequest);
    }

    @Override
    public void removeReview(UserSecureTokenEntity userSecureTokenEntity, int reviewId) throws MovieLandSecurityException, NotFoundException {
        Review review = jdbcReviewDao.getReviewByReviewId(reviewId);
        if (review == null){
            LOGGER.error("Review by id {} was not found in database", reviewId);
            throw new NotFoundException("Deleting review " + reviewId + " was not found");
        }
        try {
            reviewSecurity.checkUserDeletePermission(userSecureTokenEntity, review);
            jdbcReviewDao.remove(reviewId);
        } catch (MovieLandSecurityException e){
            throw e;
        }
    }
}
