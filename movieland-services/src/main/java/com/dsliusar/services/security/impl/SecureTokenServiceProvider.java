package com.dsliusar.services.security.impl;

import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.persistence.entity.User;
import com.dsliusar.services.security.SecureTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class SecureTokenServiceProvider implements SecureTokenService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private Map<String, UserSecureTokenEntity> tokenHashMap = new HashMap<>();

    @Value("${service.tokenLiveTime}")
    private int tokenLiveTime;

    @Override
    public String issueToken(User user) {
        LOGGER.info("Issuing a token for the user {}", user.getUserEmail());

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("body of the user {} ", user);
        }
        StringBuilder token = new StringBuilder();
        token.append(UUID.randomUUID().toString());
        UserSecureTokenEntity userSecureTokenEntity = fillUserTokenEntity(user);
        tokenHashMap.put(token.toString(), userSecureTokenEntity);
        return token.toString();
    }

    @Override
    public UserSecureTokenEntity getUserByToken(String token) throws IllegalAccessException {
        if (!checkIfExists(token)) {
            LOGGER.error("Token {} do not exists, please sign in again", token);
            throw new IllegalAccessException("Token " + token + " do not exists");
        }
        if (!checkIfNotExpired(token)) {
            deleteToken(token);
            throw new IllegalAccessException("Token " + token + " is expired");
        }
        return tokenHashMap.get(token);
    }

    public boolean checkIfNotExpired(String token) {
        return tokenHashMap.get(token).getValidFrom().isBefore(tokenHashMap.get(token).getValidTo());
    }

    private boolean checkIfExists(String token) {
        return tokenHashMap.containsKey(token);
    }


    @Override
    public int performHousekeeping() {
        int deletedTokens = 0;
        for (Map.Entry<String, UserSecureTokenEntity> mapEntries : tokenHashMap.entrySet()) {
            if (checkIfNotExpired(mapEntries.getKey())) {
                deleteToken(mapEntries.getKey());
                deletedTokens++;
            }
        }
        return deletedTokens;
    }

    public void deleteToken(String token) {
        LOGGER.info("Deleting token {} due to expiration", token);
        tokenHashMap.remove(token);
    }

    private LocalDateTime getTimeInserted() {
        return LocalDateTime.now();
    }

    private LocalDateTime getTimeExpired() {
        LocalDateTime nextTime = getTimeInserted().plusHours(tokenLiveTime);
        return nextTime;
    }

    private UserSecureTokenEntity fillUserTokenEntity(User user) {
        UserSecureTokenEntity userSecureTokenEntity = new UserSecureTokenEntity();
        userSecureTokenEntity.setUserName(user.getUserName());
        userSecureTokenEntity.setUserId(user.getUserId());
        userSecureTokenEntity.setUserRole(user.getUserRole());
        userSecureTokenEntity.setValidFrom(getTimeInserted());
        userSecureTokenEntity.setValidTo(getTimeExpired());
        return userSecureTokenEntity;
    }

    public void setTokenHashMap(Map<String, UserSecureTokenEntity> tokenHashMap) {
        this.tokenHashMap = tokenHashMap;
    }

}
