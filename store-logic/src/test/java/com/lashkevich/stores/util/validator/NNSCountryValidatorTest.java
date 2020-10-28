package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.entity.Currency;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class NNSCountryValidatorTest {
    private Country testCountry;

    @Before
    public void setUp() {
        testCountry = new Country(1, "Belarus", new Currency(2, "Belarusian ruble", new BigDecimal("2.6"), "BYN"));
    }

    @Test
    public void validatePositiveTest() {
        Assert.assertTrue(NNSCountryValidator.validate(testCountry));
    }

    @Test
    public void validateNegativeTest() {
        testCountry.setName("");
        Assert.assertFalse(NNSCountryValidator.validate(testCountry));
    }
}