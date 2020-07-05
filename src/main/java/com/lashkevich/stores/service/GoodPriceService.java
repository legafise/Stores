package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.GoodPriceDao;
import com.lashkevich.stores.entity.GoodPrice;
import com.lashkevich.stores.exception.NNSServiceStoreException;

import java.util.List;

public interface GoodPriceService {

    GoodPriceDao getGoodPriceDao();

    void setGoodPriceDao(GoodPriceDao goodPriceDao);

    GoodService getGoodService();

    void setGoodService(GoodService goodService);

    CountryService getCountryService();

    void setCountryService(CountryService countryService);

    boolean addGoodPrice(GoodPrice goodPrice) throws NNSServiceStoreException;

    List<GoodPrice> findAllGoodPrices() throws NNSServiceStoreException;

    GoodPrice findGoodPriceByCountryAndGoodId(String countryId, String goodId) throws NNSServiceStoreException;

    boolean removeGoodPriceByCountryAndGoodId(String countryId, String goodId) throws NNSServiceStoreException;

    boolean updateGoodPriceByCountryAndGoodId(String countryId, String goodId, GoodPrice goodPrice) throws NNSServiceStoreException;
}
