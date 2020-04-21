package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.BasketDao;
import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.dao.UserDao;
import com.lashkevich.stores.dao.impl.BasketDaoImpl;
import com.lashkevich.stores.dao.impl.GoodDaoImpl;
import com.lashkevich.stores.dao.impl.UserDaoImpl;
import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.exception.ServiceStoreException;
import com.lashkevich.stores.util.checker.BasketDuplicationsChecker;
import com.lashkevich.stores.util.validator.BasketValidator;

import java.util.List;

public class BasketService {
    private BasketDao basketDao;
    private GoodDao goodDao;
    private UserDao userDao;

    public BasketService() {
        basketDao = new BasketDaoImpl();
        goodDao = new GoodDaoImpl();
        userDao = new UserDaoImpl();
    }

    public BasketDao getBasketDao() {
        return basketDao;
    }

    public void setBasketDao(BasketDao basketDao) {
        this.basketDao = basketDao;
    }

    public GoodDao getGoodDao() {
        return goodDao;
    }

    public void setGoodDao(GoodDao goodDao) {
        this.goodDao = goodDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean addBasket(Basket basket, String userId) throws ServiceStoreException {
        try {
            if (BasketValidator.validate(basket, goodDao.findAll(), Integer.parseInt(userId), userDao.findAll()) &&
                    BasketDuplicationsChecker.check(basket, basketDao.findByUser(Long.parseLong(userId)))) {
                return basketDao.add(basket, Long.parseLong(userId));
            }

            return false;
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    public List<Basket> findAllBaskets() throws ServiceStoreException {
        try {
            return basketDao.findAll();
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    public List<Basket> findBasketByUserId(String userId) throws ServiceStoreException {
        try {
            return basketDao.findByUser(Integer.parseInt(userId));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    public boolean removeBasket(String id) throws ServiceStoreException {
        try {
            return basketDao.remove(Long.parseLong(id));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    public boolean updateBasket(Basket basket, String userId) throws ServiceStoreException {
        try {
            if (BasketValidator.validate(basket, goodDao.findAll(), Integer.parseInt(userId), userDao.findAll())) {
                return basketDao.update(basket, Integer.parseInt(userId));
            }

            return false;
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }
}
