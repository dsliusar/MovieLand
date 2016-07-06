package com.dsliusar.services.security.impl;

import com.dsliusar.services.service.ReviewService;
import com.dsliusar.tools.enums.Roles;
import com.dsliusar.tools.exceptions.MovieLandSecurityException;
import com.dsliusar.tools.exceptions.NotFoundException;
import com.dsliusar.tools.entities.http.ReviewAddRequest;
import com.dsliusar.tools.entities.http.UserSecureTokenEntity;
import com.dsliusar.persistence.entity.Review;
import com.dsliusar.services.security.ReviewSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Security Service that checks permissions to perform all operation over Review entity.
 * @throws MovieLandSecurityException - in case of prohibited operations
 * @throws NotFoundException - in case any request did not found any result
 */

@Service
public class ReviewSecurityImpl implements ReviewSecurity {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReviewService genericReviewService;

    @Override
    public void checkUserDeletePermission(UserSecureTokenEntity userSecureEntity, Review deletingReview)
            throws MovieLandSecurityException {
        if (userSecureEntity.getUserId() != deletingReview.getUserId()
                && !(userSecureEntity.getUserRole().equals(Roles.ADMIN))) {
            LOGGER.error("Deleting of the review {} is prohibited for this user {}",
                    deletingReview.getReviewId(),
                    userSecureEntity.getUserName());
            throw new MovieLandSecurityException("Deleting review id is not owning by this user");
        }
    }

    @Override
    public void removeReviewSecurity(int reviewId, UserSecureTokenEntity userSecureEntity) throws MovieLandSecurityException, NotFoundException {
        Review reviewById = genericReviewService.getReviewById(reviewId);
        if (reviewById == null) {
            LOGGER.error("Review by id {} was not found in database", reviewId);
            throw new NotFoundException("Deleting review " + reviewId + " was not found");
        }
           checkUserDeletePermission(userSecureEntity, reviewById);
           genericReviewService.removeReview(reviewId);
    }

    @Override
    public void addReviewSecurity(ReviewAddRequest reviewAddRequest) {
        genericReviewService.addReview(reviewAddRequest);
    }
}
