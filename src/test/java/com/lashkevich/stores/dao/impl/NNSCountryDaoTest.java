package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.CountryDao;
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

public class NNSCountryDaoTest {
    private Country firstExpectedCountry;
    private Country secondExpectedCountry;
    private Country thirdExpectedCountry;
    private Country fourthExpectedCountry;
    private Country firstChangeExpectedCountry;
    private CountryDao countryDao;

    @Before
    public void setUp() throws NNSConnectionPoolException {
        PropertiesReader testPropertiesReader = new NNSTestPropertiesReader();

        NNSConnectionPool.getInstance().setPropertiesReader(testPropertiesReader);
        NNSConnectionPool.getInstance().initializeConnectionPool(1);

        firstExpectedCountry = new Country(1, "Belarus");
        secondExpectedCountry = new Country(2, "Russia");
        thirdExpectedCountry = new Country(3, "USA");
        fourthExpectedCountry = new Country(4, "Ukraine");
        firstChangeExpectedCountry = new Country(1, "Belarussia");

        countryDao = new NNSCountryDao();
        countryDao.setPropertiesReader(testPropertiesReader);
    }

    @Test
    public void findAllTest() throws NSSDaoStoreException {
        List<Country> expectedCountries = new ArrayList<>();
        expectedCountries.add(firstExpectedCountry);
        expectedCountries.add(secondExpectedCountry);
        expectedCountries.add(thirdExpectedCountry);

        Assert.assertEquals(expectedCountries, countryDao.findAll());
    }

    @Test
    public void findByIdTest() throws NSSDaoStoreException {
        Assert.assertEquals(firstExpectedCountry, countryDao.findById(1).get());
    }

    @Test
    public void addTest() throws NSSDaoStoreException {
        Assert.assertTrue(countryDao.add(fourthExpectedCountry));
    }

    @Test
    public void removeTest() throws NSSDaoStoreException {
        Assert.assertTrue(countryDao.remove(3));
    }

    @Test
    public void updateTest() throws NSSDaoStoreException {
        Assert.assertTrue(countryDao.update(firstChangeExpectedCountry));
    }
}
