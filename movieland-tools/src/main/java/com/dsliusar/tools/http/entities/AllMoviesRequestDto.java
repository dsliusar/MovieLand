package com.dsliusar.tools.http.entities;

/**
 * Created by DSliusar on 21.06.2016.
 */
public class AllMoviesRequestDto {

    private String ratingOrder;
    private String priceOrder;
    private Integer pageNumber;
    private String currency;

    public AllMoviesRequestDto() {}

    public AllMoviesRequestDto(String ratingOrder, String priceOrder, Integer pageNumber, String currency) {
        this.ratingOrder = ratingOrder;
        this.priceOrder = priceOrder;
        this.pageNumber = pageNumber;
        this.currency = currency;
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

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "AllMoviesRequestDto{" +
                "ratingOrder='" + ratingOrder + '\'' +
                ", priceOrder='" + priceOrder + '\'' +
                ", pageNumber=" + pageNumber +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllMoviesRequestDto that = (AllMoviesRequestDto) o;

        if (ratingOrder != null ? !ratingOrder.equals(that.ratingOrder) : that.ratingOrder != null) return false;
        if (priceOrder != null ? !priceOrder.equals(that.priceOrder) : that.priceOrder != null) return false;
        if (pageNumber != null ? !pageNumber.equals(that.pageNumber) : that.pageNumber != null) return false;
        return !(currency != null ? !currency.equals(that.currency) : that.currency != null);

    }

    @Override
    public int hashCode() {
        int result = ratingOrder != null ? ratingOrder.hashCode() : 0;
        result = 31 * result + (priceOrder != null ? priceOrder.hashCode() : 0);
        result = 31 * result + (pageNumber != null ? pageNumber.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }
}
