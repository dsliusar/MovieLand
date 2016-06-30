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
     * Add rating to the database
     *
     * @param movieRatingChangeRequest
     * @throws RequestFormatException
     */
    public MovieRatingOnChangeResponse addRating(MovieRatingChangeRequest movieRatingChangeRequest) throws RuntimeException {
        LOGGER.info("Checking if rating value is valid");
        checkRatingValue(movieRatingChangeRequest.getRating());

        LOGGER.info("Checking if user already rated the movie");
        Integer userRateId = getCurrentUserRatingForMovie(movieRatingChangeRequest.getMovieId(),
                movieRatingChangeRequest.getUserId());

        // If user did not rate the movie add requested Rating
        if (userRateId == null) {
            jdbcReviewDao.addRating(movieRatingChangeRequest);
        } else { // If user already rated the movie then throw exception
            LOGGER.info("Movie Rating for this movie has been already added", movieRatingChangeRequest.getUserId());
            throw new NotFoundException("Movie Rating exist, user cannot add more than one rating for movie Id = " + movieRatingChangeRequest.getMovieId());
        }

        // calculate and update average rating of the movie base on all ratings
        double avgRating = updateAvgMovieRating(movieRatingChangeRequest.getMovieId());

        //return response object
        return fillMovieRatingChangeResponse(movieRatingChangeRequest.getMovieId(),
                movieRatingChangeRequest.getRating(),
                avgRating);
    }

    /**
     * Update rating in the database
     *
     * @param movieRatingChangeRequest
     * @throws RequestFormatException
     */
    public MovieRatingOnChangeResponse updateRating(MovieRatingChangeRequest movieRatingChangeRequest) throws RuntimeException {
        LOGGER.info("Checking if rating value is valid");
        checkRatingValue(movieRatingChangeRequest.getRating());

        LOGGER.info("Checking if user already rated the movie");
        Integer userRateId = getCurrentUserRatingForMovie(movieRatingChangeRequest.getMovieId(),
                movieRatingChangeRequest.getUserId());

        // IF user is updating the rating that he did not set than throw exception
        if (userRateId == null) {
            LOGGER.info("Movie rating for user with id {} was not found", movieRatingChangeRequest.getUserId());
            throw new NotFoundException("Movie Rating not found for user, movie Id = " + movieRatingChangeRequest.getMovieId());
        } else { // If user Rated the movie previously update current row as invalid and add new row with new rating
            jdbcReviewDao.updateRating(userRateId);
            jdbcReviewDao.addRating(movieRatingChangeRequest);
        }
        // calculate and update average rating of the movie base on all ratings
        double avgRating = updateAvgMovieRating(movieRatingChangeRequest.getMovieId());

        //return response object
        return fillMovieRatingChangeResponse(movieRatingChangeRequest.getMovieId(),
                movieRatingChangeRequest.getRating(),
                avgRating);
    }

    /**
     * Checks if RatingValue is correct, should be between 0 or 10
     *
     * @param RatingValue
     * @throws RequestFormatException
     */
    private void checkRatingValue(double RatingValue) throws RequestFormatException {
        if (RatingValue < 0 || RatingValue > 10)
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

    /**
     * Get current rating of the user in the database
     *
     * @param movieId
     * @param userId
     * @return
     */
    private Integer getCurrentUserRatingForMovie(int movieId, int userId) {
        return jdbcReviewDao.getUserMovieRatingId(movieId, userId);
    }

    /**
     * Filling the object for Response
     *
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
}
