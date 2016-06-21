package com.dsliusar.persistence.dao.jdbc.builder;

import com.dsliusar.http.entities.MovieSortRequest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by DSliusar on 18.06.2016.
 */
public class SortingQueryBuilderTest {
    private static final String commonSql = "select * from genre";
    private MovieSortRequest movieSortRequest = new MovieSortRequest();

    @Test
    public void sortingAscDescTest(){

        //Testing ASC and ASC in both clauses
        String expectedSql = commonSql + " order by 'a' , rating asc, price asc";
        SortingQueryBuilder sortAsc = new SortingQueryBuilder();
        movieSortRequest.setRatingOrder("asc");
        movieSortRequest.setPriceOrder("asc");
        String actualSql = sortAsc.movieSortingQueryBuilder(commonSql, movieSortRequest);

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());

        //Testing DESC and DESC in both clauses
        expectedSql = commonSql + " order by 'a' , rating desc, price desc";
        movieSortRequest.setRatingOrder("desc");
        movieSortRequest.setPriceOrder("desc");
        SortingQueryBuilder sortDesc = new SortingQueryBuilder();
        actualSql = sortDesc.movieSortingQueryBuilder(commonSql, movieSortRequest);

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());

        //Testing ASC and DESC in both clauses
        expectedSql = commonSql + " order by 'a' , rating asc, price desc";
        movieSortRequest.setRatingOrder("asc");
        movieSortRequest.setPriceOrder("desc");
        SortingQueryBuilder sortAscDesc = new SortingQueryBuilder();
        actualSql = sortAscDesc.movieSortingQueryBuilder(commonSql, movieSortRequest);

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());

        //Testing DESC and ASC in both clauses
        expectedSql = commonSql + " order by 'a' , rating desc, price asc";
        movieSortRequest.setRatingOrder("desc");
        movieSortRequest.setPriceOrder("asc");
        SortingQueryBuilder sortDescAsc = new SortingQueryBuilder();
        actualSql = sortDescAsc.movieSortingQueryBuilder(commonSql,movieSortRequest);

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());
    }

    @Test
    public void sortingWithOneNullValue(){
        //Testing ASC and NULL in both clauses
        String expectedSql = commonSql + " order by 'a' , rating asc";
        movieSortRequest.setRatingOrder("asc");
        movieSortRequest.setPriceOrder(null);
        SortingQueryBuilder sortAscNull = new SortingQueryBuilder();
        String actualSql = sortAscNull.movieSortingQueryBuilder(commonSql, movieSortRequest);

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());

        //Testing NULL and ASC in both clauses
        expectedSql = commonSql + " order by 'a' , price asc";
        movieSortRequest.setRatingOrder(null);
        movieSortRequest.setPriceOrder("asc");
        SortingQueryBuilder sortNullAsc = new SortingQueryBuilder();
        actualSql = sortNullAsc.movieSortingQueryBuilder(commonSql, movieSortRequest);

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());
    }

    @Test
    public void sortingAllNullValues(){
        //Testing Null and NULL in both clauses
        String expectedSql = commonSql;
        movieSortRequest.setRatingOrder(null);
        movieSortRequest.setPriceOrder(null);
        SortingQueryBuilder sortNullNull = new SortingQueryBuilder();
        String actualSql = sortNullNull.movieSortingQueryBuilder(commonSql, movieSortRequest);

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());

    }
}
