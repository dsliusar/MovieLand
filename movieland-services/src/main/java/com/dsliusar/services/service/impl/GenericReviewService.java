package com.dsliusar.services.service.impl;

import com.dsliusar.persistence.dao.ReviewDao;
import com.dsliusar.persistence.entity.Review;
import com.dsliusar.services.service.MovieService;
import com.dsliusar.services.service.ReviewService;
import com.dsliusar.tools.exceptions.NotFoundException;
import com.dsliusar.tools.exceptions.RequestFormatException;
import com.dsliusar.tools.http.entities.MovieRatingChangeRequest;
import com.dsliusar.tools.http.entities.MovieRatingOnChangeResponse;
import com.dsliusar.tools.http.entities.ReviewAddRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GenericReviewService implements ReviewService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReviewDao jdbcReviewDao;

    @Autowired
    private MovieService genericMovieService;

    @Override
    public List<Review> getAllReviewByMovieId(int movieId) {
        return jdbcReviewDao.getReviewsByMovieId(movieId);
    }

    @Override
    public Map<Integer, List<Review>> getAllMoviesReviews() {
        return jdbcReviewDao.getAllMoviesReviews();
    }

    @Override
    public void addReview(ReviewAddRequest reviewAddRequest) {
        jdbcReviewDao.insert(reviewAddRequest);
    }

    @Override
    public Review getReviewById(int reviewId) {
        return jdbcReviewDao.getReviewByReviewId(reviewId);
    }

    @Override
    public void removeReview(int reviewId) {
        jdbcReviewDao.remove(reviewId);
    }


    /**
     * Method is performing business check before insert or update the users Rating
     * Check if user has already rate the requested movie
     *
     * @param movieRatingChangeRequest
     * @return
     * @throws RequestFormatException
     */
    @Override
    public MovieRatingOnChangeResponse calculateAndUpdateRating(MovieRatingChangeRequest movieRatingChangeRequest) throws RequestFormatException, NotFoundException {
        try {
            checkRatingValue(movieRatingChangeRequest.getRating());
        } catch (RequestFormatException e) {
            LOGGER.error("Rating value is not valid", e);
            throw e;
        }

        LOGGER.info("Checking if user already rated the movie");
        // check if user already rate the movie
        int userRateId = jdbcReviewDao.getUserMovieRatingId(movieRatingChangeRequest.getMovieId(),
                movieRatingChangeRequest.getUserId());

        // If user did not rate the movie add requested Rating
        if (userRateId == 0) {
            LOGGER.info("Movie rating for user with ID {} was not found",movieRatingChangeRequest.getUserId());
            throw new NotFoundException("Rating for user was not found ");
        } else { // If user already rated the movie then update the movie rating and add Rating as latest
            updateRating(userRateId);
            addRating(movieRatingChangeRequest);
        }
        // calculate and update average rating of the movie base on all ratings
        double avgRating = updateAvgMovieRating(movieRatingChangeRequest.getMovieId());

        //return response object
        return fillMovieRatingChangeResponse(movieRatingChangeRequest.getMovieId(),
                movieRatingChangeRequest.getRating(),
                avgRating);

    }

    /**
     * Filling the object for Response
     * @param movieId
     * @param userRating
     * @param avgRating
     * @return
     */
    private MovieRatingOnChangeResponse fillMovieRatingChangeResponse(int movieId, double userRating, double avgRating) {
        MovieRatingOnChangeResponse movieRatingResponse = new MovieRatingOnChangeResponse();
        movieRatingResponse.setMovieId(movieId);
        movieRatingResponse.setAverageRating(avgRating);
        movieRatingResponse.setUserRating(userRating);
        return movieRatingResponse;
    }

    /**
     * Add rating to the database
     *
     * @param movieRatingChangeRequest
     * @throws RequestFormatException
     */
    private void addRating(MovieRatingChangeRequest movieRatingChangeRequest) throws RequestFormatException {
        jdbcReviewDao.addRating(movieRatingChangeRequest);
    }

    /**
     * Update rating in the database
     *
     * @param userRateId
     * @throws RequestFormatException
     */
    private void updateRating(int userRateId) throws RequestFormatException {
        jdbcReviewDao.updateRating(userRateId);
    }

    /**
     * Checks if RatingValue is equal, should be between 0 or 10
     *
     * @param RatingValue
     * @throws RequestFormatException
     */
    private void checkRatingValue(double RatingValue) throws RequestFormatException {
        if (!(RatingValue >= 1 && RatingValue <= 10))
            throw new RequestFormatException("Rating value should be between 0 and 10");
    }

    /**
     * Method getting all users Rating for specific movie
     *
     * @param movieID
     * @return List of ratings
     */
    private List<Double> getAllUsersMovieRating(int movieID) {
        return jdbcReviewDao.getAllUsersMovieRating(movieID);
    }

    /**
     * Method calling genericMovieService  to update avg rating of the movie based
     * on all users ratings
     *
     * @param movieId
     * @return calculated avg rating of the movie
     */
    private double updateAvgMovieRating(int movieId) {
        return genericMovieService.updateAverageRating(movieId, getAllUsersMovieRating(movieId));
    }

}
