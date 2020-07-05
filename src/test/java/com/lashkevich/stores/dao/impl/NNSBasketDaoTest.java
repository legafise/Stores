package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.BasketDao;
import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.NNSConnectionPoolException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.pool.NNSConnectionPool;
import com.lashkevich.stores.util.reader.PropertiesReader;
import com.lashkevich.stores.util.reader.impl.NNSTestPropertiesReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NNSBasketDaoTest {
    private Basket firstExpectedBasket;
    private Basket secondExpectedBasket;
    private Basket thirdExpectedBasket;
    private Basket fourthExpectedBasket;
    private Basket fifthExpectedBasket;
    private Basket sixthExpectedBasket;
    private BasketDao basketDao;

    @Before
    public void setUp() throws NNSConnectionPoolException {
        PropertiesReader testPropertiesReader = new NNSTestPropertiesReader();

        NNSConnectionPool.getInstance().setPropertiesReader(testPropertiesReader);
        NNSConnectionPool.getInstance().initializeConnectionPool(1);

        Map<Good, Integer> firstGoods = new HashMap<>();
        firstGoods.put(new Good(23, "Android", "Android", "Android"), 1);
        firstExpectedBasket = new Basket(firstGoods);

        Map<Good, Integer> secondGoods = new HashMap<>();
        secondGoods.put(new Good(22, "Apple", "Apple", "Apple"), 2);
        secondExpectedBasket = new Basket(secondGoods);

        Map<Good, Integer> thirdGoods = new HashMap<>();
        thirdGoods.put(new Good(22, "Apple", "Apple", "Apple"), 2);
        thirdExpectedBasket = new Basket(thirdGoods);

        Map<Good, Integer> fourthGoods = new HashMap<>();
        fourthGoods.put(new Good(23, "Android", "Android", "Android"), 1);
        fourthExpectedBasket = new Basket(fourthGoods);

        Map<Good, Integer> fifthGoods = new HashMap<>();
        fifthGoods.put(new Good(22, "Apple", "Apple", "Apple"), 1);
        fifthGoods.put(new Good(23, "Android", "Android", "Android"), 2);
        fifthExpectedBasket = new Basket(fifthGoods);

        Map<Good, Integer> sixthGoods = new HashMap<>();
        sixthGoods.put(new Good(23, "Android", "Android", "Android"), 1);
        sixthGoods.put(new Good(22, "Apple", "Apple", "Apple"), 2);
        sixthExpectedBasket = new Basket(sixthGoods);

        basketDao = new NNSBasketDao();
        basketDao.setPropertiesReader(testPropertiesReader);
    }

    @Test
    public void findAllTest() throws NSSDaoStoreException {
        List<Basket> expectedBaskets = new ArrayList<>();
        expectedBaskets.add(secondExpectedBasket);
        expectedBaskets.add(thirdExpectedBasket);
        expectedBaskets.add(firstExpectedBasket);
        expectedBaskets.add(fourthExpectedBasket);

        Assert.assertEquals(expectedBaskets, basketDao.findAll());
    }

    @Test
    public void findByUserTest() throws NSSDaoStoreException {
        Assert.assertEquals(sixthExpectedBasket, basketDao.findByUser(8).get());
    }

    @Test
    public void addTest() throws NSSDaoStoreException {
        Assert.assertTrue(basketDao.add(firstExpectedBasket, 10));
    }

    @Test
    public void removeTest() throws NSSDaoStoreException {
        Assert.assertTrue(basketDao.remove(9));

    }

    @Test
    public void updateTest() throws NSSDaoStoreException {
        Assert.assertTrue(basketDao.update(fifthExpectedBasket, 9));
    }
}
