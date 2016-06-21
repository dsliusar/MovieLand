package com.dsliusar.services.security;

import com.dsliusar.http.entities.UserCredentialsRequest;

import javax.naming.AuthenticationException;

public interface AuthenticationService {

    String authenticateUser(UserCredentialsRequest userCredentialsRequest) throws AuthenticationException;
    Boolean isValidToken(String token) throws IllegalAccessException;
}
