package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.NNSConnectionPoolException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.pool.NNSConnectionPool;
import com.lashkevich.stores.util.reader.PropertiesReader;
import com.lashkevich.stores.util.reader.impl.NNSTestPropertiesReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NNSGoodDaoTest {
    private Good firstExpectedGood;
    private Good secondExpectedZGood;
    private Good thirdExpectedGood;
    private Good fourthExpectedZGood;
    private Good firstChangeExpectedGood;
    private GoodDao goodDao;

    @Before
    public void setUp() throws NNSConnectionPoolException {
        PropertiesReader testPropertiesReader = new NNSTestPropertiesReader();

        NNSConnectionPool.getInstance().setPropertiesReader(testPropertiesReader);
        NNSConnectionPool.getInstance().initializeConnectionPool(1);

        firstExpectedGood = new Good(22, "Apple", new BigDecimal("1.0"),"Apple", "Apple", "Apple");
        secondExpectedZGood = new Good(23, "Android", new BigDecimal("2.0"), "Android", "Android", "Android");
        thirdExpectedGood = new Good(24, "Xiaomi", new BigDecimal("4.0"),"Xiaomi", "Xiaomi", "Xiaomi");
        fourthExpectedZGood = new Good(25, "Samsung", new BigDecimal("4.0"), "Samsung", "Samsung", "Samsung");
        firstChangeExpectedGood = new Good(22, "Apple", new BigDecimal("1"), "ios", "Apple", "Apple");

        goodDao = new NNSGoodDao();
        goodDao.setPropertiesReader(testPropertiesReader);
    }

    @Test
    public void findAllTest() throws NSSDaoStoreException {
        List<Good> expectedGoods = new ArrayList<>();
        expectedGoods.add(firstExpectedGood);
        expectedGoods.add(secondExpectedZGood);
        expectedGoods.add(thirdExpectedGood);

        Assert.assertEquals(expectedGoods, goodDao.findAll());
    }

    @Test
    public void findByIdTest() throws NSSDaoStoreException {
        Assert.assertEquals(firstExpectedGood, goodDao.findById(22).get());
    }

    @Test
    public void addTest() throws NSSDaoStoreException {
        Assert.assertTrue(goodDao.add(fourthExpectedZGood));
    }

    @Test
    public void removeTest() throws NSSDaoStoreException {
        Assert.assertTrue(goodDao.remove(24));
    }

    @Test
    public void updateTest() throws NSSDaoStoreException {
        Assert.assertTrue(goodDao.update(firstChangeExpectedGood));
    }
}
