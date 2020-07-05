package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.GoodPriceDao;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.entity.GoodPrice;
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

public class NNSGoodPriceDaoTest {
    private GoodPrice firstExpectedGoodPrice;
    private GoodPrice secondExpectedGoodPrice;
    private GoodPrice thirdExpectedGoodPrice;
    private GoodPrice fourthExpectedGoodPrice;
    private GoodPrice fifthExpectedGoodPrice;
    private GoodPrice firstChangeExpectedGoodPrice;
    private GoodPriceDao goodPriceDao;

    @Before
    public void setUp() throws NNSConnectionPoolException {
        PropertiesReader testPropertiesReader = new NNSTestPropertiesReader();

        NNSConnectionPool.getInstance().setPropertiesReader(testPropertiesReader);
        NNSConnectionPool.getInstance().initializeConnectionPool(1);

        firstExpectedGoodPrice = new GoodPrice(new Country(1, "Belarus"), new Good(22, "Apple", "Apple", "Apple"), new BigDecimal("1.0"));
        secondExpectedGoodPrice = new GoodPrice(new Country(1, "Belarus"), new Good(23, "Android", "Android", "Android"), new BigDecimal("2.0"));
        thirdExpectedGoodPrice = new GoodPrice(new Country(2, "Russia"), new Good(23, "Android", "Android", "Android"), new BigDecimal("2.0"));
        fourthExpectedGoodPrice = new GoodPrice(new Country(2, "Russia"), new Good(22, "Apple", "Apple", "Apple"), new BigDecimal("3.0"));
        firstChangeExpectedGoodPrice = new GoodPrice(new Country(1, "Belarus"), new Good(22, "Apple", "Apple", "Apple"), new BigDecimal("2.0"));
        fifthExpectedGoodPrice = new GoodPrice(new Country(1, "Belarus"), new Good(22, "Apple", "Apple", "Apple"), new BigDecimal("4.0"));

        goodPriceDao = new NNSGoodPriceDao();
        goodPriceDao.setPropertiesReader(testPropertiesReader);
    }

    @Test
    public void findAllTest() throws NSSDaoStoreException {
        List<GoodPrice> expectedGoodPrices = new ArrayList<>();
        expectedGoodPrices.add(firstExpectedGoodPrice);
        expectedGoodPrices.add(secondExpectedGoodPrice);
        expectedGoodPrices.add(fourthExpectedGoodPrice);
        expectedGoodPrices.add(thirdExpectedGoodPrice);
        Assert.assertEquals(expectedGoodPrices, goodPriceDao.findAll());
    }

    @Test
    public void findByIdTest() throws NSSDaoStoreException {
        Assert.assertEquals(firstExpectedGoodPrice, goodPriceDao.findByCountryAndGood(1,22).get());
    }

    @Test
    public void removeTest() throws NSSDaoStoreException {
        Assert.assertTrue(goodPriceDao.remove(2,22));
    }

    @Test
    public void updateTest() throws NSSDaoStoreException {
        Assert.assertTrue(goodPriceDao.update(2, 22, firstChangeExpectedGoodPrice));
    }

    @Test
    public void addTest() throws NSSDaoStoreException {
        Assert.assertTrue(goodPriceDao.add(fifthExpectedGoodPrice));
    }
}
