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

    @RequestMapping(value = "/review", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<String> addMovieReview(@RequestBody ReviewAddRequestEntity reviewAddRequest,
                                                  @RequestHeader(value = "security-token") String token) throws IllegalRoleException {

        LOGGER.info("Deleting requested review");
        UserSecureTokenEntity userSecureTokenEntity;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("The request of body is next {} ", reviewAddRequest);
        }
        try {
            userSecureTokenEntity =  authenticationService.getUserByToken(token);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(), token);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }

         if (RolesEnum.validateRole(userSecureTokenEntity.getUserRole())){
             genericReviewService.addReview(reviewAddRequest);
         } else {
             LOGGER.error("User Role is prohibited to updated reviews, role = {}", userSecureTokenEntity.getUserRole());
             throw new IllegalRoleException("User Role is prohibited to updated reviews, role = "
                     + userSecureTokenEntity.getUserRole());
         }
         return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @RequestMapping(value = "/review", method = RequestMethod.DELETE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeMovieReview(@RequestBody int reviewId,
                                                    @RequestHeader(value = "security-token") String token) throws IllegalRoleException {

        LOGGER.info("Adding requested review");
        UserSecureTokenEntity userSecureTokenEntity;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("The request of body is next {} ", reviewId);
        }
        try {
            userSecureTokenEntity =  authenticationService.getUserByToken(token);
            if (RolesEnum.validateRole(userSecureTokenEntity.getUserRole())) {
                genericReviewService.removeReview(userSecureTokenEntity, reviewId);
            }
            else {
                LOGGER.error("User Role is prohibited to updated reviews, role = {}", userSecureTokenEntity.getUserRole());
                throw new IllegalRoleException("User Role is prohibited to updated reviews, role = "
                        + userSecureTokenEntity.getUserRole());
            }
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(), token);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
        } catch (IllegalDeleteException e) {
            LOGGER.error(e.getMessage(),reviewId);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
