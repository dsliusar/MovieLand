package com.dsliusar.services.security;

import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.persistence.entity.User;

public interface SecureTokenService {

    String issueToken(User user);
    UserSecureTokenEntity isValidToken(String token);
    void deleteToken(String token);
    boolean checkIfExpired(String token);
    int performHousekeeping();

}
