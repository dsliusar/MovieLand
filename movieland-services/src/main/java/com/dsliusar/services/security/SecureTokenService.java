package com.dsliusar.services.security;

import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.persistence.entity.User;

public interface SecureTokenService {

    String issueToken(User user);
    UserSecureTokenEntity getUserByToken(String token) throws IllegalAccessException;
    void deleteToken(String token);
    boolean checkIfNotExpired(String token);
    int performHousekeeping();

}
