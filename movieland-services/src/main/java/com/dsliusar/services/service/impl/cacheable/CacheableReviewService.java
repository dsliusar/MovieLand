package com.dsliusar.services.service.impl.cacheable;

import com.dsliusar.exceptions.IllegalDeleteException;
import com.dsliusar.http.entities.ReviewAddRequestEntity;
import com.dsliusar.http.entities.UserSecureTokenEntity;
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
        return getAllMoviesReviews().get(movieId);
    }

    @Override
    public Map<Integer, List<Review>> getAllMoviesReviews() {
        return (Map<Integer, List<Review>>) concurrentHashMapService.getCacheById(Constant.ALL_MOVIES_REVIEWS_CACHE);
    }

    @Override
    public void addReview(ReviewAddRequestEntity reviewAddRequest) {

    }

    @Override
    public void removeReview(UserSecureTokenEntity userSecureTokenEntity, int reviewId) throws IllegalDeleteException {

    }

}
