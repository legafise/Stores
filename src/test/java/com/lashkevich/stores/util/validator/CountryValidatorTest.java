package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Country;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CountryValidatorTest {
    private Country testCountry;

    @Before
    public void setUp() {
        testCountry = new Country(1, "Belarus");
    }

    @Test
    public void validatePositiveTest() {
        Assert.assertTrue(CountryValidator.validate(testCountry));
    }

    @Test
    public void validateNegativeTest() {
        testCountry.setName("");
        Assert.assertFalse(CountryValidator.validate(testCountry));
    }
}