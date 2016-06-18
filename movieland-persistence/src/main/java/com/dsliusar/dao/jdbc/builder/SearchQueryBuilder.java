package com.dsliusar.dao.jdbc.builder;

import com.dsliusar.enums.MovieFieldNamesEnum;
import com.dsliusar.httpEntities.MovieSearchRequestDto;

public class SearchQueryBuilder {
    private static final String DEFAULT_WHERE_CLAUSE = " WHERE 1 = 1 ";
    private static final String AND_CLAUSE = " AND ";
    private String sql;
    private String movieNameRus;
    private String movieNameOrigin;
    private Integer year;
    private Integer genreId;
    private Integer countryId;
    public SearchQueryBuilder() {}

    public SearchQueryBuilder(String sql, MovieSearchRequestDto movieSearchRequest){
        this.sql = sql;
        this.movieNameRus = movieSearchRequest.getMovieNameRus();
        this.movieNameOrigin = movieSearchRequest.getMovieNameOrigin();
        this.year = movieSearchRequest.getYear();
        this.genreId = movieSearchRequest.getGenreId();
        this.countryId = movieSearchRequest.getCountryId();
    }
    public String movieSearchQueryBuilder() throws IllegalArgumentException{
        StringBuilder returnSql = new StringBuilder(sql);
        if (movieNameRus == null && movieNameOrigin == null && year == null && genreId == null && countryId == null){
            throw new IllegalArgumentException("No arguments given");
        }
        if (genreId != null) {
            returnSql.append(" JOIN genre_movies q1 on q1.movie_id = q.movie_id AND q1.genre_id = ").append(genreId);
        }
        if (countryId != null){
            returnSql.append(" JOIN countries_movie_mapper q2 on q2.movie_id = q.movie_id AND q2.country_id = ").append(countryId);
        }
        returnSql.append(DEFAULT_WHERE_CLAUSE);
        if (movieNameRus != null ) {
                appendWhereClause(returnSql, MovieFieldNamesEnum.MOVIE_NAME_RUS.toString(),movieNameRus);
          }
        if (movieNameOrigin != null) {
            appendWhereClause(returnSql, MovieFieldNamesEnum.MOVIE_NAME_ENG.toString(),movieNameOrigin);
        }
        if (year != null) {
            appendWhereClause(returnSql, MovieFieldNamesEnum.YEAR.toString(),year);
        }
        return returnSql.toString();
    }

    private void appendWhereClause(StringBuilder stringBuilder,String fieldName ,String criteria){
        stringBuilder.append(AND_CLAUSE);
        stringBuilder.append(fieldName);
        stringBuilder.append(" = ");
        stringBuilder.append("'");
        stringBuilder.append(criteria);
        stringBuilder.append("'");
    }


    private void appendWhereClause(StringBuilder stringBuilder,String fieldName ,Integer criteria){
        stringBuilder.append(AND_CLAUSE);
        stringBuilder.append(fieldName.toLowerCase());
        stringBuilder.append(" = ");
        stringBuilder.append(criteria);
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "SeachQueryBuilder{" +
                "sql='" + sql + '\'' +
                ", movieNameRus='" + movieNameRus + '\'' +
                ", movieNameOrigin='" + movieNameOrigin + '\'' +
                ", year=" + year +
                ", genreId=" + genreId +
                ", countryId=" + countryId +
                '}';
    }
}
