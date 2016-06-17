package com.dsliusar.dao.rowmapper;

import com.dsliusar.dao.jdbc.mapper.ReviewMapper;
import com.dsliusar.entity.Review;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DSliusar on 16.06.2016.
 */
public class ReviewRowMapperTest {

    @Test
    public void mapperMovieTest() throws SQLException {
        ResultSet rs = Mockito.mock(ResultSet.class);

        Mockito.when(rs.getInt(Mockito.any(String.class))).thenReturn(100).thenReturn(200).thenReturn(300);
        Mockito.when(rs.getString((Mockito.any(String.class)))).thenReturn("GOOD MOVIE");

        ReviewMapper mapper = new ReviewMapper();
        Review review = mapper.mapRow(rs, 0);
        Assert.assertEquals(review.getReviewId(),100);
        Assert.assertEquals(review.getMovieId(),200);
        Assert.assertEquals(review.getUserId(),300);
        Assert.assertEquals(review.getReviewText(),"GOOD MOVIE");
    }
}