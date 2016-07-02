package com.dsliusar.persistence.dao;

import com.dsliusar.tools.http.entities.CurrencyEntity;

import java.io.IOException;

public interface CurrencyDao {
    CurrencyEntity[] getNbuRates() throws IOException;
}
