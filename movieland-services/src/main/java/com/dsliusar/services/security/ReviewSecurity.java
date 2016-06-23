package com.dsliusar.services.security;

import com.dsliusar.exceptions.MovieLandSecurityException;
import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.persistence.entity.Review;

public interface ReviewSecurity {

    void checkUserDeletePermission(UserSecureTokenEntity userSecureEntity, Review DeletingReview) throws MovieLandSecurityException;
}
