package com.dsliusar.services.service;

import com.dsliusar.persistence.entity.Review;

import java.util.List;
import java.util.Map;

public interface ReviewService {

    List<Review> getAllReviewByMovieId(int movieId);
    Map<Integer,List<Review>> getAllMoviesReviews();
}
