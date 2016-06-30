package com.dsliusar.services.security;

import com.dsliusar.tools.exceptions.MovieLandSecurityException;
import com.dsliusar.tools.exceptions.NotFoundException;
import com.dsliusar.tools.http.entities.ReviewAddRequest;
import com.dsliusar.tools.http.entities.UserSecureTokenEntity;
import com.dsliusar.persistence.entity.Review;

public interface ReviewSecurity {

    void checkUserDeletePermission(UserSecureTokenEntity userSecureEntity, Review DeletingReview) throws MovieLandSecurityException;
    void removeReviewSecurity(int reviewId,UserSecureTokenEntity userSecureEntity) throws MovieLandSecurityException, NotFoundException;
    void addReviewSecurity(ReviewAddRequest reviewAddRequest);

}
