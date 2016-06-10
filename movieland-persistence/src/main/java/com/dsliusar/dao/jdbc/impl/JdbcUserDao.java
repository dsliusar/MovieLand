package com.dsliusar.dao.jdbc.impl;

import com.dsliusar.dao.UserDao;
import com.dsliusar.entity.User;
import com.dsliusar.dao.files.UserFileParser;
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
    private UserFileParser userFileParser;

    @Autowired
    private String insertUserSQL;

    @Override
    public void insert() {
        LOGGER.info("Start populating User table ");
        truncateCommon(jdbcTemplate, "user");
        userFileParser.ParseUsersIntoList();
        Map<String, User> userMap = userFileParser.getParsedUserMap();
        for (Map.Entry<String, User> arrUsers : userMap.entrySet()) {

            jdbcTemplate.update(insertUserSQL, new Object[]{arrUsers.getValue().getUserId(),
                    arrUsers.getValue().getUserName(),
                    arrUsers.getValue().getUserEmail(),
                    arrUsers.getValue().getUserLogin()});
            LOGGER.info("Inserting Next Rows to DB : " + arrUsers);
        }
        LOGGER.info("Start populating User table ");
    }

    public void setUserFileParser(UserFileParser userFileParser) {
        this.userFileParser = userFileParser;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setInsertUserSQL(String insertUserSQL) {
        this.insertUserSQL = insertUserSQL;
    }


}
