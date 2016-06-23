package com.dsliusar.services.security;

import com.dsliusar.exceptions.MovieLandSecurityException;
import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.persistence.entity.User;

public interface SecureTokenService {

    String issueToken(User user);
    UserSecureTokenEntity getUserByToken(String token) throws MovieLandSecurityException;
    void deleteToken(String token);
    boolean checkIfNotExpired(String token);
    void performHousekeeping();

}
