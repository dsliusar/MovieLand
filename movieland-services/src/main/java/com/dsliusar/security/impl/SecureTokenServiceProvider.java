package com.dsliusar.security.impl;

import com.dsliusar.entity.User;
import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.security.SecureTokenService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SecureTokenServiceProvider implements SecureTokenService {

    private Map<String, UserSecureTokenEntity> tokenHashMap = new HashMap<>();

    @Override
    public String issueToken(User user) {
        StringBuilder token = new StringBuilder();
        token.append(UUID.randomUUID().toString() + System.currentTimeMillis());
        UserSecureTokenEntity userSecureTokenEntity = fillUserTokenEntity(user);
        tokenHashMap.put(token.toString(), userSecureTokenEntity);
        return token.toString();
    }

    @Override
    public Boolean isValidToken(String token) {
        if (tokenHashMap.containsKey(token)) {
            return checkIfExpired(token);
        } else {
            return false;
        }}

    public Boolean checkIfExpired(String token) {
        return tokenHashMap.get(token).getValidFrom().before(tokenHashMap.get(token).getValidTo());
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


    private Date getTimeInserted() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }


    private Date getTimeExpired() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 4);
        return calendar.getTime();
    }

    private UserSecureTokenEntity fillUserTokenEntity(User user){
        UserSecureTokenEntity userSecureTokenEntity = new UserSecureTokenEntity();
        userSecureTokenEntity.setUserName(user.getUserName());
        userSecureTokenEntity.setUserId(user.getUserId());
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
