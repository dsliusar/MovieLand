package com.dsliusar.services.security;

import com.dsliusar.persistence.entity.User;

public interface SecureTokenService {

    String issueToken(User user);
    Boolean isValidToken(String token);
    void deleteToken(String token);
    Boolean checkIfExpired(String token);
    int performHousekeeping();

}
