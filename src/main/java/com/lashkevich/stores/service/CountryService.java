package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.CountryDao;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.exception.ServiceStoreException;

import java.util.List;

public interface CountryService {

    CountryDao getCountryDao();

    void setCountryDao(CountryDao countryDao);

    boolean addCountry(Country country) throws ServiceStoreException;

    List<Country> findAllCountries() throws ServiceStoreException;

    Country findCountryById(String id) throws ServiceStoreException;

    boolean removeCountry(String id) throws ServiceStoreException;

    boolean updateCountry(Country country) throws ServiceStoreException;
}
