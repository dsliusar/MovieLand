package com.dsliusar.persistence.dao.http;

import com.dsliusar.persistence.dao.CurrencyDao;
import com.dsliusar.tools.exceptions.NotFoundException;
import com.dsliusar.tools.http.entities.CurrencyEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CurrencyDaoImpl implements CurrencyDao {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${url.nbu.http}")
    private String nbuCurrencyHttpUrl;

    private Map<String, Double> mapCurrency = new ConcurrentHashMap<>();

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * PostConstruct needed to warm up the NBU map
     * Getting NBU rates from http URL
     * Method is running every day by scheduler
     * @throws IOException
     */
    @PostConstruct
    @Scheduled(cron = "${nbu.update.cron}")
    public void getNbuRates() throws IOException {
        LOGGER.info("Loading NBU rates from: {}", nbuCurrencyHttpUrl);
        String nbuRatesString = Request.Get(nbuCurrencyHttpUrl)
                .connectTimeout(2000)
                .socketTimeout(2000)
                .execute().returnContent().asString();
       fillCurrencyMap(mapper.readValue(nbuRatesString, CurrencyEntity[].class));

    }

    /**
     * Fill currency  Map
     * @param currencyEntities
     */
    private void fillCurrencyMap(CurrencyEntity[] currencyEntities) {
        for (CurrencyEntity currencyEntity : currencyEntities) {
           mapCurrency.put(currencyEntity.getExchange_currency(), currencyEntity.getSale());
        }
    }

    /**
     * Return rate by requested currency
     * @param requestedCurrency
     * @return SalesRate
     */
    public Double getSalesRate(String requestedCurrency) {
        Double salesRate = mapCurrency.get(requestedCurrency);
        if (salesRate != null){
            return salesRate;
        }
        throw new NotFoundException("Requested currency "
                + requestedCurrency + " is not supported by application");
    }

    /**
     * For testing purposes
     * @param nbuCurrencyHttpUrl
     */
    public void setNbuCurrencyHttpUrl(String nbuCurrencyHttpUrl) {
        this.nbuCurrencyHttpUrl = nbuCurrencyHttpUrl;
    }
}
