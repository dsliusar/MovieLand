package com.dsliusar.services.service;

import com.dsliusar.persistence.entity.Review;
import com.dsliusar.tools.http.entities.MovieRatingChangeRequest;
import com.dsliusar.tools.http.entities.MovieRatingOnChangeResponse;
import com.dsliusar.tools.http.entities.ReviewAddRequest;

import java.util.List;
import java.util.Map;

public interface ReviewService {

    List<Review> getAllReviewByMovieId(int movieId);

    Map<Integer, List<Review>> getAllMoviesReviews();

    void addReview(ReviewAddRequest reviewAddRequest);

    Review getReviewById(int reviewId);

    void removeReview(int reviewId);

    MovieRatingOnChangeResponse addRating(MovieRatingChangeRequest movieRatingChangeRequest);

    MovieRatingOnChangeResponse updateRating(MovieRatingChangeRequest movieRatingChangeRequest);


}
