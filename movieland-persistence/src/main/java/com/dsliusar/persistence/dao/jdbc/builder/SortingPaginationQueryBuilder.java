package com.dsliusar.persistence.dao.jdbc.builder;

import com.dsliusar.tools.enums.MovieFieldNames;
import com.dsliusar.tools.enums.SortType;
import com.dsliusar.tools.http.entities.AllMoviesRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SortingPaginationQueryBuilder {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    public final static String ORDER_CLAUSE = " ORDER BY 'a'";
    public final static String SPACE = " ";
    public final static String COMMA = ", ";
    public final static int LIMIT_TOTAL = 5;
    public final static String LIMIT_CLAUSE = " LIMIT ";
    public final static String OFFSET_CLAUSE = " OFFSET ";


    public String movieSortingPaginationQueryBuilder(String sql, AllMoviesRequestDto movieSortRequest) {
        LOGGER.info("Building Sorting query with next parameters {}", movieSortRequest);
        StringBuilder sortClause = new StringBuilder(sql);
        if (movieSortRequest.getRatingOrder() == null
                && movieSortRequest.getPriceOrder() == null
                && movieSortRequest.getPageNumber() == null) {
            return sql;
        } else {
            sortClause.append(ORDER_CLAUSE);
        }

        if (movieSortRequest.getRatingOrder() != null) {
            appendWithSortClause(sortClause, MovieFieldNames.RATING.toString(),
                    SortType.validateSortType(movieSortRequest.getRatingOrder()).toString().toUpperCase());
        }

        if (movieSortRequest.getPriceOrder() != null) {
            appendWithSortClause(sortClause, MovieFieldNames.PRICE.toString(),
                    SortType.validateSortType(movieSortRequest.getPriceOrder()).toString().toUpperCase());
        }

        if(movieSortRequest.getPageNumber() != null){
            sortClause.append(LIMIT_CLAUSE).
                       append(LIMIT_TOTAL).
                       append(OFFSET_CLAUSE).
                       append(LIMIT_TOTAL * movieSortRequest.getPageNumber());
        }

        LOGGER.info("Next query were built {}", sortClause.toString());
        return sortClause.toString();
    }

    private void appendWithSortClause(StringBuilder stringBuilder, String fieldName, String sortClause) {
        stringBuilder.append(COMMA).append(fieldName.toLowerCase()).append(SPACE).append(sortClause);

    }

}
