package com.dsliusar.service.impl;

import com.dsliusar.dao.ReviewDao;
import com.dsliusar.entity.Review;
import com.dsliusar.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("simpleReviewService")
public class SimpleReviewService implements ReviewService {

    @Autowired
    ReviewDao jdbcReviewDao;

    @Override
    public List<Review> getAllReviewByMovieId(int movieId) {
        return jdbcReviewDao.getReviewsByMovieId(movieId);
    }
}
