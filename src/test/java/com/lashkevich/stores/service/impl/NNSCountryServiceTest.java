package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.CountryDao;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.service.CountryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class NNSCountryServiceTest {
    private CountryService countryService;
    private CountryDao countryDao;
    private Country firstTestCountry;
    private Optional <Country> firstTestCountryOptional;
    private Country firstChangeTestCountry;
    private Country secondTestCountry;

    @Before
    public void setUp() {
        countryService = new NNSCountryService();
        countryDao = mock(CountryDao.class);
        countryService.setCountryDao(countryDao);
        firstTestCountry = new Country(1, "Belarus");
        firstTestCountryOptional = Optional.of(firstTestCountry);
        firstChangeTestCountry = new Country(1, "Ukraine");
        secondTestCountry = new Country(2, "Russia");
    }

    @Test
    public void addCountryPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        List<Country> countries = new ArrayList<>();
        countries.add(secondTestCountry);
        when(countryDao.findAll()).thenReturn(countries);
        when(countryDao.add(firstTestCountry)).thenReturn(true);
        Assert.assertTrue(countryService.addCountry(firstTestCountry));
    }

    @Test
    public void addCountryNegativeTest() throws NSSDaoStoreException, NNSServiceStoreException {
        firstTestCountry.setName("Russia");
        List<Country> countries = new ArrayList<>();
        countries.add(firstTestCountry);
        when(countryDao.findAll()).thenReturn(countries);
        when(countryDao.add(secondTestCountry)).thenReturn(true);
        Assert.assertFalse(countryService.addCountry(secondTestCountry));
    }

    @Test
    public void findAllCountries() throws NSSDaoStoreException, NNSServiceStoreException {
        List<Country> countries = new ArrayList<>();
        countries.add(firstTestCountry);
        countries.add(secondTestCountry);
        when(countryDao.findAll()).thenReturn(countries);
        Assert.assertEquals(countries, countryService.findAllCountries());
    }

    @Test
    public void findCountryByIdPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(countryDao.findById(1)).thenReturn(firstTestCountryOptional);
        Assert.assertEquals(firstTestCountry, countryService.findCountryById("1"));
    }

    @Test (expected = NNSServiceStoreException.class)
    public void findCountryByIdWithInvalidCountryIdTest() throws NNSServiceStoreException {
        countryService.findCountryById("firstId");
    }

    @Test
    public void removeCountryPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(countryDao.remove(1)).thenReturn(true);
        Assert.assertTrue(countryService.removeCountry("1"));
    }

    @Test (expected = NNSServiceStoreException.class)
    public void removeCountryWithInvalidCountryIdTest() throws NNSServiceStoreException {
        countryService.removeCountry("Удали Пендосию к чертям собачьим!");
    }

    @Test
    public void updateCountryPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        List<Country> countries = new ArrayList<>();
        countries.add(secondTestCountry);
        countries.add(firstTestCountry);
        when(countryDao.findAll()).thenReturn(countries);
        when(countryDao.update(firstChangeTestCountry)).thenReturn(true);
        Assert.assertTrue(countryService.updateCountry(firstChangeTestCountry));
    }

    @Test
    public void updateCountryNegativeTest() throws NSSDaoStoreException, NNSServiceStoreException {
        List<Country> countries = new ArrayList<>();
        countries.add(secondTestCountry);
        countries.add(firstTestCountry);
        when(countryDao.findAll()).thenReturn(countries);
        firstChangeTestCountry.setName("Russia");
        when(countryDao.update(firstChangeTestCountry)).thenReturn(true);
        Assert.assertFalse(countryService.updateCountry(firstChangeTestCountry));
    }
}
