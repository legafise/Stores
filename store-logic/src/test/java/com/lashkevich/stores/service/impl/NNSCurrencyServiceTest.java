package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.CurrencyDao;
import com.lashkevich.stores.entity.Currency;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.service.CurrencyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NNSCurrencyServiceTest {
    private CurrencyService currencyService;
    private CurrencyDao currencyDao;
    private Currency firstTestCurrency;
    private Currency secondTestCurrency;
    private List<Currency> testCurrencyList;
    private Optional<Currency> firstTestCurrencyOptional;
    private Optional<Currency> secondTestCurrencyOptional;

    @Before
    public void setUp() {
        currencyService = new NNSCurrencyService();
        currencyDao = mock(CurrencyDao.class);
        currencyService.setCurrencyDao(currencyDao);

        firstTestCurrency = new Currency(1, "United States Dollar", new BigDecimal("1.0"), "$");
        secondTestCurrency = new Currency(2, "Belarusian ruble", new BigDecimal("2.6"), "BYN");
        firstTestCurrencyOptional = Optional.of(firstTestCurrency);
        secondTestCurrencyOptional = Optional.of(secondTestCurrency);

        testCurrencyList = new ArrayList<>();
        testCurrencyList.add(firstTestCurrency);
    }

    @Test
    public void addCurrencyPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(currencyDao.findAll()).thenReturn(testCurrencyList);
        when(currencyDao.add(secondTestCurrency)).thenReturn(true);
        Assert.assertTrue(currencyService.addCurrency(secondTestCurrency));
    }

    @Test
    public void addCurrencyNegativeTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(currencyDao.findAll()).thenReturn(testCurrencyList);
        when(currencyDao.add(firstTestCurrency)).thenReturn(true);
        Assert.assertFalse(currencyService.addCurrency(firstTestCurrency));
    }

    @Test
    public void findAllCurrenciesPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(currencyDao.findAll()).thenReturn(testCurrencyList);
        Assert.assertEquals(currencyService.findAllCurrencies(), testCurrencyList);
    }

    @Test
    public void findCurrencyByIdPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(currencyDao.findById(1)).thenReturn(firstTestCurrencyOptional);
        Assert.assertEquals(currencyService.findCurrencyById("1"), firstTestCurrency);
    }

    @Test(expected = NNSServiceStoreException.class)
    public void findCurrencyByIdWithInvalidCurrencyIdTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(currencyDao.findById(1)).thenReturn(firstTestCurrencyOptional);
        Assert.assertEquals(currencyService.findCurrencyById("fgweg"), firstTestCurrency);
    }

    @Test
    public void removeCurrencyPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(currencyDao.remove(1)).thenReturn(true);
        Assert.assertTrue(currencyService.removeCurrency("1"));
    }

    @Test(expected = NNSServiceStoreException.class)
    public void removeCurrencyWithInvalidCurrencyIdTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(currencyDao.remove(1)).thenReturn(true);
        Assert.assertTrue(currencyService.removeCurrency("sfhoasdg"));
    }

    @Test
    public void updateCurrencyPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        testCurrencyList.add(secondTestCurrency);
        when(currencyDao.findAll()).thenReturn(testCurrencyList);
        when(currencyDao.update(firstTestCurrency)).thenReturn(true);
        firstTestCurrency.setName("Roma");
        Assert.assertTrue(currencyService.updateCurrency(firstTestCurrency));
    }

    @Test
    public void updateCurrencyNegativeTest() throws NSSDaoStoreException, NNSServiceStoreException {
        testCurrencyList.add(secondTestCurrency);
        when(currencyDao.findAll()).thenReturn(testCurrencyList);
        when(currencyDao.update(firstTestCurrency)).thenReturn(true);
        firstTestCurrency.setSymbol("BYN");
        Assert.assertFalse(currencyService.updateCurrency(firstTestCurrency));
    }
}