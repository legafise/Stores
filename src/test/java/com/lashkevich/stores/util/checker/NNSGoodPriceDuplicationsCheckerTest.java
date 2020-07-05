package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.entity.GoodPrice;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NNSGoodPriceDuplicationsCheckerTest {
    private GoodPrice firstTestGoodPrice;
    private GoodPrice firstChangeTestGoodPrice;
    private GoodPrice secondTestGoodPrice;
    private List<GoodPrice> testGoodPriceList;

    @Before
    public void setUp() {
        firstTestGoodPrice = new GoodPrice(new Country(1, "Belarus"), new Good(22, "Apple", "Apple", "Apple"), new BigDecimal("1.0"));
        firstChangeTestGoodPrice = new GoodPrice(new Country(1, "Belarus"), new Good(22, "Apple", "Apple", "Apple"), new BigDecimal("3.0"));
        secondTestGoodPrice = new GoodPrice(new Country(1, "Belarus"), new Good(23, "Android", "Android", "Android"), new BigDecimal("2.0"));
        testGoodPriceList = new ArrayList<>();
        testGoodPriceList.add(secondTestGoodPrice);
    }

    @Test
    public void checkGoodAddingPositiveTest() {
        Assert.assertTrue(NNSGoodPriceDuplicationsChecker.checkGoodAdding(firstTestGoodPrice, testGoodPriceList));
    }

    @Test
    public void checkGoodAddingNegativeTest() {
        testGoodPriceList.add(firstChangeTestGoodPrice);
        Assert.assertFalse(NNSGoodPriceDuplicationsChecker.checkGoodAdding(firstTestGoodPrice, testGoodPriceList));
    }

    @Test
    public void checkGoodUpdatingPositiveTest() {
        testGoodPriceList.add(firstTestGoodPrice);
        Assert.assertTrue(NNSGoodPriceDuplicationsChecker.checkGoodUpdating(firstChangeTestGoodPrice, testGoodPriceList, 22, 1));
    }

    @Test
    public void checkGoodUpdatingNegativeTest() {
        testGoodPriceList.add(firstTestGoodPrice);
        Assert.assertFalse(NNSGoodPriceDuplicationsChecker.checkGoodUpdating(secondTestGoodPrice, testGoodPriceList, 22, 1));
    }
}