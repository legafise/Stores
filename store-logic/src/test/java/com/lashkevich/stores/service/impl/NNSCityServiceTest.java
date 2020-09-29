package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.CityDao;
import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.entity.Currency;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.service.CityService;
import com.lashkevich.stores.service.CountryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NNSCityServiceTest {
    private CityService cityService;
    private CityDao cityDao;
    private CountryService countryService;
    private City testCity;
    private Optional<City> testCityOptional;
    private Country testCountry;

    @Before
    public void setUp() {
        cityService = new NNSCityService();
        cityDao = mock(CityDao.class);
        countryService = mock(CountryService.class);
        cityService.setCityDao(cityDao);
        cityService.setCountryService(countryService);
        testCountry = new Country(1, "Belarus", new Currency(2, "Belarusian ruble", new BigDecimal("2.6"), "BYN"));
        testCity = new City(1, "Minsk", new Country(1, "Belarus", new Currency(2, "Belarusian ruble", new BigDecimal("2.6"), "BYN")));
        testCityOptional = Optional.of(testCity);
    }

    @Test
    public void addCityPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        List<Country> countries = new ArrayList<>();
        countries.add(testCountry);
        when(cityDao.add(testCity)).thenReturn(true);
        when(countryService.findAllCountries()).thenReturn(countries);
        Assert.assertTrue(cityService.addCity(testCity));
    }

    @Test
    public void addCityNegativeTest() throws NSSDaoStoreException, NNSServiceStoreException {
        testCountry.setName("Poland");
        List<Country> countries = new ArrayList<>();
        countries.add(testCountry);
        when(cityDao.add(testCity)).thenReturn(true);
        when(countryService.findAllCountries()).thenReturn(countries);
        Assert.assertFalse(cityService.addCity(testCity));
    }

    @Test
    public void findAllCitiesTest() throws NSSDaoStoreException {
        List<City> cities = new ArrayList<>();
        cities.add(testCity);
        when(cityDao.findAll()).thenReturn(cities);
        Assert.assertEquals(cityDao.findAll(), cities);
    }

    @Test
    public void findCityByIdTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(cityDao.findById(1)).thenReturn(testCityOptional);
        Assert.assertEquals(cityService.findCityById("1"), testCity);
    }

    @Test (expected = NNSServiceStoreException.class)
    public void findCityByIdWithInvalidCityIdTest() throws NSSDaoStoreException, NNSServiceStoreException {
        cityService.findCityById("dgd");
}

    @Test
    public void removeCityPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(cityDao.remove(1)).thenReturn(true);
        Assert.assertTrue(cityService.removeCity("1"));
    }

    @Test (expected = NNSServiceStoreException.class)
    public void removeCityWithInvalidRemoveTest() throws NNSServiceStoreException {
        cityService.removeCity("egd");
    }

    @Test
    public void updateCityPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        List<Country> countries = new ArrayList<>();
        countries.add(testCountry);
        when(cityDao.update(testCity)).thenReturn(true);
        when(countryService.findAllCountries()).thenReturn(countries);
        Assert.assertTrue(cityService.updateCity(testCity));
    }

    @Test
    public void updateCityNegativeTest() throws NSSDaoStoreException, NNSServiceStoreException {
        List<Country> countries = new ArrayList<>();
        countries.add(testCountry);
        testCity.getCountry().setName("Russia");
        when(cityDao.update(testCity)).thenReturn(true);
        when(countryService.findAllCountries()).thenReturn(countries);
        Assert.assertFalse(cityService.updateCity(testCity));
    }
}
