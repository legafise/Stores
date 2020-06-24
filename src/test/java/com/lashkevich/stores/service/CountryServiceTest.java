package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.CountryDao;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.exception.ServiceStoreException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CountryServiceTest {
    private CountryService countryService;
    private CountryDao countryDao;
    private Country firstTestCountry;
    private Country firstChangeTestCountry;
    private Country secondTestCountry;

    @Before
    public void setUp() {
        countryService = new CountryService();
        countryDao = mock(CountryDao.class);
        countryService.setCountryDao(countryDao);
        firstTestCountry = new Country(1, "Belarus");
        firstChangeTestCountry = new Country(1, "Ukraine");
        secondTestCountry = new Country(2, "Russia");
    }

    @Test
    public void addCountryPositiveTest() throws DaoStoreException, ServiceStoreException {
        List<Country> countries = new ArrayList<>();
        countries.add(secondTestCountry);
        when(countryDao.findAll()).thenReturn(countries);
        when(countryDao.add(firstTestCountry)).thenReturn(true);
        Assert.assertTrue(countryService.addCountry(firstTestCountry));
    }

    @Test
    public void addCountryNegativeTest() throws DaoStoreException, ServiceStoreException {
        firstTestCountry.setName("Russia");
        List<Country> countries = new ArrayList<>();
        countries.add(firstTestCountry);
        when(countryDao.findAll()).thenReturn(countries);
        when(countryDao.add(secondTestCountry)).thenReturn(true);
        Assert.assertFalse(countryService.addCountry(secondTestCountry));
    }

    @Test
    public void findAllCountries() throws DaoStoreException, ServiceStoreException {
        List<Country> countries = new ArrayList<>();
        countries.add(firstTestCountry);
        countries.add(secondTestCountry);
        when(countryDao.findAll()).thenReturn(countries);
        Assert.assertEquals(countries, countryService.findAllCountries());
    }

    @Test
    public void findCountryByIdPositiveTest() throws DaoStoreException, ServiceStoreException {
        when(countryDao.findById(1)).thenReturn(firstTestCountry);
        Assert.assertEquals(firstTestCountry, countryService.findCountryById("1"));
    }

    @Test (expected = ServiceStoreException.class)
    public void findCountryByIdWithInvalidCountryIdTest() throws ServiceStoreException {
        countryService.findCountryById("firstId");
    }

    @Test
    public void removeCountryPositiveTest() throws DaoStoreException, ServiceStoreException {
        when(countryDao.remove(1)).thenReturn(true);
        Assert.assertTrue(countryService.removeCountry("1"));
    }

    @Test (expected = ServiceStoreException.class)
    public void removeCountryWithInvalidCountryIdTest() throws ServiceStoreException {
        countryService.removeCountry("Удали Пендосию к чертям собачьим!");
    }

    @Test
    public void updateCountryPositiveTest() throws DaoStoreException, ServiceStoreException {
        List<Country> countries = new ArrayList<>();
        countries.add(secondTestCountry);
        countries.add(firstTestCountry);
        when(countryDao.findAll()).thenReturn(countries);
        when(countryDao.update(firstChangeTestCountry)).thenReturn(true);
        Assert.assertTrue(countryService.updateCountry(firstChangeTestCountry));
    }

    @Test
    public void updateCountryNegativeTest() throws DaoStoreException, ServiceStoreException {
        List<Country> countries = new ArrayList<>();
        countries.add(secondTestCountry);
        countries.add(firstTestCountry);
        when(countryDao.findAll()).thenReturn(countries);
        firstChangeTestCountry.setName("Russia");
        when(countryDao.update(firstChangeTestCountry)).thenReturn(true);
        Assert.assertFalse(countryService.updateCountry(firstChangeTestCountry));
    }
}
