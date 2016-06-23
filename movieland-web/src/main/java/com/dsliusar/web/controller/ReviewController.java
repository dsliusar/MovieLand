package com.dsliusar.web.controller;

import com.dsliusar.annotations.SecurityRoles;
import com.dsliusar.enums.RolesEnum;
import com.dsliusar.exceptions.MovieLandSecurityException;
import com.dsliusar.exceptions.NotFoundException;
import com.dsliusar.http.entities.ReviewAddRequest;
import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.services.security.AuthenticationService;
import com.dsliusar.services.security.handler.SecurityRoleHandlerService;
import com.dsliusar.services.service.ReviewService;
import com.dsliusar.web.dto.ExceptionResponseDto;
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
    private ReviewService genericReviewService;

    @Autowired
    private SecurityRoleHandlerService securityRoleHandlerService;

    @SecurityRoles(roles = {RolesEnum.USER})
    @RequestMapping(value = "/review", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMovieReview(@RequestBody ReviewAddRequest reviewAddRequest,
                                            @RequestHeader(value = "security-token") String token) {
        if (reviewAddRequest == null) {
            LOGGER.warn("Review Add request was not provided");
            return new ResponseEntity<>(new SingleMessageResponseDto("Please enter review to add"),
                    HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("Inserting requested review");
        UserSecureTokenEntity userSecureTokenEntity;
        try {
            userSecureTokenEntity = authenticationService.getUserByToken(token);

            //checking roles from annotation and role of the user
            securityRoleHandlerService.handle(ReviewController.class,
                    Thread.currentThread().getStackTrace()[1].getMethodName(),
                    userSecureTokenEntity.getUserRole());

            genericReviewService.addReview(reviewAddRequest);
        } catch (MovieLandSecurityException e) {
            LOGGER.error(e.getMessage(), token);
            return new ResponseEntity<>(new ExceptionResponseDto(e.getMessage()),
                    HttpStatus.FORBIDDEN);
        }
        LOGGER.info("The review {} have been added successfully", reviewAddRequest);

        return new ResponseEntity<>(new SingleMessageResponseDto("The review have been added successfully"),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/review/{reviewId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeMovieReview(@PathVariable Integer reviewId,
                                               @RequestHeader(value = "security-token") String token) {
        if (reviewId == null) {
            LOGGER.warn("Review Id was not provide during request");
            return new ResponseEntity<>(new SingleMessageResponseDto("Please enter review ID to delete")
                    , HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("Deleting requested review, {}", reviewId);
        UserSecureTokenEntity userSecureTokenEntity;
        try {
            userSecureTokenEntity = authenticationService.getUserByToken(token);

            //checking roles from annotation and role of the user
            securityRoleHandlerService.handle(ReviewController.class,
                    Thread.currentThread().getStackTrace()[1].getMethodName(),
                    userSecureTokenEntity.getUserRole());

            genericReviewService.removeReview(userSecureTokenEntity, reviewId);
        } catch (MovieLandSecurityException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(new ExceptionResponseDto(e.getMessage()),
                    HttpStatus.FORBIDDEN);
        } catch (NotFoundException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(new ExceptionResponseDto(e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("Review {} was deleted by user {} successfully;",
                reviewId,
                userSecureTokenEntity.getUserName());

        return new ResponseEntity<>(new SingleMessageResponseDto("Review = " + reviewId + " were deleted")
                    , HttpStatus.OK);
    }


}
