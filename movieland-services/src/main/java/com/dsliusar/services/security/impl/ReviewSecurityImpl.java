package com.dsliusar.services.security.impl;

import com.dsliusar.enums.RolesEnum;
import com.dsliusar.exceptions.MovieLandSecurityException;
import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.persistence.entity.Review;
import com.dsliusar.services.security.ReviewSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReviewSecurityImpl implements ReviewSecurity {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public void checkUserDeletePermission(UserSecureTokenEntity userSecureEntity, Review DeletingReview)
            throws MovieLandSecurityException {
        if (userSecureEntity.getUserId() != DeletingReview.getUserId()
                && !(userSecureEntity.getUserRole().equalsIgnoreCase(RolesEnum.ADMIN.toString()))) {
            LOGGER.error("Deleting of the review {} is prohibited for this user {}",
                         DeletingReview.getReviewId(),
                         userSecureEntity.getUserName());
            throw new MovieLandSecurityException("Deleting review id is not owning by this user");
        }
    }
}
