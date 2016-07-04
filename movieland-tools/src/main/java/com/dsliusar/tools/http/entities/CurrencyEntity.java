package com.dsliusar.tools.http.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyEntity {
    @JsonProperty("ccy")
    private String exchange_currency;

    @JsonProperty("base_ccy")
    private String base_currency;

    @JsonProperty("buy")
    private Double buy;

    @JsonProperty("sale")
    private Double sale;

    public String getExchange_currency() {
        return exchange_currency;
    }

    public void setExchange_currency(String exchange_currency) {
        this.exchange_currency = exchange_currency;
    }

    public String getBase_currency() {
        return base_currency;
    }

    public void setBase_currency(String base_currency) {
        this.base_currency = base_currency;
    }

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "CurrencyEntity{" +
                "exchange_currency='" + exchange_currency + '\'' +
                ", base_currency='" + base_currency + '\'' +
                ", buy=" + buy +
                ", sale=" + sale +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyEntity that = (CurrencyEntity) o;

        if (exchange_currency != null ? !exchange_currency.equals(that.exchange_currency) : that.exchange_currency != null)
            return false;
        if (base_currency != null ? !base_currency.equals(that.base_currency) : that.base_currency != null)
            return false;
        if (buy != null ? !buy.equals(that.buy) : that.buy != null) return false;
        return !(sale != null ? !sale.equals(that.sale) : that.sale != null);

    }

    @Override
    public int hashCode() {
        int result = exchange_currency != null ? exchange_currency.hashCode() : 0;
        result = 31 * result + (base_currency != null ? base_currency.hashCode() : 0);
        result = 31 * result + (buy != null ? buy.hashCode() : 0);
        result = 31 * result + (sale != null ? sale.hashCode() : 0);
        return result;
    }
}
