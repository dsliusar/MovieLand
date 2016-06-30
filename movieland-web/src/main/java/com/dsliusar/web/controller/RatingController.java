package com.dsliusar.web.controller;

import com.dsliusar.services.security.AuthenticationService;
import com.dsliusar.services.service.ReviewService;
import com.dsliusar.tools.exceptions.MovieLandSecurityException;
import com.dsliusar.tools.exceptions.NotFoundException;
import com.dsliusar.tools.exceptions.RequestFormatException;
import com.dsliusar.tools.http.entities.MovieRatingChangeRequest;
import com.dsliusar.tools.http.entities.MovieRatingOnChangeResponse;
import com.dsliusar.tools.http.entities.UserSecureTokenEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Rating operations
 * Controllers can add and update user Ratings
 */
@RestController
@RequestMapping(value = "/v1")
public class RatingController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReviewService genericReviewService;

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Adding Rating of the movie that was entered by user to the database
     * In case of any issues throws exception
     * @param movieRatingChangeRequest
     * @param token
     * @return
     * @throws RequestFormatException
     * @throws MovieLandSecurityException
     * @throws NotFoundException
     */
    @RequestMapping(value = "/rating/add",
                    method = RequestMethod.POST,
                    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<MovieRatingOnChangeResponse> addUserMovieRating(@RequestBody MovieRatingChangeRequest movieRatingChangeRequest,
                                                                          @RequestHeader(value = "security-token") String token) throws RequestFormatException, MovieLandSecurityException, NotFoundException {        LOGGER.info("Received request to add rating for movie {}" , movieRatingChangeRequest.getMovieId());
        UserSecureTokenEntity userSecure =  authenticationService.getUserByToken(token);
        MDC.put("userLogin", userSecure.getUserName());
        movieRatingChangeRequest.setUserId(userSecure.getUserId());
        MovieRatingOnChangeResponse movieRatignAddResponse =  genericReviewService.addRating(movieRatingChangeRequest);
        MDC.remove("userLogin");
        return new ResponseEntity<>(movieRatignAddResponse, HttpStatus.OK);
    }


    /**
     * Updating Rating of Movie for the specific user in the database
     * In case of any issues throws exception
     * @param movieRatingChangeRequest
     * @param token
     * @return
     * @throws MovieLandSecurityException
     * @throws RequestFormatException
     * @throws NotFoundException
     */
    @RequestMapping(value = "/rating/update",
                    method = RequestMethod.PUT,
                    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<MovieRatingOnChangeResponse> updateUserMovieRating(@RequestBody MovieRatingChangeRequest movieRatingChangeRequest,
                                                                             @RequestHeader(value = "security-token") String token) throws MovieLandSecurityException, RequestFormatException, NotFoundException {
        UserSecureTokenEntity userSecure =  authenticationService.getUserByToken(token);
        MDC.put("userLogin", userSecure.getUserName());
        movieRatingChangeRequest.setUserId(userSecure.getUserId());
        MovieRatingOnChangeResponse movieRatingUpdateResponse =  genericReviewService.updateRating(movieRatingChangeRequest);
        MDC.remove("userLogin");
        return new ResponseEntity<>(movieRatingUpdateResponse, HttpStatus.OK);
    }

}
