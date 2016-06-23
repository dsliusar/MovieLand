package com.dsliusar.services.security.impl;

import com.dsliusar.exceptions.MovieLandSecurityException;
import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.persistence.entity.User;
import com.dsliusar.services.security.SecureTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SecureTokenServiceProvider implements SecureTokenService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private Map<String, UserSecureTokenEntity> tokenMap = new ConcurrentHashMap<>();

    @Value("${service.tokenLiveTime}")
    private int tokenLiveTime;

    /**
     * Issuing the token to the user.
     * @return token
     */
    @Override
    public String issueToken(User user) {
        LOGGER.info("Issuing a token for the user {}", user.getUserEmail());
        String token = UUID.randomUUID().toString();
        UserSecureTokenEntity userSecureTokenEntity = fillUserTokenEntity(user);
        tokenMap.put(token, userSecureTokenEntity);
        return token;
    }
    /**
     * Getting user by token.
     *
     * Logging count of deleted(expired) tokens from the Map of tokens
     * @throws MovieLandSecurityException
     */
    @Override
    public UserSecureTokenEntity getUserByToken(String token) throws MovieLandSecurityException {
        if (!checkIfExists(token)) {
            LOGGER.error("Token {} do not exists, please sign in again", token);
            throw new MovieLandSecurityException("Token " + token + " do not exists");
        }
        if (!checkIfNotExpired(token)) {
            deleteToken(token);
            throw new MovieLandSecurityException("Token " + token + " is expired");
        }
        return tokenMap.get(token);
    }

    public boolean checkIfNotExpired(String token) {
        return tokenMap.get(token).getValidFrom().isBefore(tokenMap.get(token).getValidTo());
    }

    private boolean checkIfExists(String token) {
        return tokenMap.containsKey(token);
    }

    public void deleteToken(String token) {
        LOGGER.info("Deleting token {} due to expiration", token);
        tokenMap.remove(token);
    }

    private LocalDateTime getTimeInserted() {
        return LocalDateTime.now();
    }

    private LocalDateTime getTimeExpired() {
        return getTimeInserted().plusHours(tokenLiveTime);
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


    public void setTokenMap(Map<String, UserSecureTokenEntity> tokenMap) {
        this.tokenMap = tokenMap;
    }

    /**
     * Perform housekeeping of tokens, expiration time of token is 4 hours.
     * Method is calling from context every 30 minutes by Spring Scheduler Executor
     * Logging count of deleted(expired) tokens from the Map of tokens
     */
    @Scheduled(cron = "${service.cronTokensHouseKeepingInterval}")
    @Override
    public void performHousekeeping() {
        LOGGER.info("Performing Housekeeping of Tokens");
        int deletedTokens = 0;
        for (Map.Entry<String, UserSecureTokenEntity> mapEntries : tokenMap.entrySet()) {
            if (checkIfNotExpired(mapEntries.getKey())) {
                deleteToken(mapEntries.getKey());
                deletedTokens++;
            }
        }
        LOGGER.info("Tokens housekeeping finished, next count deleted = {}", deletedTokens);
    }

}
