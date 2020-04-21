package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.CountryDao;
import com.lashkevich.stores.dao.impl.CountryDaoImpl;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.exception.ServiceStoreException;
import com.lashkevich.stores.util.checker.CountryDuplicationsChecker;
import com.lashkevich.stores.util.validator.CountryValidator;

import java.util.List;

public class CountryService {
    private CountryDao countryDao;

    public CountryService() {
        countryDao = new CountryDaoImpl();
    }

    public CountryDao getCountryDao() {
        return countryDao;
    }

    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public boolean addCountry(Country country) throws ServiceStoreException {
        try {
            if (CountryValidator.validate(country) && CountryDuplicationsChecker.check(country, countryDao.findAll())) {
                return countryDao.add(country);
            }

            return false;
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    public List<Country> findAllCountries() throws ServiceStoreException {
        try {
            return countryDao.findAll();
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    public Country findCountryById(String id) throws ServiceStoreException {
        try {
            return countryDao.findById(Long.parseLong(id));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    public boolean removeCountry(String id) throws ServiceStoreException {
        try {
            return countryDao.remove(Long.parseLong(id));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    public boolean updateCountry(Country country) throws ServiceStoreException {
        try {
            if (CountryValidator.validate(country) && CountryDuplicationsChecker.check(country, countryDao.findAll())) {
                return countryDao.update(country);
            }

            return false;
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }
}
