package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.CityDao;
import com.lashkevich.stores.dao.CountryDao;
import com.lashkevich.stores.entity.City;
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

public class CityServiceTest {
    private CityService cityService;
    private CityDao cityDao;
    private CountryDao countryDao;
    private City testCity;
    private Country testCountry;

    @Before
    public void setUp() {
        cityService = new CityService();
        cityDao = mock(CityDao.class);
        countryDao = mock(CountryDao.class);
        cityService.setCityDao(cityDao);
        cityService.setCountryDao(countryDao);
        testCountry = new Country(1, "Belarus");
        testCity = new City(1, "Minsk", new Country(1, "Belarus"));
    }

    @Test
    public void addCityPositiveTest() throws DaoStoreException, ServiceStoreException {
        List<Country> countries = new ArrayList<>();
        countries.add(testCountry);
        when(cityDao.add(testCity)).thenReturn(true);
        when(countryDao.findAll()).thenReturn(countries);
        Assert.assertTrue(cityService.addCity(testCity));
    }

    @Test
    public void addCityNegativeTest() throws DaoStoreException, ServiceStoreException {
        testCountry.setName("Poland");
        List<Country> countries = new ArrayList<>();
        countries.add(testCountry);
        when(cityDao.add(testCity)).thenReturn(true);
        when(countryDao.findAll()).thenReturn(countries);
        Assert.assertFalse(cityService.addCity(testCity));
    }

    @Test
    public void findAllCitiesTest() throws DaoStoreException {
        List<City> cities = new ArrayList<>();
        cities.add(testCity);
        when(cityDao.findAll()).thenReturn(cities);
        Assert.assertEquals(cityDao.findAll(), cities);
    }

    @Test
    public void findCityByIdTest() throws DaoStoreException, ServiceStoreException {
        when(cityDao.findById(1)).thenReturn(testCity);
        Assert.assertEquals(cityService.findCityById("1"), testCity);
    }

    @Test (expected = ServiceStoreException.class)
    public void findCityByIdWithInvalidCityIdTest() throws DaoStoreException, ServiceStoreException {
        cityService.findCityById("dgd");
}

    @Test
    public void removeCityPositiveTest() throws DaoStoreException, ServiceStoreException {
        when(cityDao.remove(1)).thenReturn(true);
        Assert.assertTrue(cityService.removeCity("1"));
    }

    @Test (expected = ServiceStoreException.class)
    public void removeCityWithInvalidRemoveTest() throws DaoStoreException, ServiceStoreException {
        cityService.removeCity("egd");
    }

    @Test
    public void updateCityPositiveTest() throws DaoStoreException, ServiceStoreException {
        List<Country> countries = new ArrayList<>();
        countries.add(testCountry);
        when(cityDao.update(testCity)).thenReturn(true);
        when(countryDao.findAll()).thenReturn(countries);
        Assert.assertTrue(cityService.updateCity(testCity));
    }

    @Test
    public void updateCityNegativeTest() throws DaoStoreException, ServiceStoreException {
        List<Country> countries = new ArrayList<>();
        countries.add(testCountry);
        testCity.getCountry().setName("Russia");
        when(cityDao.update(testCity)).thenReturn(true);
        when(countryDao.findAll()).thenReturn(countries);
        Assert.assertFalse(cityService.updateCity(testCity));
    }
}
