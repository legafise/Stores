package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.CountryDao;
import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.dao.GoodPriceDao;
import com.lashkevich.stores.entity.GoodPrice;
import com.lashkevich.stores.exception.ServiceStoreException;

import java.util.List;

public interface GoodPriceService {

    GoodPriceDao getGoodPriceDao();

    void setGoodPriceDao(GoodPriceDao goodPriceDao);

    GoodDao getGoodDao();

    void setGoodDao(GoodDao goodDao);

    CountryDao getCountryDao();

    void setCountryDao(CountryDao countryDao);

    boolean addGoodPrice(GoodPrice goodPrice) throws ServiceStoreException;

    List<GoodPrice> findAllGoodPrices() throws ServiceStoreException ;

    GoodPrice findGoodPriceByCountryAndGoodId(String countryId, String goodId) throws ServiceStoreException;

    boolean removeGoodPriceByCountryAndGoodId(String countryId, String goodId) throws ServiceStoreException;

    boolean updateGoodPriceByCountryAndGoodId(String countryId, String goodId, GoodPrice goodPrice) throws ServiceStoreException;
}
