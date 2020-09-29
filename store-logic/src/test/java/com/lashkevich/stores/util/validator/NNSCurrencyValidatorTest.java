package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Currency;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class NNSCurrencyValidatorTest {
    private Currency testCurrency;

    @Before
    public void setUp() {
        testCurrency = new Currency(2, "Belarusian ruble", new BigDecimal("2.6"), "BYN");
    }

    @Test
    public void validateCurrencyPositiveTest() {
        Assert.assertTrue(NNSCurrencyValidator.validateCurrency(testCurrency));
    }

    @Test
    public void validateCurrencyNegativeTest() {
        testCurrency.setSymbol(null);
        Assert.assertFalse(NNSCurrencyValidator.validateCurrency(testCurrency));
    }
}