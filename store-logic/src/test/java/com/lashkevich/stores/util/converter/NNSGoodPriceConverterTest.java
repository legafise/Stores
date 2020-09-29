package com.lashkevich.stores.util.converter;

import com.lashkevich.stores.entity.Currency;
import com.lashkevich.stores.entity.Good;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class NNSGoodPriceConverterTest {
    private Good firstTestGood;
    private Good firstTestGoodAfterConversion;
    private Currency firstTestCurrency;

    @Before
    public void setUp() {
        firstTestGood = new Good(1, "Samsung", new BigDecimal("2.0"), "Samsung", "Samsung", "Samsung");
        firstTestGoodAfterConversion = new Good(1, "Samsung", new BigDecimal("5.20"), "Samsung", "Samsung", "Samsung");
        firstTestCurrency = new Currency(2, "Belarusian ruble", new BigDecimal("2.6"), "BYN");
    }

    @Test
    public void convertPositiveTest() {
        Assert.assertEquals(NNSGoodPriceConverter.convert(firstTestGood, firstTestCurrency), firstTestGoodAfterConversion);
    }

    @Test (expected = AssertionError.class)
    public void convertNegativeTest() {
        firstTestGoodAfterConversion.setPrice(new BigDecimal("5000"));
        Assert.assertEquals(NNSGoodPriceConverter.convert(firstTestGood, firstTestCurrency), firstTestGoodAfterConversion);
    }
}