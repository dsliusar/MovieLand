package com.dsliusar.tools.http.entities;

/**
 * Created by DSliusar on 21.06.2016.
 */
public class MovieSortRequest {

    private String ratingOrder;
    private String priceOrder;

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

    @Override
    public String toString() {
        return "MovieSortRequest{" +
                "ratingOrder='" + ratingOrder + '\'' +
                ", priceOrder='" + priceOrder + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieSortRequest that = (MovieSortRequest) o;

        if (ratingOrder != null ? !ratingOrder.equals(that.ratingOrder) : that.ratingOrder != null) return false;
        return !(priceOrder != null ? !priceOrder.equals(that.priceOrder) : that.priceOrder != null);

    }

    @Override
    public int hashCode() {
        int result = ratingOrder != null ? ratingOrder.hashCode() : 0;
        result = 31 * result + (priceOrder != null ? priceOrder.hashCode() : 0);
        return result;
    }
}
