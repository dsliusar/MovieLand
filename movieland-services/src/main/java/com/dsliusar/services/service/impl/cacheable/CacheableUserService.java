package com.dsliusar.services.service.impl.cacheable;

import com.dsliusar.constants.Constant;
import com.dsliusar.http.entities.UserCredentialsRequest;
import com.dsliusar.persistence.entity.User;
import com.dsliusar.services.cache.CacheService;
import com.dsliusar.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class CacheableUserService implements UserService {
    @Autowired
    private CacheService concurrentHashMapService;

    @Override
    public User checkUserByCredentials(UserCredentialsRequest userCredentials) {
        Map<Integer, User> userMap;
        Map<Integer, List<User>> allUsersMap = getAllUsers();
        User user = null;
        for (Map.Entry<Integer, List<User>> userEntry : allUsersMap.entrySet()) {
            for (User userEntries : userEntry.getValue()) {
                if (userEntries.getUserEmail().equalsIgnoreCase(userCredentials.getUserEmail())
                        && userEntries.getUserPassword().equalsIgnoreCase(userCredentials.getUserPassword())) {
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
        return user;
    }

    @Override
    public Map<Integer, List<User>> getAllUsers() {
        return (Map<Integer, List<User>>) concurrentHashMapService.getCacheById(Constant.ALL_USERS_CACHE);
    }
}
