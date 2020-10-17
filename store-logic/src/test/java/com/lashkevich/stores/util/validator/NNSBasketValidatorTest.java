package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.entity.Good;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class NNSBasketValidatorTest {
    private Basket testBasket;
    private Good testGood;

    @Before
    public void setUp() {
        testGood = new Good(23, "Android", new BigDecimal("2.0"), "Android", "Android", "Android");
        Map<Good, Integer> firstGoods = new HashMap<>();
        firstGoods.put(testGood, 1);
        testBasket = new Basket(firstGoods);
    }

    @Test
    public void validatePositiveTest() {
        Assert.assertTrue(NNSBasketValidator.validate(testBasket));
    }

    @Test
    public void validateNegativeTest() {
        Assert.assertFalse(NNSBasketValidator.validate(null));
    }
}
