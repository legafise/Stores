package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.BasketDao;
import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.dao.UserDao;
import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.exception.ServiceStoreException;

import java.util.List;

public interface BasketService {
    BasketDao getBasketDao();

    void setBasketDao(BasketDao basketDao);

    GoodDao getGoodDao();

    void setGoodDao(GoodDao goodDao);

    UserDao getUserDao();

    void setUserDao(UserDao userDao);

    boolean addBasket(Basket basket, String userId) throws ServiceStoreException;

    List<Basket> findAllBaskets() throws ServiceStoreException;

    List<Basket> findBasketByUserId(String userId) throws ServiceStoreException;

    boolean removeBasket(String id) throws ServiceStoreException;

    boolean updateBasket(Basket basket, String userId) throws ServiceStoreException;
}
