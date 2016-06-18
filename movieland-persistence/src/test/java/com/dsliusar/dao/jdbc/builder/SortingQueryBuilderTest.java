package com.dsliusar.dao.jdbc.builder;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by DSliusar on 18.06.2016.
 */
public class SortingQueryBuilderTest {
    private static final String commonSql = "select * from genre";

    @Test
    public void sortingAscDescTest(){

        //Testing ASC and ASC in both clauses
        String expectedSql = commonSql + " order by 'a' , rating asc, price asc";
        SortingQueryBuilder sortAsc = new SortingQueryBuilder(commonSql, "asc", "asc");
        String actualSql = sortAsc.movieSortingQueryBuilder();

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());

        //Testing DESC and DESC in both clauses
        expectedSql = commonSql + " order by 'a' , rating desc, price desc";
        SortingQueryBuilder sortDesc = new SortingQueryBuilder(commonSql, "desc", "desc");
        actualSql = sortDesc.movieSortingQueryBuilder();

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());

        //Testing ASC and DESC in both clauses
        expectedSql = commonSql + " order by 'a' , rating asc, price desc";
        SortingQueryBuilder sortAscDesc = new SortingQueryBuilder(commonSql, "asc", "desc");
        actualSql = sortAscDesc.movieSortingQueryBuilder();

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());

        //Testing DESC and ASC in both clauses
        expectedSql = commonSql + " order by 'a' , rating desc, price asc";
        SortingQueryBuilder sortDescAsc = new SortingQueryBuilder(commonSql, "desc", "asc");
        actualSql = sortDescAsc.movieSortingQueryBuilder();

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());
    }

    @Test
    public void sortingWithOneNullValue(){
        //Testing ASC and NULL in both clauses
        String expectedSql = commonSql + " order by 'a' , rating asc";
        SortingQueryBuilder sortAscNull = new SortingQueryBuilder(commonSql, "asc",null);
        String actualSql = sortAscNull.movieSortingQueryBuilder();

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());

        //Testing NULL and ASC in both clauses
        expectedSql = commonSql + " order by 'a' , price asc";
        SortingQueryBuilder sortNullAsc = new SortingQueryBuilder(commonSql, null, "asc");
        actualSql = sortNullAsc.movieSortingQueryBuilder();

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());
    }

    @Test
    public void sortingAllNullValues(){
        //Testing Null and NULL in both clauses
        String expectedSql = commonSql;
        SortingQueryBuilder sortNullNull = new SortingQueryBuilder(commonSql, null,null);
        String actualSql = sortNullNull.movieSortingQueryBuilder();

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());

    }
}
