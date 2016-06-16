package com.dsliusar.dao.jdbc.builder;

import com.dsliusar.MovieFieldNamesEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by DSliusar on 15.06.2016.
 */

@Repository
public class SortingQueryBuilder {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private String sql;
    private String ratingOrder;
    private String priceOrder;

    public SortingQueryBuilder() {}

    public SortingQueryBuilder(String sql, String ratingOrder, String priceOrder) {
        this.sql = sql;
        this.ratingOrder = ratingOrder;
        this.priceOrder = priceOrder;
    }

    public final static String DESC_SORTING = "DESC";
    public final static String ORDER_CLAUSE = " ORDER BY 'a' ";
    public final static String SPACE = " ";
    public final static String COMMA = ", ";

    List<String> orderArray = new ArrayList<>(Arrays.asList(ASC_SORTING, DESC_SORTING));

    public String movieSortingQueryBuilder() {
        StringBuilder sortClause = new StringBuilder(sql);
        if (ratingOrder == null && priceOrder == null) {
            return sql;
        } else {
            sortClause.append(ORDER_CLAUSE);
        }

        if (ratingOrder != null) {
            checkIfOrderClauseExists(ratingOrder);
            if (ratingOrder.equalsIgnoreCase(ASC_SORTING)) {
                appendWithSortClause(sortClause, MovieFieldNamesEnum.RATING.toString(), ASC_SORTING);

            } else {
                appendWithSortClause(sortClause, MovieFieldNamesEnum.RATING.toString(), DESC_SORTING);
            }
        }

        if (priceOrder != null) {
            checkIfOrderClauseExists(priceOrder);
            if (priceOrder.equalsIgnoreCase(ASC_SORTING)) {
                appendWithSortClause(sortClause, MovieFieldNamesEnum.PRICE.toString(), ASC_SORTING);
            } else {
                appendWithSortClause(sortClause, MovieFieldNamesEnum.PRICE.toString(), DESC_SORTING);
            }
        }

        return sortClause.toString();
    }

    private void appendWithSortClause(StringBuilder stringBuilder, String fieldName, String sortClause) {
        stringBuilder.append(COMMA).append(fieldName.toLowerCase()).append(SPACE).append(sortClause);

    }

    private void checkIfOrderClauseExists(String orderParam) {
        if (!orderArray.contains(orderParam.toUpperCase())) {
            LOGGER.info("Please enter correct sorting clause, acceptable values {}, {}", ASC_SORTING, DESC_SORTING);
            throw new IllegalArgumentException("Please enter correct sorting clause, should be");
        }

    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getRatingOrder() {
        return ratingOrder;
    }

    public void setRatingOrder(String ratingOrder) {
        this.ratingOrder = ratingOrder;
    }

    public String getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(String priceOrder) {
        this.priceOrder = priceOrder;
    }
    public final static String ASC_SORTING = "ASC";

}
