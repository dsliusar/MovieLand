package com.dsliusar.services.service.impl;

import com.dsliusar.exceptions.MovieLandSecurityException;
import com.dsliusar.http.entities.ReviewAddRequestEntity;
import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.persistence.dao.ReviewDao;
import com.dsliusar.persistence.entity.Review;
import com.dsliusar.services.security.ReviewSecurity;
import com.dsliusar.services.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GenericReviewService implements ReviewService {

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
    public void addReview(ReviewAddRequestEntity reviewAddRequest) {
        jdbcReviewDao.insert(reviewAddRequest);
    }

    @Override
    public void removeReview(UserSecureTokenEntity userSecureTokenEntity, int reviewId) throws MovieLandSecurityException {
        Review review = jdbcReviewDao.getReviewByReviewId(reviewId);
        try {
            reviewSecurity.checkDeletePermission(userSecureTokenEntity,review);
            jdbcReviewDao.remove(reviewId);
        } catch (MovieLandSecurityException e){
            throw e;
        }
    }
}
