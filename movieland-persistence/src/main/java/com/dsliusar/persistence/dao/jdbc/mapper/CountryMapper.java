package com.dsliusar.persistence.dao.jdbc.mapper;

import com.dsliusar.persistence.entity.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CountryMapper implements RowMapper<Country> {
    @Override
    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
        Country country = new Country();
        country.setCountryId(resultSet.getInt("country_id"));
        country.setCountryName(resultSet.getString("country_name"));
        return country;
    }
}
