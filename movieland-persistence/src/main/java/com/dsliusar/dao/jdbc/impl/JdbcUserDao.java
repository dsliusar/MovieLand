package com.dsliusar.dao.jdbc.impl;

import com.dsliusar.dao.UserDao;
import com.dsliusar.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

    @Override
    public void insert(Map<String, User> userMap) {
        LOGGER.info("Start populating User table ");
        for (Map.Entry<String, User> arrUsers : userMap.entrySet()) {

            jdbcTemplate.update(insertUserSQL, arrUsers.getValue().getUserId(),
                                               arrUsers.getValue().getUserName(),
                                               arrUsers.getValue().getUserEmail(),
                                               arrUsers.getValue().getUserPassword());
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Inserting Next Rows to DB : " + arrUsers);
            }
        }
        LOGGER.info("All users to user table were inserted");
    }
}
