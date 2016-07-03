package com.dsliusar.persistence.dao.http;

import com.dsliusar.tools.http.entities.CurrencyEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CurrencyDaoImplTest {

    @Value("${url.nbu.http}")
    private String nbuHttpUrl;

    @Test
    public void getCurrencyTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-persistence-config.xml");
        CurrencyDaoImpl currencyDao = ctx.getBean(CurrencyDaoImpl.class);
        try {
            currencyDao.setNbuCurrencyHttpUrl(nbuHttpUrl);
            currencyDao.getNbuRates();
            String requestedCurrency = "USD";
            System.out.println(currencyDao.getSalesRate(requestedCurrency));
            Assert.assertNotNull(currencyDao.getSalesRate(requestedCurrency));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Configuration
    static class someConfig {
        @Bean
        PropertyPlaceholderConfigurer propConfig() {
            PropertyPlaceholderConfigurer ppc =  new PropertyPlaceholderConfigurer();
            ppc.setLocation(new ClassPathResource("properties-tools/application-dev.properties"));
            return ppc;
        }
    }

}
