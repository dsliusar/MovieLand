package com.dsliusar.service;

import com.dsliusar.entity.Review;

import java.util.List;
import java.util.Map;

public interface ReviewService {

    List<Review> getAllReviewByMovieId(int movieId);
    Map<Integer,List<Review>> getAllMoviesReviews();
}
