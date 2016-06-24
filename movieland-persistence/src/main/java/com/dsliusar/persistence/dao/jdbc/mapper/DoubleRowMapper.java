package com.dsliusar.persistence.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DoubleRowMapper implements RowMapper<Double> {
    @Override
    public Double mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Double(rs.getDouble(1));
    }
}
