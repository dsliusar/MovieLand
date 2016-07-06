package com.dsliusar.persistence.dao.jdbc.builder;

import com.dsliusar.tools.enums.MovieFieldNames;
import com.dsliusar.tools.entities.http.MovieSearchRequest;
import org.springframework.stereotype.Repository;

@Repository
public class SearchQueryBuilder {
    private static final String DEFAULT_WHERE_CLAUSE = " WHERE 1 = 1 ";
    private static final String AND_CLAUSE = " AND ";

    public String movieSearchQueryBuilder(String sql, MovieSearchRequest movieSearchRequest)
            throws IllegalArgumentException {
        StringBuilder returnSql = new StringBuilder(sql);
        if (movieSearchRequest.getMovieNameRus() == null && movieSearchRequest.getMovieNameOrigin() == null
                && movieSearchRequest.getYear() == null && movieSearchRequest.getGenreId() == null
                && movieSearchRequest.getCountryId() == null) {
            throw new IllegalArgumentException("No arguments given");
        }
        if (movieSearchRequest.getGenreId() != null) {
            returnSql.append(" JOIN genre_movies q1 on q1.movie_id = q.movie_id AND q1.genre_id = ")
                    .append(movieSearchRequest.getGenreId());
        }
        if (movieSearchRequest.getCountryId() != null) {
            returnSql.append(" JOIN countries_movie_mapper q2 on q2.movie_id = q.movie_id AND q2.country_id = ")
                    .append(movieSearchRequest.getCountryId());
        }
        returnSql.append(DEFAULT_WHERE_CLAUSE);
        if (movieSearchRequest.getMovieNameRus() != null) {
            appendWhereClause(returnSql, MovieFieldNames.MOVIE_NAME_RUS.toString(),
                    movieSearchRequest.getMovieNameRus());
        }
        if (movieSearchRequest.getMovieNameOrigin() != null) {
            appendWhereClause(returnSql, MovieFieldNames.MOVIE_NAME_ENG.toString(),
                    movieSearchRequest.getMovieNameOrigin());
        }
        if (movieSearchRequest.getYear() != null) {
            appendWhereClause(returnSql, MovieFieldNames.YEAR.toString(),
                    movieSearchRequest.getYear());
        }
        return returnSql.toString();
    }

    private void appendWhereClause(StringBuilder stringBuilder, String fieldName, String criteria) {
        stringBuilder.append(AND_CLAUSE);
        stringBuilder.append(fieldName);
        stringBuilder.append(" = ");
        stringBuilder.append("'");
        stringBuilder.append(criteria);
        stringBuilder.append("'");
    }


    private void appendWhereClause(StringBuilder stringBuilder, String fieldName, Integer criteria) {
        stringBuilder.append(AND_CLAUSE);
        stringBuilder.append(fieldName.toLowerCase());
        stringBuilder.append(" = ");
        stringBuilder.append(criteria);
    }

}
