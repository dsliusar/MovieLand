package com.dsliusar.dao.rowmapper;

import com.dsliusar.dao.jdbc.mapper.MovieMapper;
import com.dsliusar.entity.Movie;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DSliusar on 15.06.2016.
 */
public class MovieRowMapperTest {

    @Test
    public void mapperMovieTest() throws SQLException {
        ResultSet rs = Mockito.mock(ResultSet.class);

        Mockito.when(rs.getString((Mockito.any(String.class)))).thenReturn("NAME_ENG").thenReturn("NAME_RU");
        Mockito.when(rs.getInt(Mockito.any(String.class))).thenReturn(100).thenReturn(2000);
        Mockito.when(rs.getDouble(Mockito.any(String.class))).thenReturn(2.2);

        MovieMapper mapper = new MovieMapper();
        Movie movie = mapper.mapRow(rs,0);
        Assert.assertEquals(movie.getMovieNameOrigin(), "NAME_ENG");
        Assert.assertEquals(movie.getMovieNameRus(), "NAME_RU");
        Assert.assertEquals(movie.getMovieId(), 100);
        Assert.assertEquals(movie.getYear(), 2000);
        Assert.assertEquals(movie.getRating(), 2.2, 0.01);

    }
}
