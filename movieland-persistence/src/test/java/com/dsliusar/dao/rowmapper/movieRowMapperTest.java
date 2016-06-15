package com.dsliusar.dao.rowmapper;

import com.dsliusar.MovieFieldNamesEnum;
import com.dsliusar.dao.jdbc.mapper.AllMovieMapper;
import com.dsliusar.entity.Movie;
import org.junit.Assert;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DSliusar on 15.06.2016.
 */
public class movieRowMapperTest {

    public void mapperMovieTest() throws SQLException {
        ResultSet rs = Mockito.mock(ResultSet.class);
     //   Mockito.when(rs.getInt(MovieFieldNamesEnum.MOVIE_ID.toString())).thenReturn(100);
        Mockito.when(rs.getString(MovieFieldNamesEnum.MOVIE_NAME_RUS.toString())).thenReturn("NAME_RU");
        Mockito.when(rs.getString(MovieFieldNamesEnum.MOVIE_NAME_ENG.toString())).thenReturn("NAME_ENG");
        Mockito.when(rs.getString(MovieFieldNamesEnum.DESCRIPTION.toString())).thenReturn("DESCRIPTION");
     //   Mockito.when(rs.getDouble(MovieFieldNamesEnum.PRICE.toString())).thenReturn(1.0);
        Mockito.when(rs.getDouble(MovieFieldNamesEnum.RATING.toString())).thenReturn(2.2);
        Mockito.when(rs.getInt(MovieFieldNamesEnum.YEAR.toString())).thenReturn(2000);


        AllMovieMapper mapper = new AllMovieMapper();
        Movie movie = mapper.mapRow(rs,0);
      //  Assert.assertEquals(movie.getMovieId(), 100);
        Assert.assertEquals(movie.getMovieNameRus(), "NAME_RU");
        Assert.assertEquals(movie.getMovieNameOrigin(), "NAME_ENG");
        Assert.assertEquals(movie.getDescription(), "DESCRIPTION");
    //    Assert.assertEquals(movie.getPrice(), 1.0, 0.01);
        Assert.assertEquals(movie.getRating(), 2.2, 0.01);
        Assert.assertEquals(movie.getYear(), 2000);
    }
}
