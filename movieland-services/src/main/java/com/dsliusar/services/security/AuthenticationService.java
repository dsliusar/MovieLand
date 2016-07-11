package com.dsliusar.services.security;

import com.dsliusar.tools.exceptions.MovieLandSecurityException;
import com.dsliusar.tools.entities.http.UserCredentialsRequest;
import com.dsliusar.tools.entities.http.UserSecureTokenEntity;

public interface AuthenticationService {

    String authenticateUser(UserCredentialsRequest userCredentialsRequest) throws MovieLandSecurityException;
    UserSecureTokenEntity getUserByToken(String token) throws MovieLandSecurityException;
}
