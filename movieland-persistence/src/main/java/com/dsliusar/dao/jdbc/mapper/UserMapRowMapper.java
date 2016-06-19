package com.dsliusar.dao.jdbc.mapper;

import com.dsliusar.entity.Review;
import com.dsliusar.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserMapRowMapper implements ResultSetExtractor<Map<Integer,List<User>>> {

    @Override
    public Map<Integer,List<User>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        ConcurrentHashMap<Integer, List<User>> mapRet = new ConcurrentHashMap<>();
        while (rs.next()) {
            User user = new User();
            List<User> userList = new ArrayList<>();
            user.setUserName(rs.getString("user_name"));
            user.setUserEmail(rs.getString("user_email"));
            user.setUserPassword(rs.getString("user_password"));
            userList.add(user);
            mapRet.put(rs.getInt("user_id"),userList);
        }
        return mapRet;
    }
}
