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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1")
public class RatingController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReviewService genericReviewService;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/rating/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieRatingOnChangeResponse> addUserMovieRating(@RequestBody MovieRatingChangeRequest movieRatingChangeRequest,
                                                                          @RequestHeader(value = "security-token") String token) throws RequestFormatException, MovieLandSecurityException, NotFoundException {        LOGGER.info("Received request to add rating for movie {}" , movieRatingChangeRequest.getMovieId());

        return new ResponseEntity<>(calculateAndUpdateRating(movieRatingChangeRequest,token), HttpStatus.OK);
    }



    @RequestMapping(value = "/rating/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieRatingOnChangeResponse> updateUserMovieRating(@RequestBody MovieRatingChangeRequest movieRatingChange,
                                                                             @RequestHeader(value = "security-token") String token) throws MovieLandSecurityException, RequestFormatException, NotFoundException {

        return new ResponseEntity<>(calculateAndUpdateRating(movieRatingChange,token), HttpStatus.OK);
    }

    private MovieRatingOnChangeResponse calculateAndUpdateRating(MovieRatingChangeRequest ratingChangeRequest
                                                              , String token) throws MovieLandSecurityException, RequestFormatException, NotFoundException {
        UserSecureTokenEntity userSecure =  authenticationService.getUserByToken(token);
        ratingChangeRequest.setUserId(userSecure.getUserId());
        return genericReviewService.calculateAndUpdateRating(ratingChangeRequest);
    }
}
