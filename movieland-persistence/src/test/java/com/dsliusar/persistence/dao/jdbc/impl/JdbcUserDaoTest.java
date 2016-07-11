package com.dsliusar.persistence.dao.jdbc.impl;

import com.dsliusar.persistence.dao.UserDao;
import com.dsliusar.persistence.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-persistence-config.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class JdbcUserDaoTest {

    @Autowired
    private UserDao jdbcUserDao;

    private Map<String, User> userMap = new HashMap<>();

    private static int userId = 100;

    @Before
    public void fillUserMap(){
        User user = new User();
        user.setUserId(userId);
        user.setUserEmail("test.user@gmail.com");
        user.setUserRole("ADMIN");
        user.setUserName("test");
        user.setUserPassword("123");
        userMap.put("test",user);
    }

    @Test
    public void getAllUsersTest() {
        List<User> userList = jdbcUserDao.getAllUsers();
        Assert.assertNotNull(userList);
    }

    @Test
    public void getAllUsersMap(){
        Map<Integer, User> allUsersMap = jdbcUserDao.getAllUsersMap();
        for (Map.Entry<Integer, User> integerUserEntry : allUsersMap.entrySet()) {
            System.out.println(integerUserEntry.getValue());
        }
    }

    @Test
    public void insertUserMap(){
        jdbcUserDao.insert(userMap);
        Assert.assertNotNull(getUserByIdCommon(userMap.get("test").getUserId()));
    }

    @Test
    public void getUserByIdTest(){
        Assert.assertNotNull(1);
    }

    private User getUserByIdCommon(int userId){
        return jdbcUserDao.getUserById(userId);
    }




}
