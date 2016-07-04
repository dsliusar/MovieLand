package com.dsliusar.tools.http.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyEntity {
    @JsonProperty("ccy")
    private String exchangeCurrency;

    @JsonProperty("base_ccy")
    private String baseCurrency;

    @JsonProperty("buy")
    private Double buy;

    @JsonProperty("sale")
    private Double sale;

    public String getExchangeCurrency() {
        return exchangeCurrency;
    }

    public void setExchangeCurrency(String exchangeCurrency) {
        this.exchangeCurrency = exchangeCurrency;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
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
                "exchangeCurrency='" + exchangeCurrency + '\'' +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", buy=" + buy +
                ", sale=" + sale +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyEntity that = (CurrencyEntity) o;

        if (exchangeCurrency != null ? !exchangeCurrency.equals(that.exchangeCurrency) : that.exchangeCurrency != null)
            return false;
        if (baseCurrency != null ? !baseCurrency.equals(that.baseCurrency) : that.baseCurrency != null)
            return false;
        if (buy != null ? !buy.equals(that.buy) : that.buy != null) return false;
        return !(sale != null ? !sale.equals(that.sale) : that.sale != null);

    }

    @Override
    public int hashCode() {
        int result = exchangeCurrency != null ? exchangeCurrency.hashCode() : 0;
        result = 31 * result + (baseCurrency != null ? baseCurrency.hashCode() : 0);
        result = 31 * result + (buy != null ? buy.hashCode() : 0);
        result = 31 * result + (sale != null ? sale.hashCode() : 0);
        return result;
    }
}
