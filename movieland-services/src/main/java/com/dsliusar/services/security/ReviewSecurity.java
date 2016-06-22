package com.dsliusar.services.security;

import com.dsliusar.exceptions.MovieLandSecurityException;
import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.persistence.entity.Review;

/**
 * Created by DSliusar on 22.06.2016.
 */
public interface ReviewSecurity {

    void checkDeletePermission(UserSecureTokenEntity userSecureEntity, Review DeletingReview) throws MovieLandSecurityException;
}
