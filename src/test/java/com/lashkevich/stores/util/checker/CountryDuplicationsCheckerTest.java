package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.Country;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CountryDuplicationsCheckerTest {
    private Country firstTestCountry;
    private Country secondTestCountry;
    private List<Country> testCountryList;

    @Before
    public void setUp() {
        firstTestCountry = new Country(1, "Belarus");
        secondTestCountry = new Country(2, "Russia");
        testCountryList = new ArrayList<>();
        testCountryList.add(secondTestCountry);
    }

    @Test
    public void checkPositiveTest() {
        Assert.assertTrue(CountryDuplicationsChecker.check(firstTestCountry, testCountryList));
    }

    @Test
    public void checkNegativeTest() {
        testCountryList.add(firstTestCountry);
        Assert.assertFalse(CountryDuplicationsChecker.check(firstTestCountry, testCountryList));
    }
}