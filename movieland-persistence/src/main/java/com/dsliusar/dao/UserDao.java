package com.dsliusar.dao;

import com.dsliusar.entity.User;
import com.dsliusar.http.entities.UserCredentialsRequest;

import java.util.List;
import java.util.Map;

public interface UserDao{
     void insert(Map<String, User> userMap);
     Map<Integer,User> checkUserByCredentials(UserCredentialsRequest userCredentials);
     Map<Integer,List<User>> getAllUsers();
}
