package com.lashkevich.stores.test;

import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.dao.impl.GoodDaoImpl;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.util.provider.impl.TestConnectionProviderImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GoodDaoTest {
    private Good firstExpectedGood;
    private Good secondExpectedZGood;
    private Good thirdExpectedGood;
    private Good fourthExpectedZGood;
    private Good firstChangeExpectedGood;
    private GoodDao goodDao;

    @Before
    public void setUp() {
        firstExpectedGood = new Good(22, "Apple", "Apple", "Apple");
        secondExpectedZGood = new Good(23, "Android", "Android", "Android");
        thirdExpectedGood = new Good(24, "Xiaomi", "Xiaomi", "Xiaomi");
        fourthExpectedZGood = new Good(25, "Samsung", "Samsung", "Samsung");
        firstChangeExpectedGood = new Good(22, "Apple", "ios", "Apple");

        goodDao = new GoodDaoImpl();
        ((GoodDaoImpl) goodDao).setConnectionProvider(new TestConnectionProviderImpl());
    }

    @Test
    public void findAllTest() throws DaoStoreException {
        List<Good> expectedGoods = new ArrayList<>();
        expectedGoods.add(firstExpectedGood);
        expectedGoods.add(secondExpectedZGood);
        expectedGoods.add(thirdExpectedGood);

        Assert.assertEquals(expectedGoods, goodDao.findAll());
    }

    @Test
    public void findByIdTest() throws DaoStoreException {
        Assert.assertEquals(firstExpectedGood, goodDao.findById(22));
    }

    @Test
    public void addTest() throws DaoStoreException {
        Assert.assertTrue(goodDao.add(fourthExpectedZGood));
    }

    @Test
    public void removeTest() throws DaoStoreException {
        Assert.assertTrue(goodDao.remove(24));
    }

    @Test
    public void updateTest() throws DaoStoreException {
        Assert.assertTrue(goodDao.update(firstChangeExpectedGood));
    }
}
