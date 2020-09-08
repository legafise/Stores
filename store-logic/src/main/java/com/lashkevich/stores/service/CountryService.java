package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.CountryDao;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.exception.NNSServiceStoreException;

import java.util.List;

public interface CountryService {

    CountryDao getCountryDao();

    void setCountryDao(CountryDao countryDao);

    boolean addCountry(Country country) throws NNSServiceStoreException;

    List<Country> findAllCountries() throws NNSServiceStoreException;

    Country findCountryById(String id) throws NNSServiceStoreException;

    boolean removeCountry(String id) throws NNSServiceStoreException;

    boolean updateCountry(Country country) throws NNSServiceStoreException;
}
