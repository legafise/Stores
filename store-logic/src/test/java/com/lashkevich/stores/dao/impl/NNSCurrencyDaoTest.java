package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.CurrencyDao;
import com.lashkevich.stores.entity.Currency;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.pool.NNSConnectionPool;
import com.lashkevich.stores.util.reader.PropertiesReader;
import com.lashkevich.stores.util.reader.impl.NNSTestPropertiesReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NNSCurrencyDaoTest {
    private CurrencyDao currencyDao;
    private Currency firstExpectedCurrency;
    private Currency secondExpectedCurrency;
    private Currency thirdExpectedCurrency;
    private Currency fourthExpectedCurrency;
    private Currency addedCurrency;

    @Before
    public void setUp() throws Exception {
        PropertiesReader testPropertiesReader = new NNSTestPropertiesReader();

        NNSConnectionPool.getInstance().setPropertiesReader(testPropertiesReader);
        NNSConnectionPool.getInstance().initializeConnectionPool(1);

        firstExpectedCurrency = new Currency(1, "United States Dollar", new BigDecimal("1.0"), "$");
        secondExpectedCurrency = new Currency(2, "Belarusian ruble", new BigDecimal("2.6"), "BYN");
        thirdExpectedCurrency = new Currency(3, "Russian ruble", new BigDecimal("76.8"), "RUB");
        fourthExpectedCurrency = new Currency(4, "Polish zloty", new BigDecimal("3.91"), "PLN");
        addedCurrency = new Currency(5, "Ukrainian hryvnia", new BigDecimal("28.25"), "UAH");

        currencyDao = new NNSCurrencyDao();
        currencyDao.setPropertiesReader(testPropertiesReader);
    }

    @Test
    public void addTest() throws NSSDaoStoreException {
        Assert.assertTrue(currencyDao.add(addedCurrency));
    }

    @Test
    public void findAllTest() throws NSSDaoStoreException {
        List<Currency> currencies = new ArrayList<>();
        currencies.add(firstExpectedCurrency);
        currencies.add(secondExpectedCurrency);
        currencies.add(thirdExpectedCurrency);
        currencies.add(fourthExpectedCurrency);
        Assert.assertEquals(currencies, currencyDao.findAll());
    }

    @Test
    public void findByIdTest() throws NSSDaoStoreException {
        Assert.assertEquals(firstExpectedCurrency, currencyDao.findById(1).get());
    }

    @Test
    public void updateTest() throws NSSDaoStoreException {
        firstExpectedCurrency.setName("USD");
        Assert.assertTrue(currencyDao.update(firstExpectedCurrency));
    }

    @Test
    public void removeTest() throws NSSDaoStoreException {
        Assert.assertTrue(currencyDao.remove(4));
    }
}