package com.dsliusar.services.security;

import com.dsliusar.exceptions.MovieLandSecurityException;
import com.dsliusar.http.entities.UserCredentialsRequest;
import com.dsliusar.http.entities.UserSecureTokenEntity;

public interface AuthenticationService {

    String authenticateUser(UserCredentialsRequest userCredentialsRequest) throws MovieLandSecurityException;
    UserSecureTokenEntity getUserByToken(String token) throws MovieLandSecurityException;
}
