package com.dsliusar.persistence.dao.jdbc.impl;

import com.dsliusar.persistence.dao.UserDao;
import com.dsliusar.persistence.dao.jdbc.mapper.SingleUserRowMapper;
import com.dsliusar.persistence.dao.jdbc.mapper.UsersRowMapper;
import com.dsliusar.persistence.entity.User;
import com.dsliusar.http.entities.UserCredentialsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by DSliusar on 06.06.2016.
 */

@Repository
public class JdbcUserDao implements UserDao {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String insertUserSQL;

    @Autowired
    private String getUserByCredentials;

    @Autowired
    private String getAllUsers;

    @Autowired
    private UsersRowMapper usersRowMapper;

    @Autowired
    private SingleUserRowMapper singleUserRowMapper;

    @Override
    public void insert(Map<String, User> userMap) {
        LOGGER.info("Start populating User table ");
        for (Map.Entry<String, User> arrUsers : userMap.entrySet()) {

            jdbcTemplate.update(insertUserSQL, arrUsers.getValue().getUserId(),
                                               arrUsers.getValue().getUserName(),
                                               arrUsers.getValue().getUserEmail(),
                                               arrUsers.getValue().getUserPassword(),
                                               arrUsers.getValue().getUserRole());
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Inserting Next Rows to DB : " + arrUsers);
            }
        }
        LOGGER.info("All users to user table were inserted");
    }

    @Override
    public Map<Integer, User> checkUserByCredentials(UserCredentialsRequest userCredentials) {
        LOGGER.info("Get user by credentials");
        long startTime = System.currentTimeMillis();
        Map<Integer, User> userMap = jdbcTemplate.query(getUserByCredentials,
                                     new Object[]{userCredentials.getUserEmail(),userCredentials.getUserPassword()},
                                     singleUserRowMapper);
        LOGGER.info("Finished getting user by credentials, it took {}", System.currentTimeMillis() - startTime);
        return userMap;
    }

    @Override
    public Map<Integer, User> getAllUsersMap() {
        LOGGER.info("Get All Users");
        long startTime = System.currentTimeMillis();
        Map<Integer, User> userMap = jdbcTemplate.query(getAllUsers, singleUserRowMapper);
        LOGGER.info("Finished getting all users, it took {}", System.currentTimeMillis() - startTime);
        return userMap;
    }


    @Override
    public List<User> getAllUsers() {
        LOGGER.info("Get all users from database");
        long startTime = System.currentTimeMillis();
        List<User> usersList = jdbcTemplate.query(getAllUsers, usersRowMapper);
        LOGGER.info("Finished getting all users, it took {}", System.currentTimeMillis() - startTime);
        return usersList;
    }

}
