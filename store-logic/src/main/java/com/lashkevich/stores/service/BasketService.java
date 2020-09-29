package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.BasketDao;
import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.exception.NNSServiceStoreException;

import java.util.List;

public interface BasketService {
    BasketDao getBasketDao();

    void setBasketDao(BasketDao basketDao);

    GoodService getGoodService();

    void setGoodService(GoodService goodService);

    void setCurrencyService(CurrencyService currencyService);

    UserService getUserService();

    void setUserService(UserService userService);

    boolean addBasket(Basket basket, String userId) throws NNSServiceStoreException;

    List<Basket> findAllBaskets(String currencyId) throws NNSServiceStoreException;

    Basket findBasketByUserId(String userId, String currencyId) throws NNSServiceStoreException;

    boolean removeBasket(String id) throws NNSServiceStoreException;

    boolean updateBasket(Basket basket, String userId) throws NNSServiceStoreException;
}
