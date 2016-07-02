package com.dsliusar.web.controller;

import com.dsliusar.services.security.AuthenticationService;
import com.dsliusar.services.security.ReviewSecurity;
import com.dsliusar.tools.annotations.SecurityRolesAllowed;
import com.dsliusar.tools.enums.Roles;
import com.dsliusar.tools.http.entities.ReviewAddRequest;
import com.dsliusar.tools.http.entities.UserSecureTokenEntity;
import com.dsliusar.web.dto.SingleMessageResponseDto;
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
    private ReviewSecurity reviewSecurity;

    @SecurityRolesAllowed(roles = {Roles.USER})
    @RequestMapping(value = "/review/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMovieReview(@RequestBody ReviewAddRequest reviewAddRequest,
                                            @RequestHeader(value = "security-token") String token) {
        LOGGER.info("Inserting requested review");
        reviewSecurity.addReviewSecurity(reviewAddRequest);
        LOGGER.info("The review {} have been added successfully", reviewAddRequest);
        return new ResponseEntity<>(new SingleMessageResponseDto("The review have been added successfully"),
                HttpStatus.OK);
    }

    @SecurityRolesAllowed(roles = {Roles.USER, Roles.ADMIN})
    @RequestMapping(value = "/review/delete/{reviewId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeMovieReview(@PathVariable Integer reviewId,
                                               @RequestHeader(value = "security-token") String token){

        LOGGER.info("Deleting requested review, {}", reviewId);
        UserSecureTokenEntity userSecureTokenEntity = authenticationService.getUserByToken(token);
        reviewSecurity.removeReviewSecurity(reviewId, userSecureTokenEntity);
        LOGGER.info("Review {} was deleted by user {} successfully;",
                reviewId,
                userSecureTokenEntity.getUserName());
        return new ResponseEntity<>(new SingleMessageResponseDto("Review with id " + reviewId + " were deleted")
                , HttpStatus.OK);
    }


}
