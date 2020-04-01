package com.lashkevich.stores.test;

import com.lashkevich.stores.dao.GoodPriceDao;
import com.lashkevich.stores.dao.impl.GoodPriceDaoImpl;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.entity.GoodPrice;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.util.provider.impl.TestConnectionProviderImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GoodPriceDaoTest {
    private GoodPrice firstExpectedGoodPrice;
    private GoodPrice secondExpectedGoodPrice;
    private GoodPrice thirdExpectedGoodPrice;
    private GoodPrice fourthExpectedGoodPrice;
    private GoodPrice fifthExpectedGoodPrice;
    private GoodPrice firstChangeExpectedGoodPrice;
    private GoodPriceDao goodPriceDao;

    @Before
    public void setUp() {
        firstExpectedGoodPrice = new GoodPrice(new Country(1, "Belarus"), new Good(22, "Apple", "Apple", "Apple"), new BigDecimal("1.0"));
        secondExpectedGoodPrice = new GoodPrice(new Country(1, "Belarus"), new Good(23, "Android", "Android", "Android"), new BigDecimal("2.0"));
        thirdExpectedGoodPrice = new GoodPrice(new Country(2, "Russia"), new Good(23, "Android", "Android", "Android"), new BigDecimal("2.0"));
        fourthExpectedGoodPrice = new GoodPrice(new Country(2, "Russia"), new Good(22, "Apple", "Apple", "Apple"), new BigDecimal("3.0"));
        firstChangeExpectedGoodPrice = new GoodPrice(new Country(1, "Belarus"), new Good(22, "Apple", "Apple", "Apple"), new BigDecimal("2.0"));
        fifthExpectedGoodPrice = new GoodPrice(new Country(1, "Belarus"), new Good(22, "Apple", "Apple", "Apple"), new BigDecimal("4.0"));

        goodPriceDao = new GoodPriceDaoImpl();
        ((GoodPriceDaoImpl) goodPriceDao).setConnectionProvider(new TestConnectionProviderImpl());
    }

    @Test
    public void findAllTest() throws DaoStoreException {
        List<GoodPrice> expectedGoodPrices = new ArrayList<>();
        expectedGoodPrices.add(firstExpectedGoodPrice);
        expectedGoodPrices.add(secondExpectedGoodPrice);
        expectedGoodPrices.add(thirdExpectedGoodPrice);
        expectedGoodPrices.add(fourthExpectedGoodPrice);
        Assert.assertEquals(expectedGoodPrices, goodPriceDao.findAll());
    }

    @Test
    public void findByIdTest() throws DaoStoreException {
        Assert.assertEquals(firstExpectedGoodPrice, goodPriceDao.findByCountryAndGood(1,22));
    }

    @Test
    public void removeTest() throws DaoStoreException {
        Assert.assertTrue(goodPriceDao.remove(2,22));
    }

    @Test
    public void updateTest() throws DaoStoreException {
        Assert.assertTrue(goodPriceDao.update(2, 22, firstChangeExpectedGoodPrice));
    }

    @Test
    public void addTest() throws DaoStoreException {
        Assert.assertTrue(goodPriceDao.add(fifthExpectedGoodPrice));
    }
}
