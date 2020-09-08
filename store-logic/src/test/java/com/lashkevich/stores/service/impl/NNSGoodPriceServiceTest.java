package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.GoodPriceDao;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.entity.GoodPrice;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.service.CountryService;
import com.lashkevich.stores.service.GoodPriceService;
import com.lashkevich.stores.service.GoodService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NNSGoodPriceServiceTest {
    private GoodPriceService goodPriceService;
    private GoodPriceDao goodPriceDao;
    private GoodService goodService;
    private CountryService countryService;
    private GoodPrice firstTestGoodPrice;
    private Optional<GoodPrice> firstTestGoodPriceOptional;
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
        goodPriceService = new NNSGoodPriceService();
        goodPriceDao = mock(GoodPriceDao.class);
        countryService = mock(CountryService.class);
        goodService = mock(GoodService.class);
        goodPriceService.setGoodPriceDao(goodPriceDao);
        goodPriceService.setGoodService(goodService);
        goodPriceService.setCountryService(countryService);

        firstTestGoodPrice = new GoodPrice(new Country(1, "Belarus"), new Good(22, "Apple", "Apple", "Apple"), new BigDecimal("1.0"));
        firstTestGoodPriceOptional = Optional.of(firstTestGoodPrice);
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
    public void addGoodPricePositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(countryService.findAllCountries()).thenReturn(countries);
        when(goodService.findAllGoods()).thenReturn(goods);
        when(goodPriceDao.findAll()).thenReturn(secondTestGoodPrices);
        when(goodPriceDao.add(firstTestGoodPrice)).thenReturn(true);
        Assert.assertTrue(goodPriceService.addGoodPrice(firstTestGoodPrice));
    }

    @Test
    public void addGoodPriceNegativeTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(countryService.findAllCountries()).thenReturn(countries);
        when(goodService.findAllGoods()).thenReturn(goods);
        when(goodPriceDao.findAll()).thenReturn(secondTestGoodPrices);
        when(goodPriceDao.add(firstTestGoodPrice)).thenReturn(true);
        firstTestGoodPrice.getCountry().setName("Moldova");
        Assert.assertFalse(goodPriceService.addGoodPrice(firstTestGoodPrice));
    }

    @Test
    public void findAllGoodPricesTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(goodPriceDao.findAll()).thenReturn(firstTestGoodPrices);
        Assert.assertEquals(goodPriceService.findAllGoodPrices(), firstTestGoodPrices);
    }

    @Test
    public void findGoodPriceByCountryAndGoodIdTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(goodPriceDao.findByCountryAndGood(1,22)).thenReturn(firstTestGoodPriceOptional);
        Assert.assertEquals(goodPriceService.findGoodPriceByCountryAndGoodId("1", "22"), firstTestGoodPrice);
    }

    @Test (expected = NNSServiceStoreException.class)
    public void findGoodPriceWithInvalidCountryAndGoodIdTest() throws NNSServiceStoreException {
        goodPriceService.findGoodPriceByCountryAndGoodId("odin", "dvadchat dva");
    }

    @Test
    public void removeGoodPriceByCountryAndGoodIdTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(goodPriceDao.remove(1,22)).thenReturn(true);
        Assert.assertTrue(goodPriceService.removeGoodPriceByCountryAndGoodId("1", "22"));
    }

    @Test (expected = NNSServiceStoreException.class)
    public void removeGoodPriceWithInvalidCountryAndGoodIdTest() throws NNSServiceStoreException {
        goodPriceService.removeGoodPriceByCountryAndGoodId("Roma", "Maxim");
    }

    @Test
    public void updateGoodPriceByCountryAndGoodIdPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(countryService.findAllCountries()).thenReturn(countries);
        when(goodService.findAllGoods()).thenReturn(goods);
        when(goodPriceDao.findAll()).thenReturn(thirdTestGoodPrices);
        when(goodPriceDao.update(1,22, firstChangeTestGoodPrice)).thenReturn(true);
        Assert.assertTrue(goodPriceService.updateGoodPriceByCountryAndGoodId("1", "22", firstChangeTestGoodPrice));
    }

    @Test
    public void updateGoodPriceByCountryAndGoodIdNegativeTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(countryService.findAllCountries()).thenReturn(countries);
        when(goodService.findAllGoods()).thenReturn(goods);
        when(goodPriceDao.findAll()).thenReturn(thirdTestGoodPrices);
        when(goodPriceDao.update(1,23, firstChangeTestGoodPrice)).thenReturn(true);
        Assert.assertFalse(goodPriceService.updateGoodPriceByCountryAndGoodId("1", "23", firstChangeTestGoodPrice));
    }

    @Test (expected = NNSServiceStoreException.class)
    public void updateGoodPriceWithInvalidCountryAndGoodIdTest() throws NNSServiceStoreException {
        Assert.assertFalse(goodPriceService.updateGoodPriceByCountryAndGoodId("ehe", "hgeh", firstChangeTestGoodPrice));
    }
}
