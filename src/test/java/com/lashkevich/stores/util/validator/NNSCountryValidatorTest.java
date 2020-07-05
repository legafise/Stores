package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Country;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NNSCountryValidatorTest {
    private Country testCountry;

    @Before
    public void setUp() {
        testCountry = new Country(1, "Belarus");
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