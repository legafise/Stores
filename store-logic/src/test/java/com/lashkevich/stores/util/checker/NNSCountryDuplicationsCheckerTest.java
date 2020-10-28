package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.entity.Currency;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NNSCountryDuplicationsCheckerTest {
    private Country firstTestCountry;
    private Country secondTestCountry;
    private List<Country> testCountryList;

    @Before
    public void setUp() {
        firstTestCountry = new Country(1, "Belarus", new Currency(2, "Belarusian ruble", new BigDecimal("2.6"), "BYN"));
        secondTestCountry = new Country(2, "Russia", new Currency(3, "Russian ruble", new BigDecimal("76.8"), "RUB"));
        testCountryList = new ArrayList<>();
        testCountryList.add(secondTestCountry);
    }

    @Test
    public void checkPositiveTest() {
        Assert.assertTrue(NNSCountryDuplicationsChecker.check(firstTestCountry, testCountryList));
    }

    @Test
    public void checkNegativeTest() {
        testCountryList.add(firstTestCountry);
        Assert.assertFalse(NNSCountryDuplicationsChecker.check(firstTestCountry, testCountryList));
    }
}