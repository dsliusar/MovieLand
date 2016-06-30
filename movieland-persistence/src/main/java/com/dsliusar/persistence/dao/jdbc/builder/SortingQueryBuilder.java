package com.dsliusar.persistence.dao.jdbc.builder;

import com.dsliusar.tools.enums.MovieFieldNames;
import com.dsliusar.tools.enums.SortType;
import com.dsliusar.tools.http.entities.MovieSortRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SortingQueryBuilder {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    public final static String ORDER_CLAUSE = " ORDER BY 'a' ";
    public final static String SPACE = " ";
    public final static String COMMA = ", ";

    public String movieSortingQueryBuilder(String sql, MovieSortRequest movieSortRequest) {
        LOGGER.info("Building Sorting query with next parameters");
        StringBuilder sortClause = new StringBuilder(sql);
        if (movieSortRequest.getRatingOrder() == null && movieSortRequest.getPriceOrder() == null) {
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

        return sortClause.toString();
    }

    private void appendWithSortClause(StringBuilder stringBuilder, String fieldName, String sortClause) {
        stringBuilder.append(COMMA).append(fieldName.toLowerCase()).append(SPACE).append(sortClause);

    }

}
