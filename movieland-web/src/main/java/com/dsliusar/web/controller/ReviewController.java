package com.dsliusar.web.controller;

import com.dsliusar.annotations.SecurityRoles;
import com.dsliusar.enums.RolesEnum;
import com.dsliusar.exceptions.MovieLandSecurityException;
import com.dsliusar.http.entities.ReviewAddRequestEntity;
import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.services.security.AuthenticationService;
import com.dsliusar.services.security.handler.SecurityRoleHandlerService;
import com.dsliusar.services.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1")
public class ReviewController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ReviewService genericReviewService;

    @Autowired
    private SecurityRoleHandlerService securityRoleHandlerService;

    @SecurityRoles(roles = {RolesEnum.USER})
    @RequestMapping(value = "/review", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addMovieReview(@RequestBody ReviewAddRequestEntity reviewAddRequest,
                                                 @RequestHeader(value = "security-token") String token) {
        if (reviewAddRequest == null) {
            return new ResponseEntity<>("Please enter review to add", HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("Inserting requested review");
        UserSecureTokenEntity userSecureTokenEntity;
        try {
          userSecureTokenEntity = authenticationService.getUserByToken(token);
                if (securityRoleHandlerService.handle(
                        new ReviewController(),
                        Thread.currentThread().getStackTrace()[1].getMethodName(),
                        userSecureTokenEntity.getUserRole()))
          genericReviewService.addReview(reviewAddRequest);

        } catch (MovieLandSecurityException e) {
            LOGGER.error(e.getMessage(), token);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        LOGGER.info("The review {} have been added successfully", reviewAddRequest);
        return new ResponseEntity<>("The review have been added successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/review/{reviewId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeMovieReview(@PathVariable Integer reviewId,
                                                    @RequestHeader(value = "security-token") String token) {
        if (reviewId == null) {
            return new ResponseEntity<>("Please enter review ID to delete", HttpStatus.BAD_REQUEST);
        }
      LOGGER.info("Deleting requested review, {}", reviewId);
        UserSecureTokenEntity userSecureTokenEntity;
        try {
           userSecureTokenEntity = authenticationService.getUserByToken(token);
                if (securityRoleHandlerService.handle(
                        new ReviewController(),
                        Thread.currentThread().getStackTrace()[1].getMethodName(),
                        userSecureTokenEntity.getUserRole()))

           genericReviewService.removeReview(userSecureTokenEntity, reviewId);
        } catch (MovieLandSecurityException e) {
            LOGGER.error(e.getMessage(), token);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        LOGGER.info("Review {} was deleted by user {} successfully;",
                reviewId,
                userSecureTokenEntity.getUserName());

        return new ResponseEntity<>("Review = " + reviewId + "were deleted;", HttpStatus.OK);
    }



}
