package com.dsliusar.dao;

import com.dsliusar.entity.Review;

import java.util.List;
import java.util.Map;

public interface ReviewDao{
    void insert(List<Review> reviewList);

    List<Review> getReviewsByMovieId(int movieId);
    Map<Integer,List<Review>> getAllMoviesReviews();

}
