package com.dsliusar.persistence.dao;

import com.dsliusar.persistence.entity.Review;

import java.util.List;
import java.util.Map;

public interface ReviewDao{
    void insert(List<Review> reviewList);

    List<Review> getReviewsByMovieId(int movieId);
    Map<Integer,List<Review>> getAllMoviesReviews();

}
