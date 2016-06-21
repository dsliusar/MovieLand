package com.dsliusar.services.service.impl;

import com.dsliusar.http.entities.UserCredentialsRequest;
import com.dsliusar.persistence.dao.UserDao;
import com.dsliusar.persistence.entity.User;
import com.dsliusar.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GenericUserService implements UserService {

    @Autowired
    private UserDao jdbcUserDao;

    @Override
    public User checkUserByCredentials(UserCredentialsRequest userCredentials) {
        Map<Integer, User> userMap = jdbcUserDao.checkUserByCredentials(userCredentials);
        User user = null;
        for (Map.Entry<Integer,User> userEntry : userMap.entrySet()){
            user = userEntry.getValue();
        }
        return user;
    }

    @Override
    public Map<Integer,User> getAllUsers() {
        return jdbcUserDao.getAllUsersMap();
    }
}
