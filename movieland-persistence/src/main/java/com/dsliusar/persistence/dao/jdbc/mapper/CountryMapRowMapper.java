package com.dsliusar.persistence.dao.jdbc.mapper;

import com.dsliusar.persistence.entity.Country;
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
public class CountryMapRowMapper implements ResultSetExtractor<Map<Integer, List<Country>>> {
    @Override
    public Map<Integer, List<Country>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, List<Country>> mapRet = new ConcurrentHashMap<>();
        List<Country> countryList;
        while (rs.next()) {
            Country country = new Country();
            Integer movieId = rs.getInt("movie_id");
            country.setCountryName(rs.getString("country_name"));
            country.setCountryId(rs.getInt("country_id"));
            if (mapRet.containsKey(movieId)) {
                mapRet.get(movieId).add(country);
            } else {
                countryList = new ArrayList<>();
                countryList.add(country);
                mapRet.put(movieId, countryList);
            }
        }
        return mapRet;
    }
}
