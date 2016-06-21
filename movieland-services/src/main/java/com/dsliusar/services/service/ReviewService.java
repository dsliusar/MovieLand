package com.dsliusar.services.service;

import com.dsliusar.exceptions.IllegalDeleteException;
import com.dsliusar.http.entities.ReviewAddRequestEntity;
import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.persistence.entity.Review;

import java.util.List;
import java.util.Map;

public interface ReviewService {

    List<Review> getAllReviewByMovieId(int movieId);
    Map<Integer,List<Review>> getAllMoviesReviews();
    void addReview(ReviewAddRequestEntity reviewAddRequest);
    void removeReview(UserSecureTokenEntity userSecureTokenEntity,int reviewId) throws IllegalDeleteException;

}
