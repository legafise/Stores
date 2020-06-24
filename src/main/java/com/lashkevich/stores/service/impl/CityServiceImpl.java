package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.CityDao;
import com.lashkevich.stores.dao.CountryDao;
import com.lashkevich.stores.dao.impl.CityDaoImpl;
import com.lashkevich.stores.dao.impl.CountryDaoImpl;
import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.exception.ServiceStoreException;
import com.lashkevich.stores.service.CityService;
import com.lashkevich.stores.util.validator.CityValidator;

import java.util.List;

public class CityServiceImpl implements CityService {
    private CityDao cityDao;
    private CountryDao countryDao;

    public CityServiceImpl() {
        cityDao = new CityDaoImpl();
        countryDao = new CountryDaoImpl();
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
    public CountryDao getCountryDao() {
        return countryDao;
    }

    @Override
    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public boolean addCity(City city) throws ServiceStoreException {
        try {
            if (CityValidator.validate(city) && countryDao.findAll().contains(city.getCountry())) {
                return cityDao.add(city);
            }

            return false;
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public List<City> findAllCities() throws ServiceStoreException {
        try {
            return cityDao.findAll();
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public City findCityById(String id) throws ServiceStoreException {
        try {
            return cityDao.findById(Long.parseLong(id));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public boolean removeCity(String id) throws ServiceStoreException {
        try {
            return cityDao.remove(Long.parseLong(id));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public boolean updateCity(City city) throws ServiceStoreException {
        try {
            if (CityValidator.validate(city) && countryDao.findAll().contains(city.getCountry())) {
                return cityDao.update(city);
            }

            return false;
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }
}
