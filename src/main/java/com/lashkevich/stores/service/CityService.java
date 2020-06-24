package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.CityDao;
import com.lashkevich.stores.dao.CountryDao;
import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.exception.ServiceStoreException;

import java.util.List;

public interface CityService {

    CityDao getCityDao();

    void setCityDao(CityDao cityDao);

    CountryDao getCountryDao();

    void setCountryDao(CountryDao countryDao);

    boolean addCity(City city) throws ServiceStoreException;

    List<City> findAllCities() throws ServiceStoreException;

    City findCityById(String id) throws ServiceStoreException;

    boolean removeCity(String id) throws ServiceStoreException;

    boolean updateCity(City city) throws ServiceStoreException;
}
