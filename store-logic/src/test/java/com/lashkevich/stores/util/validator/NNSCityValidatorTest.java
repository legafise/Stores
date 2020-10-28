package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.entity.Currency;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class NNSCityValidatorTest {
    private City testCity;

    @Before
    public void setUp() {
        testCity = new City(1, "Minsk", new Country(1, "Belarus", new Currency(2, "Belarusian ruble", new BigDecimal("2.6"), "BYN")));
    }

    @Test
    public void validatePositiveTest() {
        Assert.assertTrue(NNSCityValidator.validate(testCity));
    }

    @Test
    public void validateNegativeTest() {
        testCity.setName("");
        Assert.assertFalse(NNSCityValidator.validate(testCity));
    }
}
