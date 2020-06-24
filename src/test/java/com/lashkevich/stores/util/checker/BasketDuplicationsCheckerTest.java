package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.entity.Good;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketDuplicationsCheckerTest {
    private Basket firstTestBasket;
    private Basket secondTestBasket;
    private List<Basket> testBasketList;
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

        testBasketList = new ArrayList<>();
        testBasketList.add(secondTestBasket);
    }

    @Test
    public void checkPositiveTest() {
        Assert.assertTrue(BasketDuplicationsChecker.check(firstTestBasket, testBasketList));
    }

    @Test
    public void checkNegativeTest() {
        testBasketList.add(firstTestBasket);
        Assert.assertFalse(BasketDuplicationsChecker.check(firstTestBasket, testBasketList));
    }
}