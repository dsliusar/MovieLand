package com.dsliusar.services.service.impl;

import com.dsliusar.exceptions.IllegalDeleteException;
import com.dsliusar.http.entities.ReviewAddRequestEntity;
import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.persistence.dao.ReviewDao;
import com.dsliusar.persistence.entity.Review;
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

    @Override
    public List<Review> getAllReviewByMovieId(int movieId) {
        return jdbcReviewDao.getReviewsByMovieId(movieId);
    }

    @Override
    public Map<Integer, List<Review>> getAllMoviesReviews() {
        return jdbcReviewDao.getAllMoviesReviews();
    }

    @Override
    public void addReview(ReviewAddRequestEntity reviewAddRequest) {
        jdbcReviewDao.insert(reviewAddRequest);
    }

    @Override
    public void removeReview(UserSecureTokenEntity userSecureTokenEntity, int reviewId) throws IllegalDeleteException {
        Review review = jdbcReviewDao.getReviewByReviewId(reviewId);
        if (userSecureTokenEntity.getUserId() == review.getUserId()) {
            jdbcReviewDao.remove(reviewId);
        } else {
            LOGGER.error("Deleting of the review {} is prohibited for this user {}", reviewId, userSecureTokenEntity.getUserName());
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("Deleting this review {} is only eligible to next user {}", reviewId, review);
            }
            throw new IllegalDeleteException("Deleting review id is not owning by this user");
        }
    }
}
