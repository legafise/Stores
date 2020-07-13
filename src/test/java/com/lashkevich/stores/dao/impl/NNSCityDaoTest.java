package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.CityDao;
import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.exception.NNSConnectionPoolException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.pool.NNSConnectionPool;
import com.lashkevich.stores.util.reader.PropertiesReader;
import com.lashkevich.stores.util.reader.impl.NNSTestPropertiesReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NNSCityDaoTest {
    private City firstExpectedCity;
    private City secondExpectedCity;
    private City thirdExpectedCity;
    private City fourthExpectedCity;
    private CityDao cityDao;

    @Before
    public void setUp() throws NNSConnectionPoolException {
        PropertiesReader testPropertiesReader = new NNSTestPropertiesReader();

        NNSConnectionPool.getInstance().setPropertiesReader(testPropertiesReader);
        NNSConnectionPool.getInstance().initializeConnectionPool(1);

        firstExpectedCity = new City(1, "Minsk", new Country(1, "Belarus"));
        secondExpectedCity = new City(2, "Moscow", new Country(2, "Russia"));
        thirdExpectedCity = new City(3, "Gomel", new Country(1, "Belarus"));
        fourthExpectedCity = new City(4, "Smolensk", new Country(2, "Russia"));

        cityDao = new NNSCityDao();
        cityDao.setPropertiesReader(testPropertiesReader);
    }

    @Test
    public void findAllTest() throws NSSDaoStoreException {
        List<City> expectedCities = new ArrayList<>();
        expectedCities.add(firstExpectedCity);
        expectedCities.add(secondExpectedCity);
        expectedCities.add(thirdExpectedCity);

        Assert.assertEquals(expectedCities, cityDao.findAll());
    }

    @Test
    public void findByIdTest() throws NSSDaoStoreException {
        Assert.assertEquals(firstExpectedCity, cityDao.findById(1).get());
    }

    @Test
    public void addTest() throws NSSDaoStoreException {
        Assert.assertTrue(cityDao.add(fourthExpectedCity));
    }

    @Test
    public void removeTest() throws NSSDaoStoreException {
        Assert.assertTrue(cityDao.remove(3));
    }

    @Test
    public void updateTest() throws NSSDaoStoreException {
        Assert.assertTrue(cityDao.update(firstExpectedCity));
    }
}
