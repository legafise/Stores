package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.CurrencyDao;
import com.lashkevich.stores.dao.impl.NNSCurrencyDao;
import com.lashkevich.stores.entity.Currency;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.service.CurrencyService;
import com.lashkevich.stores.util.checker.NNSCurrencyDuplicationsChecker;
import com.lashkevich.stores.util.validator.NNSCurrencyValidator;

import java.util.List;
import java.util.Optional;

public class NNSCurrencyService implements CurrencyService {
    private static final String CURRENCY_ID_DOES_NOT_EXIST_ERROR_MESSAGE = "Currency with id = %s doesn't exist";
    private static final String CURRENCY_SYMBOL_DOES_NOT_EXIST_ERROR_MESSAGE = "Currency with symbol = %s doesn't exist";
    private static final String STANDARD_CURRENCY_NAME = "USD";

    private CurrencyDao currencyDao;

    public NNSCurrencyService() {
        currencyDao = new NNSCurrencyDao();
    }

    @Override
    public CurrencyDao getCurrencyDao() {
        return currencyDao;
    }

    @Override
    public void setCurrencyDao(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    @Override
    public boolean addCurrency(Currency currency) throws NNSServiceStoreException {
        try {
            if (NNSCurrencyValidator.validateCurrency(currency) && NNSCurrencyDuplicationsChecker.checkCurrencyAdding(currency, currencyDao.findAll())) {
                return currencyDao.add(currency);
            }

            return false;
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public List<Currency> findAllCurrencies() throws NNSServiceStoreException {
        try {
            return currencyDao.findAll();
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public Currency findCurrencyById(String id) throws NNSServiceStoreException {
        try {
            Optional<Currency> currencyOptional = currencyDao.findById(Long.parseLong(id));
            if (!currencyOptional.isPresent()) {
                throw new NNSServiceStoreException(String.format(CURRENCY_ID_DOES_NOT_EXIST_ERROR_MESSAGE, id));
            }

            return currencyOptional.get();
        } catch (NumberFormatException | NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public Currency findCurrencyByName(String name) throws NNSServiceStoreException {
        try {
            Optional<Currency> currencyOptional = currencyDao.findByName(name);
            if (!currencyOptional.isPresent()) {
                throw new NNSServiceStoreException(String.format(CURRENCY_SYMBOL_DOES_NOT_EXIST_ERROR_MESSAGE, name));
            }

            return currencyOptional.get();
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public boolean removeCurrency(String id) throws NNSServiceStoreException {
        try {
            return currencyDao.remove(Long.parseLong(id));
        } catch (NSSDaoStoreException | NumberFormatException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public boolean updateCurrency(Currency currency) throws NNSServiceStoreException {
        try {
            if (NNSCurrencyValidator.validateCurrency(currency) && NNSCurrencyDuplicationsChecker.checkCurrencyUpdating(currency, currencyDao.findAll())) {
                return currencyDao.update(currency);
            }

            return false;
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public String findStandardCurrencyId() throws NNSServiceStoreException {
        return String.valueOf(findCurrencyByName(STANDARD_CURRENCY_NAME).getId());
    }
}
