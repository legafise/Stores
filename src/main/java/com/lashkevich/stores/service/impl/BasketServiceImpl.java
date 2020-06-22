package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.BasketDao;
import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.dao.UserDao;
import com.lashkevich.stores.dao.impl.BasketDaoImpl;
import com.lashkevich.stores.dao.impl.GoodDaoImpl;
import com.lashkevich.stores.dao.impl.UserDaoImpl;
import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.entity.User;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.exception.ServiceStoreException;
import com.lashkevich.stores.service.BasketService;
import com.lashkevich.stores.util.checker.BasketDuplicationsChecker;
import com.lashkevich.stores.util.validator.BasketValidator;
import com.lashkevich.stores.util.validator.GoodValidator;

import java.util.List;
import java.util.Map;

public class BasketServiceImpl implements BasketService {
    private BasketDao basketDao;
    private GoodDao goodDao;
    private UserDao userDao;

    public BasketServiceImpl() {
        basketDao = new BasketDaoImpl();
        goodDao = new GoodDaoImpl();
        userDao = new UserDaoImpl();
    }

    @Override
    public BasketDao getBasketDao() {
        return basketDao;
    }

    @Override
    public void setBasketDao(BasketDao basketDao) {
        this.basketDao = basketDao;
    }

    @Override
    public GoodDao getGoodDao() {
        return goodDao;
    }

    @Override
    public void setGoodDao(GoodDao goodDao) {
        this.goodDao = goodDao;
    }

    @Override
    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean addBasket(Basket basket, String userId) throws ServiceStoreException {
        try {
            if (BasketValidator.validate(basket) && BasketDuplicationsChecker.check(basket, basketDao.findByUser(Long.parseLong(userId))) &&
                    isValidGood(basket.getGoods()) && isGoodCreated(basket, goodDao.findAll()) && isUserCreated(Long.parseLong(userId), userDao.findAll())) {
                return basketDao.add(basket, Long.parseLong(userId));
            }

            return false;
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public List<Basket> findAllBaskets() throws ServiceStoreException {
        try {
            return basketDao.findAll();
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public List<Basket> findBasketByUserId(String userId) throws ServiceStoreException {
        try {
            return basketDao.findByUser(Integer.parseInt(userId));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public boolean removeBasket(String id) throws ServiceStoreException {
        try {
            return basketDao.remove(Long.parseLong(id));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public boolean updateBasket(Basket basket, String userId) throws ServiceStoreException {
        try {
            if (BasketValidator.validate(basket) && isUserCreated(Long.parseLong(userId), userDao.findAll()) &&
                    isValidGood(basket.getGoods()) && isGoodCreated(basket, goodDao.findAll())) {
                return basketDao.update(basket, Long.parseLong(userId));
            }

            return false;
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    private static boolean isGoodCreated(Basket basket, List<Good> goodList) {
        for (Map.Entry<Good, Integer> good : basket.getGoods().entrySet()) {
            if (!goodList.contains(good.getKey())) {
                return false;
            }
        }

        return true;
    }

    private static boolean isValidGood(Map<Good, Integer> goods) {
        for (Map.Entry<Good, Integer> good : goods.entrySet()) {
            if (!GoodValidator.validate(good.getKey()) || good.getValue() == 0) {
                return false;
            }
        }

        return true;
    }

    private static boolean isUserCreated(Long userId, List<User> userList) {
        if (userId == null) {
            return false;
        }

        for (User users : userList) {
            if (userId == users.getId()) {
                return true;
            }
        }

        return false;
    }
}

