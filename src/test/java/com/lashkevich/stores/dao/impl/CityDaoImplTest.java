package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.CityDao;
import com.lashkevich.stores.dao.impl.CityDaoImpl;
import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.util.provider.impl.TestConnectionProviderImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CityDaoImplTest {
    private City firstExpectedCity;
    private City secondExpectedCity;
    private City thirdExpectedCity;
    private City fourthExpectedCity;
    private City firstChangeExpectedCity;
    private CityDao cityDao;

    @Before
    public void setUp() {
        firstExpectedCity = new City(1, "Minsk", new Country(1, "Belarus"));
        secondExpectedCity = new City(2, "Moscow", new Country(2, "Russia"));
        thirdExpectedCity = new City(3, "Gomel", new Country(1, "Belarus"));
        fourthExpectedCity = new City(4, "Smolensk", new Country(2, "Russia"));
        firstChangeExpectedCity = new City(1, "Menesk", new Country(1, "Belarus"));

        cityDao = new CityDaoImpl();
        cityDao.setConnectionProvider(new TestConnectionProviderImpl());
    }

    @Test
    public void findAllTest() throws DaoStoreException {
        List<City> expectedCities = new ArrayList<>();
        expectedCities.add(firstExpectedCity);
        expectedCities.add(secondExpectedCity);
        expectedCities.add(thirdExpectedCity);

        Assert.assertEquals(expectedCities, cityDao.findAll());
    }

    @Test
    public void findByIdTest() throws DaoStoreException {
        Assert.assertEquals(firstExpectedCity, cityDao.findById(1));
    }

    @Test
    public void addTest() throws DaoStoreException {
        Assert.assertTrue(cityDao.add(fourthExpectedCity));
    }

    @Test
    public void removeTest() throws DaoStoreException {
        Assert.assertTrue(cityDao.remove(3));
    }

    @Test
    public void updateTest() throws DaoStoreException {
        Assert.assertTrue(cityDao.update(firstExpectedCity));
    }
}
