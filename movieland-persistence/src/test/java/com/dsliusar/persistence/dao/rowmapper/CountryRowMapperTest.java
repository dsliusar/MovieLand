package com.dsliusar.persistence.dao.rowmapper;

import com.dsliusar.persistence.dao.jdbc.mapper.CountryMapper;
import com.dsliusar.persistence.entity.Country;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DSliusar on 16.06.2016.
 */
public class CountryRowMapperTest {

    @Test
    public void mapperCountryTest() throws SQLException {
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(rs.getInt(Mockito.any(String.class))).thenReturn(100);
        Mockito.when(rs.getString((Mockito.any(String.class)))).thenReturn("USA");

        CountryMapper mapper = new CountryMapper();
        Country country = mapper.mapRow(rs,0);
        Assert.assertEquals(country.getCountryId(), 100);
        Assert.assertEquals(country.getCountryName(), "USA");


    }
}
