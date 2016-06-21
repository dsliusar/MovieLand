package com.dsliusar.persistence.dao.jdbc.mapper;

import com.dsliusar.persistence.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.WeakHashMap;

@Repository
public class SingleUserRowMapper implements ResultSetExtractor<Map<Integer,User>> {
    @Override
    public Map<Integer, User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        WeakHashMap<Integer, User> mapRet = new WeakHashMap<>();
        while (rs.next()) {
            User user = new User();
            user.setUserName(rs.getString("user_name"));
            user.setUserEmail(rs.getString("user_email"));
            user.setUserPassword(rs.getString("user_password"));
            user.setUserId(rs.getInt("user_id"));
            user.setUserRole(rs.getString("user_role"));
            mapRet.put(user.getUserId(),user);
        }
        return mapRet;
    }
}
