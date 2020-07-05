package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.CountryDao;
import com.lashkevich.stores.dao.impl.NNSCountryDao;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.service.CountryService;
import com.lashkevich.stores.util.checker.NNSCountryDuplicationsChecker;
import com.lashkevich.stores.util.validator.NNSCountryValidator;

import java.util.List;
import java.util.Optional;

public class NNSCountryService implements CountryService {
    private CountryDao countryDao;

    public NNSCountryService() {
        countryDao = new NNSCountryDao();
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
    public boolean addCountry(Country country) throws NNSServiceStoreException {
        try {
            if (NNSCountryValidator.validate(country) && NNSCountryDuplicationsChecker.check(country, countryDao.findAll())) {
                return countryDao.add(country);
            }

            return false;
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public List<Country> findAllCountries() throws NNSServiceStoreException {
        try {
            return countryDao.findAll();
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public Country findCountryById(String id) throws NNSServiceStoreException {
        try {
            Optional<Country> countryOptional = countryDao.findById(Long.parseLong(id));
            if (!countryOptional.isPresent()) {
                throw new NNSServiceStoreException();
            }

            return countryOptional.get();
        } catch (NumberFormatException | NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public boolean removeCountry(String id) throws NNSServiceStoreException {
        try {
            return countryDao.remove(Long.parseLong(id));
        } catch (NSSDaoStoreException | NumberFormatException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public boolean updateCountry(Country country) throws NNSServiceStoreException {
        try {
            if (NNSCountryValidator.validate(country) && NNSCountryDuplicationsChecker.check(country, countryDao.findAll())) {
                return countryDao.update(country);
            }

            return false;
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }
}
