package com.dsliusar.services.service;

import com.dsliusar.persistence.entity.User;
import com.dsliusar.http.entities.UserCredentialsRequest;

import java.util.List;
import java.util.Map;

public interface UserService {

    User checkUserByCredentials(UserCredentialsRequest userCredentials);
    Map<Integer,List<User>> getAllUsers();
}
