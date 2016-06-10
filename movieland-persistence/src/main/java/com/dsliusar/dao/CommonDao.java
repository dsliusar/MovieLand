package com.dsliusar.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public interface CommonDao {

    public default void truncateCommon(JdbcTemplate jdbcTemplate, String tableName){
            jdbcTemplate.execute("TRUNCATE " + tableName);
    }
}
