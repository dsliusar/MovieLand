package com.dsliusar.persistence.dao;

import com.dsliusar.persistence.entity.User;
import com.dsliusar.http.entities.UserCredentialsRequest;

import java.util.List;
import java.util.Map;

public interface UserDao{
     void insert(Map<String, User> userMap);
     Map<Integer,User> checkUserByCredentials(UserCredentialsRequest userCredentials);
     Map<Integer,User> getAllUsersMap();
     List<User> getAllUsers();
}
