package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.entity.Country;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NNSCityValidatorTest {
    private City testCity;

    @Before
    public void setUp() {
        testCity = new City(1, "Minsk", new Country(1, "Belarus"));
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
