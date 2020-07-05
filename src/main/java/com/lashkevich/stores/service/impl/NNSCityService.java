package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.CityDao;
import com.lashkevich.stores.dao.impl.NNSCityDao;
import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.service.CityService;
import com.lashkevich.stores.service.CountryService;
import com.lashkevich.stores.util.validator.NNSCityValidator;

import java.util.List;
import java.util.Optional;

public class NNSCityService implements CityService {
    private CityDao cityDao;
    private CountryService countryService;

    public NNSCityService() {
        cityDao = new NNSCityDao();
        countryService = new NNSCountryService();
    }

    @Override
    public CityDao getCityDao() {
        return cityDao;
    }

    @Override
    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public CountryService getCountryService() {
        return countryService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public boolean addCity(City city) throws NNSServiceStoreException {
        try {
            if (NNSCityValidator.validate(city) && countryService.findAllCountries().contains(city.getCountry())) {
                return cityDao.add(city);
            }

            return false;
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public List<City> findAllCities() throws NNSServiceStoreException {
        try {
            return cityDao.findAll();
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public City findCityById(String id) throws NNSServiceStoreException {
        try {
            Optional<City> cityOptional = cityDao.findById(Long.parseLong(id));
            if (!cityOptional.isPresent()) {
                throw new NNSServiceStoreException();
            }

            return cityOptional.get();
        } catch (NumberFormatException | NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public boolean removeCity(String id) throws NNSServiceStoreException {
        try {
            return cityDao.remove(Long.parseLong(id));
        } catch (NSSDaoStoreException | NumberFormatException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public boolean updateCity(City city) throws NNSServiceStoreException {
        try {
            if (NNSCityValidator.validate(city) && countryService.findAllCountries().contains(city.getCountry())) {
                return cityDao.update(city);
            }

            return false;
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }
}
