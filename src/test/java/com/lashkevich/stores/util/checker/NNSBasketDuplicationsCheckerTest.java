package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.entity.Good;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class NNSBasketDuplicationsCheckerTest {
    private Basket firstTestBasket;
    private Basket secondTestBasket;
    private Good firstTestGood;
    private Good secondTestGood;

    @Before
    public void setUp() {
        firstTestGood = new Good(23, "Android", "Android", "Android");
        Map<Good, Integer> firstTestGoodMap = new HashMap<>();
        firstTestGoodMap.put(firstTestGood, 1);
        firstTestBasket = new Basket(firstTestGoodMap);

        secondTestGood = new Good(22, "Apple", "Apple", "Apple");
        Map<Good, Integer>  secondTestGoodMap = new HashMap<>();
        secondTestGoodMap.put(secondTestGood, 2);
        secondTestBasket = new Basket(secondTestGoodMap);
    }

    @Test
    public void checkPositiveTest() {
        Assert.assertTrue(NNSBasketDuplicationsChecker.check(firstTestBasket, secondTestBasket));
    }

    @Test
    public void checkNegativeTest() {
        Assert.assertFalse(NNSBasketDuplicationsChecker.check(firstTestBasket, firstTestBasket));
    }
}