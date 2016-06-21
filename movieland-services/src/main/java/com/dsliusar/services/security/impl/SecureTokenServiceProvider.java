package com.dsliusar.services.security.impl;

import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.persistence.entity.User;
import com.dsliusar.services.security.SecureTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class SecureTokenServiceProvider implements SecureTokenService {

    private Map<String, UserSecureTokenEntity> tokenHashMap = new HashMap<>();

    @Value("${service.tokenLiveTime}")
    int tokenLiveTime;

    @Override
    public String issueToken(User user) {
        StringBuilder token = new StringBuilder();
        token.append(UUID.randomUUID().toString());
        UserSecureTokenEntity userSecureTokenEntity = fillUserTokenEntity(user);
        tokenHashMap.put(token.toString(), userSecureTokenEntity);
        return token.toString();
    }

    @Override
    public UserSecureTokenEntity isValidToken(String token){
            return tokenHashMap.get(token);
       }

    public boolean checkIfExpired(String token) {
        return tokenHashMap.get(token).getValidFrom().isBefore(tokenHashMap.get(token).getValidTo());
    }

    @Override
    public int performHousekeeping() {
        int deletedTokens = 0;
        for(Map.Entry<String,UserSecureTokenEntity> mapEntries : tokenHashMap.entrySet()){
            if(checkIfExpired(mapEntries.getKey())){
                deleteToken(mapEntries.getKey());
                deletedTokens++;
            }
        }
        return deletedTokens;
    }


    private LocalDateTime getTimeInserted() {
        return LocalDateTime.now();
    }


    private LocalDateTime getTimeExpired() {
        LocalDateTime nextTime = getTimeInserted().plusHours(tokenLiveTime);
        return nextTime;
    }

    private UserSecureTokenEntity fillUserTokenEntity(User user){
        UserSecureTokenEntity userSecureTokenEntity = new UserSecureTokenEntity();
        userSecureTokenEntity.setUserName(user.getUserName());
        userSecureTokenEntity.setUserId(user.getUserId());
        userSecureTokenEntity.setUserRole(user.getUserRole());
        userSecureTokenEntity.setValidFrom(getTimeInserted());
        userSecureTokenEntity.setValidTo(getTimeExpired());
        return userSecureTokenEntity;
    }

    public void deleteToken(String token) {
        tokenHashMap.remove(token);
    }

    public void setTokenHashMap(Map<String, UserSecureTokenEntity> tokenHashMap) {
        this.tokenHashMap = tokenHashMap;
    }

}
