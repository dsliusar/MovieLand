package com.dsliusar.persistence.dao.jdbc.builder;

import com.dsliusar.enums.MovieFieldNamesEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

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

    public final static String ORDER_CLAUSE = " ORDER BY 'a' ";
    public final static String SPACE = " ";
    public final static String COMMA = ", ";

    public String movieSortingQueryBuilder() {
        StringBuilder sortClause = new StringBuilder(sql);
        if (ratingOrder == null && priceOrder == null) {
            return sql;
        } else {
            sortClause.append(ORDER_CLAUSE);
        }

        if (ratingOrder != null) {
            appendWithSortClause(sortClause, MovieFieldNamesEnum.RATING.toString(), ratingOrder.toUpperCase());
        }

        if (priceOrder != null) {
            appendWithSortClause(sortClause, MovieFieldNamesEnum.PRICE.toString(), priceOrder.toUpperCase());
        }

        return sortClause.toString();
    }

    private void appendWithSortClause(StringBuilder stringBuilder, String fieldName, String sortClause) {
        stringBuilder.append(COMMA).append(fieldName.toLowerCase()).append(SPACE).append(sortClause);

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

}
