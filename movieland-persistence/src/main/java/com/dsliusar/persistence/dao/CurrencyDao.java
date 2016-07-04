package com.dsliusar.persistence.dao;

import java.io.IOException;

public interface CurrencyDao {
    void getNbuRates() throws IOException;
    Double getSalesRate(String requestedCurrency);
}
