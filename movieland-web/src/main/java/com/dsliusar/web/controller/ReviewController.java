package com.dsliusar.web.controller;

import com.dsliusar.enums.RolesEnum;
import com.dsliusar.exceptions.IllegalDeleteException;
import com.dsliusar.exceptions.IllegalRoleException;
import com.dsliusar.http.entities.ReviewAddRequestEntity;
import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.services.security.AuthenticationService;
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

    @RequestMapping(value = "/review", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addMovieReview(@RequestBody ReviewAddRequestEntity reviewAddRequest,
                                                 @RequestHeader(value = "security-token") String token) throws IllegalRoleException {
        if (reviewAddRequest == null){
            return new ResponseEntity<>("Please enter review to add", HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("Inserting requested review");
        UserSecureTokenEntity userSecureTokenEntity = null;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The request of body is next {} ", reviewAddRequest);
        }
        try {
            userSecureTokenEntity = authenticationService.getUserByToken(token);
            if (RolesEnum.validateRole(userSecureTokenEntity.getUserRole())) {
                genericReviewService.addReview(reviewAddRequest);
            }
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(), token);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (IllegalRoleException e) {
            LOGGER.error("User Role is prohibited to update reviews, role = {}", userSecureTokenEntity.getUserRole());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        LOGGER.info("The review {} have been added successfully", reviewAddRequest);
        return new ResponseEntity<>("The review have been added successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/review/{reviewId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeMovieReview(@PathVariable Integer reviewId,
                                                    @RequestHeader(value = "security-token") String token) throws IllegalRoleException {
        if (reviewId == null){
            return new ResponseEntity<>("Please enter review ID to delete", HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("Deleting requested review, {}", reviewId);
        UserSecureTokenEntity userSecureTokenEntity = null;
        try {
            userSecureTokenEntity = authenticationService.getUserByToken(token);
            if (RolesEnum.validateRole(userSecureTokenEntity.getUserRole())) {
                genericReviewService.removeReview(userSecureTokenEntity, reviewId);
            }
        } catch (IllegalAccessException e) {
            LOGGER.error("Illegal Access with token provided, {};", token);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (IllegalDeleteException e) {
            LOGGER.error("Deleting review is not created by user, {};", reviewId);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (IllegalRoleException e) {
            LOGGER.error("User Role is prohibited to update reviews, role = {};", userSecureTokenEntity.getUserRole());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        LOGGER.info("Review {} was deleted by user {} successfully;",
                    reviewId,
                    userSecureTokenEntity.getUserName());

        return new ResponseEntity<>("Review = " + reviewId + "were deleted;", HttpStatus.OK);
    }
}
