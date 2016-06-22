package com.dsliusar.services.security.impl;

import com.dsliusar.http.entities.UserCredentialsRequest;
import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.persistence.entity.User;
import com.dsliusar.services.security.AuthenticationService;
import com.dsliusar.services.security.SecureTokenService;
import com.dsliusar.services.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService genericUserService;

    @Autowired
    private SecureTokenService secureTokenServiceProvider;

    @Override
    public String authenticateUser(UserCredentialsRequest userCredentialsRequest) throws AuthenticationException {
        User user = genericUserService.checkUserByCredentials(userCredentialsRequest);
        String token;
        if (user == null) {
            throw new AuthenticationException("Invalid user or Password");
        } else {
            token = secureTokenServiceProvider.issueToken(user);
        }
        return token;
    }

    @Override
    public UserSecureTokenEntity getUserByToken(String token) throws IllegalAccessException {
        if (token == null) {
            LOGGER.error("Token was not provided in the request");
            throw new IllegalAccessException("Token was not provided during request");
        }
        UserSecureTokenEntity userSecureTokenEntity;
        try {
            userSecureTokenEntity = secureTokenServiceProvider.getUserByToken(token);
        } catch (IllegalAccessException e){
            LOGGER.error(e.getMessage());
            throw e;
        }
        return userSecureTokenEntity;
    }
}
