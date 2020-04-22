package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.CountryDao;
import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.dao.GoodPriceDao;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.entity.GoodPrice;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.exception.ServiceStoreException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GoodPriceServiceTest {
    private GoodPriceService goodPriceService;
    private GoodPriceDao goodPriceDao;
    private GoodDao goodDao;
    private CountryDao countryDao;
    private GoodPrice firstTestGoodPrice;
    private GoodPrice firstChangeTestGoodPrice;
    private GoodPrice secondTestGoodPrice;
    private List<GoodPrice> firstTestGoodPrices;
    private List<GoodPrice> secondTestGoodPrices;
    private List<GoodPrice> thirdTestGoodPrices;
    private Good firstTestGood;
    private Good secondTestGood;
    private List<Good> goods;
    private Country testCountry;
    private List<Country> countries;


    @Before
    public void setUp() {
        goodPriceService = new GoodPriceService();
        goodPriceDao = mock(GoodPriceDao.class);
        countryDao = mock(CountryDao.class);
        goodDao = mock(GoodDao.class);
        goodPriceService.setGoodPriceDao(goodPriceDao);
        goodPriceService.setGoodDao(goodDao);
        goodPriceService.setCountryDao(countryDao);

        firstTestGoodPrice = new GoodPrice(new Country(1, "Belarus"), new Good(22, "Apple", "Apple", "Apple"), new BigDecimal("1.0"));
        firstChangeTestGoodPrice = new GoodPrice(new Country(1, "Belarus"), new Good(22, "Apple", "Apple", "Apple"), new BigDecimal("3.0"));
        secondTestGoodPrice = new GoodPrice(new Country(1, "Belarus"), new Good(23, "Android", "Android", "Android"), new BigDecimal("2.0"));

        firstTestGoodPrices = new ArrayList<>();
        firstTestGoodPrices.add(firstTestGoodPrice);

        secondTestGoodPrices = new ArrayList<>();
        secondTestGoodPrices.add(secondTestGoodPrice);

        thirdTestGoodPrices = new ArrayList<>();
        thirdTestGoodPrices.add(firstTestGoodPrice);
        thirdTestGoodPrices.add(secondTestGoodPrice);

        firstTestGood = new Good(22, "Apple", "Apple", "Apple");
        secondTestGood = new Good(23, "Android", "Android", "Android");

        goods = new ArrayList<>();
        goods.add(firstTestGood);

        testCountry = new Country(1, "Belarus");

        countries = new ArrayList<>();
        countries.add(testCountry);
    }

    @Test
    public void addGoodPricePositiveTest() throws DaoStoreException, ServiceStoreException {
        when(countryDao.findAll()).thenReturn(countries);
        when(goodDao.findAll()).thenReturn(goods);
        when(goodPriceDao.findAll()).thenReturn(secondTestGoodPrices);
        when(goodPriceDao.add(firstTestGoodPrice)).thenReturn(true);
        Assert.assertTrue(goodPriceService.addGoodPrice(firstTestGoodPrice));
    }

    @Test
    public void addGoodPriceNegativeTest() throws DaoStoreException, ServiceStoreException {
        when(countryDao.findAll()).thenReturn(countries);
        when(goodDao.findAll()).thenReturn(goods);
        when(goodPriceDao.findAll()).thenReturn(secondTestGoodPrices);
        when(goodPriceDao.add(firstTestGoodPrice)).thenReturn(true);
        firstTestGoodPrice.getCountry().setName("Moldova");
        Assert.assertFalse(goodPriceService.addGoodPrice(firstTestGoodPrice));
    }

    @Test
    public void findAllGoodPricesTest() throws DaoStoreException, ServiceStoreException {
        when(goodPriceDao.findAll()).thenReturn(firstTestGoodPrices);
        Assert.assertEquals(goodPriceService.findAllGoodPrices(), firstTestGoodPrices);
    }

    @Test
    public void findGoodPriceByCountryAndGoodIdTest() throws DaoStoreException, ServiceStoreException {
        when(goodPriceDao.findByCountryAndGood(1,22)).thenReturn(firstTestGoodPrice);
        Assert.assertEquals(goodPriceService.findGoodPriceByCountryAndGoodId("1", "22"), firstTestGoodPrice);
    }

    @Test (expected = ServiceStoreException.class)
    public void findGoodPriceWithInvalidCountryAndGoodIdTest() throws ServiceStoreException {
        goodPriceService.findGoodPriceByCountryAndGoodId("odin", "dvadchat dva");
    }

    @Test
    public void removeGoodPriceByCountryAndGoodIdTest() throws DaoStoreException, ServiceStoreException {
        when(goodPriceDao.remove(1,22)).thenReturn(true);
        Assert.assertTrue(goodPriceService.removeGoodPriceByCountryAndGoodId("1", "22"));
    }

    @Test (expected = ServiceStoreException.class)
    public void removeGoodPriceWithInvalidCountryAndGoodIdTest() throws ServiceStoreException {
        goodPriceService.removeGoodPriceByCountryAndGoodId("Roma", "Maxim");
    }

    @Test
    public void updateGoodPriceByCountryAndGoodIdPositiveTest() throws DaoStoreException, ServiceStoreException {
        when(countryDao.findAll()).thenReturn(countries);
        when(goodDao.findAll()).thenReturn(goods);
        when(goodPriceDao.findAll()).thenReturn(thirdTestGoodPrices);
        when(goodPriceDao.update(1,22, firstChangeTestGoodPrice)).thenReturn(true);
        Assert.assertTrue(goodPriceService.updateGoodPriceByCountryAndGoodId("1", "22", firstChangeTestGoodPrice));
    }

    @Test
    public void updateGoodPriceByCountryAndGoodIdNegativeTest() throws DaoStoreException, ServiceStoreException {
        when(countryDao.findAll()).thenReturn(countries);
        when(goodDao.findAll()).thenReturn(goods);
        when(goodPriceDao.findAll()).thenReturn(thirdTestGoodPrices);
        when(goodPriceDao.update(1,23, firstChangeTestGoodPrice)).thenReturn(true);
        Assert.assertFalse(goodPriceService.updateGoodPriceByCountryAndGoodId("1", "23", firstChangeTestGoodPrice));
    }

    @Test
    public void updateGoodPriceWithInvalidCountryAndGoodIdTest() throws ServiceStoreException {
        Assert.assertFalse(goodPriceService.updateGoodPriceByCountryAndGoodId("ehe", "hgeh", firstChangeTestGoodPrice));
    }
}
