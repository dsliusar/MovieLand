package com.dsliusar.services.service;

import com.dsliusar.exceptions.MovieLandSecurityException;
import com.dsliusar.exceptions.NotFoundException;
import com.dsliusar.http.entities.ReviewAddRequest;
import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.persistence.entity.Review;

import java.util.List;
import java.util.Map;

public interface ReviewService {

    List<Review> getAllReviewByMovieId(int movieId);
    Map<Integer,List<Review>> getAllMoviesReviews();
    void addReview(ReviewAddRequest reviewAddRequest);
    void removeReview(UserSecureTokenEntity userSecureTokenEntity,int reviewId) throws SecurityException, MovieLandSecurityException, NotFoundException;

}
