package com.dsliusar.services.security;

import com.dsliusar.http.entities.UserCredentialsRequest;
import com.dsliusar.http.entities.UserSecureTokenEntity;

import javax.naming.AuthenticationException;

public interface AuthenticationService {

    String authenticateUser(UserCredentialsRequest userCredentialsRequest) throws AuthenticationException;
    UserSecureTokenEntity getUserByToken(String token) throws IllegalAccessException;
}
