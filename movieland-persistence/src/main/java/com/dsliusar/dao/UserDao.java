package com.dsliusar.dao;

import com.dsliusar.entity.User;

import java.util.Map;

public interface UserDao{
     void insert(Map<String, User> userMap);

}
