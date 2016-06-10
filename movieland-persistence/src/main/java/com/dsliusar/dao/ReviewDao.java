package com.dsliusar.dao;

import com.dsliusar.entity.Review;

import java.util.List;

public interface ReviewDao extends CommonDao {
    public void insert();

    public List<Review> getReviewsByMovieId(int reviewId);

}
