package com.dsliusar.persistence.dao.jdbc.builder;

import com.dsliusar.tools.entities.http.AllMoviesRequestDto;
import org.junit.Assert;
import org.junit.Test;

public class SortingPaginationQueryBuilderTest {
    private static final String commonSql = "select * from movie";
    private AllMoviesRequestDto movieSortRequest = new AllMoviesRequestDto();

    @Test
    public void paginationTest() {

        //Testing ASC and ASC in both clauses
        String expectedSql = commonSql + " order by 'a', rating asc, price asc limit 5 offset 5";
        SortingPaginationQueryBuilder sortAsc = new SortingPaginationQueryBuilder();
        movieSortRequest.setRatingOrder("asc");
        movieSortRequest.setPriceOrder("asc");
        movieSortRequest.setPageNumber(1);
        String actualSql = sortAsc.movieSortingPaginationQueryBuilder(commonSql, movieSortRequest);

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());

        //Testing DESC and DESC in both clauses
        expectedSql = commonSql + " order by 'a', rating desc, price desc limit 5 offset 10";
        movieSortRequest.setRatingOrder("desc");
        movieSortRequest.setPriceOrder("desc");
        movieSortRequest.setPageNumber(2);
        SortingPaginationQueryBuilder sortDesc = new SortingPaginationQueryBuilder();
        actualSql = sortDesc.movieSortingPaginationQueryBuilder(commonSql, movieSortRequest);

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());

        //Testing DESC and DESC in both clauses
        expectedSql = commonSql + " order by 'a' limit 5 offset 10";
        movieSortRequest.setRatingOrder(null);
        movieSortRequest.setPriceOrder(null);
        movieSortRequest.setPageNumber(2);
        SortingPaginationQueryBuilder paginationOnly = new SortingPaginationQueryBuilder();
        actualSql = paginationOnly.movieSortingPaginationQueryBuilder(commonSql, movieSortRequest);

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());

    }

    @Test
    public void sortingAscDescTest(){

        //Testing ASC and ASC in both clauses
        String expectedSql = commonSql + " order by 'a', rating asc, price asc";
        SortingPaginationQueryBuilder sortAsc = new SortingPaginationQueryBuilder();
        movieSortRequest.setRatingOrder("asc");
        movieSortRequest.setPriceOrder("asc");
        String actualSql = sortAsc.movieSortingPaginationQueryBuilder(commonSql, movieSortRequest);

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());

        //Testing DESC and DESC in both clauses
        expectedSql = commonSql + " order by 'a', rating desc, price desc";
        movieSortRequest.setRatingOrder("desc");
        movieSortRequest.setPriceOrder("desc");
        SortingPaginationQueryBuilder sortDesc = new SortingPaginationQueryBuilder();
        actualSql = sortDesc.movieSortingPaginationQueryBuilder(commonSql, movieSortRequest);

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());

        //Testing ASC and DESC in both clauses
        expectedSql = commonSql + " order by 'a', rating asc, price desc";
        movieSortRequest.setRatingOrder("asc");
        movieSortRequest.setPriceOrder("desc");
        SortingPaginationQueryBuilder sortAscDesc = new SortingPaginationQueryBuilder();
        actualSql = sortAscDesc.movieSortingPaginationQueryBuilder(commonSql, movieSortRequest);

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());

        //Testing DESC and ASC in both clauses
        expectedSql = commonSql + " order by 'a', rating desc, price asc";
        movieSortRequest.setRatingOrder("desc");
        movieSortRequest.setPriceOrder("asc");
        SortingPaginationQueryBuilder sortDescAsc = new SortingPaginationQueryBuilder();
        actualSql = sortDescAsc.movieSortingPaginationQueryBuilder(commonSql, movieSortRequest);

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());
    }

    @Test
    public void sortingWithOneNullValue(){
        //Testing ASC and NULL in both clauses
        String expectedSql = commonSql + " order by 'a', rating asc";
        movieSortRequest.setRatingOrder("asc");
        movieSortRequest.setPriceOrder(null);
        SortingPaginationQueryBuilder sortAscNull = new SortingPaginationQueryBuilder();
        String actualSql = sortAscNull.movieSortingPaginationQueryBuilder(commonSql, movieSortRequest);

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());

        //Testing NULL and ASC in both clauses
        expectedSql = commonSql + " order by 'a', price asc";
        movieSortRequest.setRatingOrder(null);
        movieSortRequest.setPriceOrder("asc");
        SortingPaginationQueryBuilder sortNullAsc = new SortingPaginationQueryBuilder();
        actualSql = sortNullAsc.movieSortingPaginationQueryBuilder(commonSql, movieSortRequest);

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());
    }

    @Test
    public void sortingAllNullValues(){
        //Testing Null and NULL in both clauses
        String expectedSql = commonSql;
        movieSortRequest.setRatingOrder(null);
        movieSortRequest.setPriceOrder(null);
        SortingPaginationQueryBuilder sortNullNull = new SortingPaginationQueryBuilder();
        String actualSql = sortNullNull.movieSortingPaginationQueryBuilder(commonSql, movieSortRequest);

        Assert.assertEquals(expectedSql.toLowerCase(), actualSql.toLowerCase());

    }
}
