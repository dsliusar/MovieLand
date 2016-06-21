package com.dsliusar.persistence.dao.jdbc.builder;

import com.dsliusar.http.entities.MovieSearchRequest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by DSliusar on 18.06.2016.
 */
public class SearchQueryBuilderTest {

    private static final String commonSql = "select * from movie";

    @Test
    public void SearchAllMovies(){
        MovieSearchRequest movieSearchRequest = new MovieSearchRequest();
        movieSearchRequest.setMovieNameOrigin("Escape");
        movieSearchRequest.setMovieNameRus("Побег");
        movieSearchRequest.setGenreId(1);
        movieSearchRequest.setCountryId(2);
        movieSearchRequest.setYear(2009);

        String expectedSql = commonSql + " JOIN genre_movies q1 on q1.movie_id = q.movie_id AND q1.genre_id = 1 JOIN countries_movie_mapper q2 on q2.movie_id = q.movie_id AND q2.country_id = 2 WHERE 1 = 1  AND MOVIE_NAME_RUS = 'Побег' AND MOVIE_NAME_ENG = 'Escape' AND year = 2009";
        SearchQueryBuilder searchAll = new SearchQueryBuilder();
        String actualSql = searchAll.movieSearchQueryBuilder(commonSql,movieSearchRequest);

        Assert.assertEquals(expectedSql.toLowerCase(),actualSql.toLowerCase());
    }

    @Test
    public void SearchWithGenresOnly(){
        MovieSearchRequest movieSearchRequest = new MovieSearchRequest();
        movieSearchRequest.setMovieNameOrigin("Escape");
        movieSearchRequest.setMovieNameRus("Побег");
        movieSearchRequest.setGenreId(1);
        movieSearchRequest.setYear(2009);

        String expectedSql = commonSql + " JOIN genre_movies q1 on q1.movie_id = q.movie_id AND q1.genre_id = 1 WHERE 1 = 1  AND MOVIE_NAME_RUS = 'Побег' AND MOVIE_NAME_ENG = 'Escape' AND year = 2009";
        SearchQueryBuilder searchAll = new SearchQueryBuilder();
        String actualSql = searchAll.movieSearchQueryBuilder(commonSql,movieSearchRequest);

        Assert.assertEquals(expectedSql.toLowerCase(),actualSql.toLowerCase());
    }

    @Test
    public void SearchWithCountriesOnly(){
        MovieSearchRequest movieSearchRequest = new MovieSearchRequest();
        movieSearchRequest.setMovieNameOrigin("Escape");
        movieSearchRequest.setMovieNameRus("Побег");
        movieSearchRequest.setCountryId(2);
        movieSearchRequest.setYear(2009);

        String expectedSql = commonSql + " JOIN countries_movie_mapper q2 on q2.movie_id = q.movie_id AND q2.country_id = 2 WHERE 1 = 1  AND MOVIE_NAME_RUS = 'Побег' AND MOVIE_NAME_ENG = 'Escape' AND year = 2009";
        SearchQueryBuilder searchAll = new SearchQueryBuilder();
        String actualSql = searchAll.movieSearchQueryBuilder(commonSql,movieSearchRequest);

        Assert.assertEquals(expectedSql.toLowerCase(),actualSql.toLowerCase());
    }

    @Test
    public void SearchWithAllNulls(){
        MovieSearchRequest movieSearchRequest = new MovieSearchRequest();
        String exception = null;
        SearchQueryBuilder searchAll = new SearchQueryBuilder();
        try {
            searchAll.movieSearchQueryBuilder(commonSql,movieSearchRequest);
        }catch (IllegalArgumentException e){
            exception = e.getMessage();
        }

        Assert.assertEquals("No arguments given", exception);
    }

}
