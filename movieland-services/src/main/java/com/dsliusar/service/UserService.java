package com.dsliusar.service;

import com.dsliusar.entity.User;
import com.dsliusar.http.entities.UserCredentialsRequest;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<Integer,User> checkUserByCredentials(UserCredentialsRequest userCredentials);
    Map<Integer,List<User>> getAllUsers();
}
