package com.dsliusar.service.impl.cacheable;

import com.dsliusar.cache.CacheService;
import com.dsliusar.constants.Constant;
import com.dsliusar.entity.User;
import com.dsliusar.http.entities.UserCredentialsRequest;
import com.dsliusar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class CacheableUserService implements UserService {
    @Autowired
    private CacheService concurrentHashMapService;

    @Override
    public Map<Integer, User> checkUserByCredentials(UserCredentialsRequest userCredentials) {
        Map<Integer, User> userMap = null;
        Map<Integer, List<User>> allUsersMap = getAllUsers();
        User user;
        for (Map.Entry<Integer, List<User>> userEntry : allUsersMap.entrySet()) {
            for (User userEntries : userEntry.getValue()) {
                if (userEntries.getUserEmail() == userCredentials.getUserEmail()
                        && userEntries.getUserPassword() == userCredentials.getUserPassword()) {
                    user = new User();
                    userMap = new ConcurrentHashMap<>();
                    user.setUserId(userEntries.getUserId());
                    user.setUserPassword(userEntries.getUserPassword());
                    user.setUserName(userEntries.getUserName());
                    user.setUserEmail(userEntries.getUserEmail());
                    userMap.put(user.getUserId(),user);
                }
            }
        }
        return userMap;
    }

    @Override
    public Map<Integer, List<User>> getAllUsers() {
        return (Map<Integer, List<User>>) concurrentHashMapService.getCacheById(Constant.ALL_USERS_CACHE);
    }
}
