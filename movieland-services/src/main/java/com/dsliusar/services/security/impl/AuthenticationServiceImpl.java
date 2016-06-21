package com.dsliusar.services.security.impl;

import com.dsliusar.http.entities.UserCredentialsRequest;
import com.dsliusar.persistence.entity.User;
import com.dsliusar.services.security.AuthenticationService;
import com.dsliusar.services.security.SecureTokenService;
import com.dsliusar.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.naming.AuthenticationException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserService cacheableUserService;

    @Autowired
    private SecureTokenService secureTokenServiceProvider;

    @Override
    public String authenticateUser(UserCredentialsRequest userCredentialsRequest) throws AuthenticationException {
        User user = cacheableUserService.checkUserByCredentials(userCredentialsRequest);
        String token;
        if (user == null) {
            throw new AuthenticationException("Invalid user or Password");
        } else {
            token = secureTokenServiceProvider.issueToken(user);
        }
        return token;
    }

    @Override
    public Boolean isValidToken(String token) throws IllegalAccessException {
        Boolean isValidToken = secureTokenServiceProvider.isValidToken(token);
        if (!isValidToken) {
            throw new IllegalAccessException("Token " + token + " is outdated or invalid");
        } else {
            return isValidToken;
        }
    }
}
