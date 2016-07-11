package com.dsliusar.services.service.impl;

import com.dsliusar.tools.entities.http.UserCredentialsRequest;
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
        return jdbcUserDao.checkUserByCredentials(userCredentials);
    }

    @Override
    public Map<Integer,User> getAllUsers() {
        return jdbcUserDao.getAllUsersMap();
    }
}
