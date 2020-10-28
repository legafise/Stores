package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.NNSServiceStoreException;

import java.util.List;

public interface GoodService {

    GoodDao getGoodDao();

    void setGoodDao(GoodDao goodDao);

    CurrencyService getCurrencyService();

    boolean addGood(Good good) throws NNSServiceStoreException;

    List<Good> findAllGoods(String currencyId) throws NNSServiceStoreException;

    Good findGoodById(String goodId, String currencyId) throws NNSServiceStoreException;

    boolean updateGood(Good good) throws NNSServiceStoreException;

    boolean removeGood(String id) throws NNSServiceStoreException;

}
