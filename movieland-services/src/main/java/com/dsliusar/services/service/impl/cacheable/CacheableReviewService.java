package com.dsliusar.services.service.impl.cacheable;

import com.dsliusar.services.cache.CacheService;
import com.dsliusar.constants.Constant;
import com.dsliusar.persistence.entity.Review;
import com.dsliusar.services.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CacheableReviewService implements ReviewService {

    @Autowired
    private CacheService concurrentHashMapService;

    @Override
    public List<Review> getAllReviewByMovieId(int movieId) {
        return null;
    }

    @Override
    public Map<Integer, List<Review>> getAllMoviesReviews() {
        return (Map<Integer, List<Review>>) concurrentHashMapService.getCacheById(Constant.ALL_MOVIES_REVIEWS_CACHE);
    }
}
