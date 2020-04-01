package com.lashkevich.stores.test;

import com.lashkevich.stores.dao.CountryDao;
import com.lashkevich.stores.dao.impl.CoutryDaoImpl;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.util.provider.impl.TestConnectionProviderImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CountryDaoTest {
    private Country firstExpectedCountry;
    private Country secondExpectedCountry;
    private Country thirdExpectedCountry;
    private Country fourthExpectedCountry;
    private Country firstChangeExpectedCountry;
    private CountryDao countryDao;

    @Before
    public void setUp() {
        firstExpectedCountry = new Country(1, "Belarus");
        secondExpectedCountry = new Country(2, "Russia");
        thirdExpectedCountry = new Country(3, "USA");
        fourthExpectedCountry = new Country(4, "Ukraine");
        firstChangeExpectedCountry = new Country(1, "Belarussia");

        countryDao = new CoutryDaoImpl();
        ((CoutryDaoImpl) countryDao).setConnectionProvider(new TestConnectionProviderImpl());
    }

    @Test
    public void findAllTest() throws DaoStoreException {
        List<Country> expectedCountries = new ArrayList<>();
        expectedCountries.add(firstExpectedCountry);
        expectedCountries.add(secondExpectedCountry);
        expectedCountries.add(thirdExpectedCountry);

        Assert.assertEquals(expectedCountries, countryDao.findAll());
    }

    @Test
    public void findByIdTest() throws DaoStoreException {
        Assert.assertEquals(firstExpectedCountry, countryDao.findById(1));
    }

    @Test
    public void addTest() throws DaoStoreException {
        Assert.assertTrue(countryDao.add(fourthExpectedCountry));
    }

    @Test
    public void removeTest() throws DaoStoreException {
        Assert.assertTrue(countryDao.remove(3));
    }

    @Test
    public void updateTest() throws DaoStoreException {
        Assert.assertTrue(countryDao.update(firstChangeExpectedCountry));
    }
}
