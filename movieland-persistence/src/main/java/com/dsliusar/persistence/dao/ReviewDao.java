package com.dsliusar.persistence.dao;

import com.dsliusar.http.entities.ReviewAddRequestEntity;
import com.dsliusar.persistence.entity.Review;

import java.util.List;
import java.util.Map;

public interface ReviewDao{

    void insert(List<Review> reviewList);
    void insert(ReviewAddRequestEntity reviewAddRequest);
    void remove(int reviewId);
    List<Review> getReviewsByMovieId(int movieId);
    Review getReviewByReviewId(int reviewId);
    Map<Integer,List<Review>> getAllMoviesReviews();

}
