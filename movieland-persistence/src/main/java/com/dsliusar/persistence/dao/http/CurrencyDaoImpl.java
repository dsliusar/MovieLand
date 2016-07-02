package com.dsliusar.persistence.dao.http;

import com.dsliusar.persistence.dao.CurrencyDao;
import com.dsliusar.tools.http.entities.CurrencyEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class CurrencyDaoImpl implements CurrencyDao{
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${url.nbu.http}")
    private String nbuCurrencyHttpUrl;

    private ObjectMapper mapper = new ObjectMapper();

    public CurrencyEntity[] getNbuRates() throws IOException {
            LOGGER.info("Loading NBU rates from: {}", nbuCurrencyHttpUrl);
            String nbuRatesString = Request.Get(nbuCurrencyHttpUrl)
                    .connectTimeout(2000)
                    .socketTimeout(2000)
                    .execute().returnContent().asString();
            CurrencyEntity[] currencyRates = mapper.readValue(nbuRatesString, CurrencyEntity[].class);
            return currencyRates;

    }

    public void setNbuCurrencyHttpUrl(String nbuCurrencyHttpUrl) {
        this.nbuCurrencyHttpUrl = nbuCurrencyHttpUrl;
    }

}
