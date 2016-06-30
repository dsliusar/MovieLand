package com.dsliusar.services.service.impl.cacheable;

import com.dsliusar.persistence.entity.Review;
import com.dsliusar.services.cache.CacheService;
import com.dsliusar.services.service.ReviewService;
import com.dsliusar.tools.constants.Constant;
import com.dsliusar.tools.exceptions.NotFoundException;
import com.dsliusar.tools.exceptions.RequestFormatException;
import com.dsliusar.tools.http.entities.MovieRatingChangeRequest;
import com.dsliusar.tools.http.entities.MovieRatingOnChangeResponse;
import com.dsliusar.tools.http.entities.ReviewAddRequest;
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
    public void addReview(ReviewAddRequest reviewAddRequest) {

    }

    @Override
    public Review getReviewById(int reviewId) {
        return null;
    }

    @Override
    public void removeReview(int reviewId) {

    }

    @Override
    public MovieRatingOnChangeResponse addRating(MovieRatingChangeRequest movieRatingChangeRequest) throws RequestFormatException {
        return null;
    }

    @Override
    public MovieRatingOnChangeResponse updateRating(MovieRatingChangeRequest movieRatingChangeRequest) throws RequestFormatException, NotFoundException {
        return null;
    }
}
