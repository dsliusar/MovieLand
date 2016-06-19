package com.dsliusar.service.impl;

import com.dsliusar.dao.UserDao;
import com.dsliusar.entity.User;
import com.dsliusar.http.entities.UserCredentialsRequest;
import com.dsliusar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GenericUserService implements UserService {
    @Autowired
    UserDao jdbcUserDao;

    @Override
    public Map<Integer, User> checkUserByCredentials(UserCredentialsRequest userCredentials) {
        return jdbcUserDao.checkUserByCredentials(userCredentials);
    }

    @Override
    public Map<Integer, List<User>> getAllUsers() {
        return jdbcUserDao.getAllUsers();
    }
}
