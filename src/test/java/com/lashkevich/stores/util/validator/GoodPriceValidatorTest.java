package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.entity.GoodPrice;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class GoodPriceValidatorTest {
    private GoodPrice testGoodPrice;

    @Before
    public void setUp() {
        testGoodPrice = new GoodPrice(new Country(1, "Belarus"), new Good(22, "Apple", "Apple", "Apple"), new BigDecimal("1.0"));
    }

    @Test
    public void validatePositiveTest() {
        Assert.assertTrue(GoodPriceValidator.validate(testGoodPrice));
    }

    @Test
    public void validateNegativeTest() {
        testGoodPrice.setPrice(new BigDecimal("0.0"));
        Assert.assertFalse(GoodPriceValidator.validate(testGoodPrice));
    }
}