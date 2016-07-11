package com.dsliusar.services.service;

import com.dsliusar.tools.entities.http.UserCredentialsRequest;
import com.dsliusar.persistence.entity.User;

import java.util.Map;

public interface UserService {

    User checkUserByCredentials(UserCredentialsRequest userCredentials);
    Map<Integer,User> getAllUsers();
}
