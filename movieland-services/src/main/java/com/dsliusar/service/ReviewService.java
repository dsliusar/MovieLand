package com.dsliusar.service;

import com.dsliusar.entity.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviewByMovieId(int movieId);
}
