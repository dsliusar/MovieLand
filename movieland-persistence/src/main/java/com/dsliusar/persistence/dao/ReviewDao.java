package com.dsliusar.persistence.dao;

import com.dsliusar.tools.http.entities.MovieRatingChangeRequest;
import com.dsliusar.tools.http.entities.ReviewAddRequest;
import com.dsliusar.persistence.entity.Review;

import java.util.List;
import java.util.Map;

public interface ReviewDao{

    void insert(List<Review> reviewList);
    void insert(ReviewAddRequest reviewAddRequest);
    void remove(int reviewId);
    List<Review> getReviewsByMovieId(int movieId);
    Review getReviewByReviewId(int reviewId);
    Map<Integer,List<Review>> getAllMoviesReviews();
    void addRating(MovieRatingChangeRequest movieRatingChangeRequest);
    void updateRating(int userRateId);
    List<Double> getAllUsersMovieRating(int movieID);
    int getUserMovieRatingId(int movieID, int userId);


}
