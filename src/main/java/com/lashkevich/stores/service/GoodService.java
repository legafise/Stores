package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.dao.impl.GoodDaoImpl;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.exception.ServiceStoreException;
import com.lashkevich.stores.util.validator.GoodValidator;

import java.util.List;

public class GoodService {
    private GoodDao goodDao;

    public GoodDao getGoodDao() {
        return goodDao;
    }

    public void setGoodDao(GoodDao goodDao) {
        this.goodDao = goodDao;
    }

    public GoodService() {
        goodDao = new GoodDaoImpl();
    }

    public boolean addGood(Good good) throws ServiceStoreException {
        try {
            if (GoodValidator.validateGood(good)) {
                return goodDao.add(good);
            }

            return false;
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    public List<Good> findAllGoods() throws ServiceStoreException {
        try {
            return goodDao.findAll();
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    public Good findGoodById(String id) throws ServiceStoreException {
        try {
            return goodDao.findById(Long.parseLong(id));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    public boolean updateGood(Good good) throws ServiceStoreException {
        try {
            if (GoodValidator.validateGood(good)) {
                return goodDao.update(good);
            }

            return false;
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    public boolean removeGood(String id) throws ServiceStoreException {
        try {
            return goodDao.remove(Long.parseLong(id));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }
}
