package com.dsliusar.services.security;

import com.dsliusar.persistence.entity.User;
import com.dsliusar.http.entities.UserSecureTokenEntity;
import com.dsliusar.services.security.impl.SecureTokenServiceProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SecureTokenServiceProviderTest {

    User user = new User();;
    UserSecureTokenEntity userSecureTokenEntity = new UserSecureTokenEntity();
    SecureTokenServiceProvider secureTokenService = new SecureTokenServiceProvider();

    @Before
    public void fillUserEntity(){
        user.setUserName("Dima");
        user.setUserId(1);
        user.setUserEmail("dmitrii@gmailc.com");
    }

    @Before
    public void fillUserTokenMap(){
        Map<String, UserSecureTokenEntity > tokenHashMap = new HashMap<>();
        userSecureTokenEntity.setUserName(user.getUserName());
        userSecureTokenEntity.setUserId(user.getUserId());
        userSecureTokenEntity.setValidFrom(getTimeInserted());
        userSecureTokenEntity.setValidTo(getTimeExpired());
        tokenHashMap.put(secureTokenService.issueToken(user),userSecureTokenEntity);
        secureTokenService.setTokenMap(tokenHashMap);
    }

    @Test
    public void issueTokenTest() {
        SecureTokenService secure = new SecureTokenServiceProvider();
        String s = secure.issueToken(user);
        Assert.assertNotNull(s);
    }

    @Test
    public void tokenHouseKeepingTest(){
        int expectedTokensDeletion = 1;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        int actualTokensDeleted = secureTokenService.performHousekeeping();
//        Assert.assertEquals(expectedTokensDeletion,actualTokensDeleted);
    }

    private LocalDateTime getTimeInserted() {
        return LocalDateTime.now();
    }


    private LocalDateTime getTimeExpired() {
        LocalDateTime nextTime = getTimeInserted().plusHours(4);
        return nextTime;
    }

}
