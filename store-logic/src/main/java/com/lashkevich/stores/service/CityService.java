package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.CityDao;
import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.exception.NNSServiceStoreException;

import java.util.List;

public interface CityService {

    CityDao getCityDao();

    void setCityDao(CityDao cityDao);

    CountryService getCountryService();

    void setCountryService(CountryService countryService);

    boolean addCity(City city) throws NNSServiceStoreException;

    List<City> findAllCities() throws NNSServiceStoreException;

    City findCityById(String id) throws NNSServiceStoreException;

    boolean removeCity(String id) throws NNSServiceStoreException;

    boolean updateCity(City city) throws NNSServiceStoreException;
}
