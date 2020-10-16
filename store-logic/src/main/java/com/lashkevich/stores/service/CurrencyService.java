package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.CurrencyDao;
import com.lashkevich.stores.entity.Currency;
import com.lashkevich.stores.exception.NNSServiceStoreException;

import java.util.List;

public interface CurrencyService {
    CurrencyDao getCurrencyDao();

    void setCurrencyDao(CurrencyDao currencyDao);

    boolean addCurrency(Currency currency) throws NNSServiceStoreException;

    List<Currency> findAllCurrencies() throws NNSServiceStoreException;

    Currency findCurrencyById(String id) throws NNSServiceStoreException;

    Currency findCurrencyByName(String symbol) throws NNSServiceStoreException;

    boolean removeCurrency(String id) throws NNSServiceStoreException;

    boolean updateCurrency(Currency currency) throws NNSServiceStoreException;

    String findStandardCurrencyId() throws NNSServiceStoreException;
}
