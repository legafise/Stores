package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.ServiceStoreException;

import java.util.List;

public interface GoodService {

    GoodDao getGoodDao();

    void setGoodDao(GoodDao goodDao);

    boolean addGood(Good good) throws ServiceStoreException;

    List<Good> findAllGoods() throws ServiceStoreException;

    Good findGoodById(String id) throws ServiceStoreException;

    boolean updateGood(Good good) throws ServiceStoreException;

    boolean removeGood(String id) throws ServiceStoreException;

}
