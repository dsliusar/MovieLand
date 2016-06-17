package com.dsliusar.dao.rowmapper;

import com.dsliusar.dao.jdbc.mapper.GenresMapper;
import com.dsliusar.entity.Genre;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DSliusar on 16.06.2016.
 */
public class GenreRowMapperTest {
    @Test
    public void mapperGenreTest() throws SQLException {
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(rs.getInt(Mockito.any(String.class))).thenReturn(100);
        Mockito.when(rs.getString((Mockito.any(String.class)))).thenReturn("комедия");

        GenresMapper mapper = new GenresMapper();
        Genre genre = mapper.mapRow(rs,0);
        Assert.assertEquals(genre.getGenreId(), 100);
        Assert.assertEquals(genre.getName(), "комедия");


    }
}
