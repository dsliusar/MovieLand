package com.dsliusar.dao;

import com.dsliusar.entity.Review;

import java.util.List;

public interface ReviewDao{
    void insert(List<Review> reviewList);

    List<Review> getReviewsByMovieId(int reviewId);

}
