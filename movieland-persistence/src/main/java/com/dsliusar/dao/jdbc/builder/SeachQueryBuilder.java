package com.dsliusar.dao.jdbc.builder;

import com.dsliusar.GenreFieldNamesEnum;
import com.dsliusar.MovieFieldNamesEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Red1 on 6/16/2016.
 */
public class SeachQueryBuilder {
    private String sql;
    private String movieNameRus;
    private String movieNameOrigin;
    private Integer year;
    private Integer genreId;
    private Integer countryId;

    public SeachQueryBuilder() {}
    public SeachQueryBuilder(String sql, String movieNameRus, String movieNameOrigin, Integer year, Integer genreId, Integer countryId) {
        this.sql = sql;
        this.movieNameRus = movieNameRus;
        this.movieNameOrigin = movieNameOrigin;
        this.year = year;
        this.genreId = genreId;
        this.countryId = countryId;
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

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private static final String DEFAULT_WHERE_CLAUSE = " WHERE 1 = 1 ";
    private static final String AND_CLAUSE = " AND ";

    public String movieSearchQueryBuilder(){
        StringBuilder returnSql = new StringBuilder(sql);
        if (movieNameRus == null && movieNameOrigin == null && year == null && genreId == null && countryId == null){
            return sql;
        }
        if (genreId != null) {
            returnSql.append(" JOIN genre q1 on q1.movie_id = q.movie_id AND q1.genre_id = ").append(genreId);
        }
        if (countryId != null){
            returnSql.append(" JOIN country q2 on q2.movie_id = q.movie_id AND q2.country_id = ").append(countryId);
        }
        returnSql.append(DEFAULT_WHERE_CLAUSE);
        if (movieNameRus != null ) {
                appendWhereClause(returnSql, MovieFieldNamesEnum.MOVIE_NAME_RUS.toString(),movieNameRus);
          }
        if (movieNameOrigin != null) {
            appendWhereClause(returnSql, MovieFieldNamesEnum.MOVIE_NAME_ORIGIN.toString(),movieNameOrigin);
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

    public String getMovieNameRus() {
        return movieNameRus;
    }

    public void setMovieNameRus(String movieNameRus) {
        this.movieNameRus = movieNameRus;
    }

    public String getMovieNameOrigin() {
        return movieNameOrigin;
    }

    public void setMovieNameOrigin(String movieNameOrigin) {
        this.movieNameOrigin = movieNameOrigin;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
