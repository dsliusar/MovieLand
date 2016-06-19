package com.dsliusar.service.impl.cacheable;

import com.dsliusar.cache.CacheService;
import com.dsliusar.constants.Constant;
import com.dsliusar.dao.ReviewDao;
import com.dsliusar.entity.Review;
import com.dsliusar.service.ReviewService;
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
