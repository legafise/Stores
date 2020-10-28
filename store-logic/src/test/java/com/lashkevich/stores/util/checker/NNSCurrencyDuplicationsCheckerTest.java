package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.Currency;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NNSCurrencyDuplicationsCheckerTest {
    Currency firstTestCurrency;
    Currency secondTestCurrency;
    Currency addedTestCurrency;
    List<Currency> currencies;

    @Before
    public void setUp() {
        firstTestCurrency = new Currency(2, "Belarusian ruble", new BigDecimal("2.6"), "BYN");
        secondTestCurrency = new Currency(3, "Russian ruble", new BigDecimal("76.8"), "RUB");
        addedTestCurrency = new Currency(1, "United States Dollar", new BigDecimal("1.0"), "$");

        currencies = new ArrayList<>();
        currencies.add(firstTestCurrency);
        currencies.add(secondTestCurrency);
    }

    @Test
    public void checkAddingPositiveTest() {
        Assert.assertTrue(NNSCurrencyDuplicationsChecker.checkCurrencyAdding(addedTestCurrency, currencies));
    }

    @Test
    public void checkAddingNegativeTest() {
        addedTestCurrency.setSymbol("RUB");
        Assert.assertFalse(NNSCurrencyDuplicationsChecker.checkCurrencyAdding(addedTestCurrency, currencies));
    }

    @Test
    public void checkUpdatingPositiveTest() {
        firstTestCurrency.setName("Bel rub");
        Assert.assertTrue(NNSCurrencyDuplicationsChecker.checkCurrencyUpdating(firstTestCurrency, currencies));
    }

    @Test
    public void checkUpdatingNegativeTest() {
        firstTestCurrency.setName("Russian ruble");
        Assert.assertFalse(NNSCurrencyDuplicationsChecker.checkCurrencyUpdating(firstTestCurrency, currencies));
    }
}