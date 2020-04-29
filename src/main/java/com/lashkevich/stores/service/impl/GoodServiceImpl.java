package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.dao.impl.GoodDaoImpl;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.exception.ServiceStoreException;
import com.lashkevich.stores.service.GoodService;
import com.lashkevich.stores.util.validator.GoodValidator;

import java.util.List;

public class GoodServiceImpl implements GoodService {
    private GoodDao goodDao;

    @Override
    public GoodDao getGoodDao() {
        return goodDao;
    }

    @Override
    public void setGoodDao(GoodDao goodDao) {
        this.goodDao = goodDao;
    }

    public GoodServiceImpl() {
        goodDao = new GoodDaoImpl();
    }

    @Override
    public boolean addGood(Good good) throws ServiceStoreException {
        try {
            if (GoodValidator.validate(good)) {
                return goodDao.add(good);
            }

            return false;
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public List<Good> findAllGoods() throws ServiceStoreException {
        try {
            return goodDao.findAll();
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public Good findGoodById(String id) throws ServiceStoreException {
        try {
            return goodDao.findById(Long.parseLong(id));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public boolean updateGood(Good good) throws ServiceStoreException {
        try {
            if (GoodValidator.validate(good)) {
                return goodDao.update(good);
            }

            return false;
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public boolean removeGood(String id) throws ServiceStoreException {
        try {
            return goodDao.remove(Long.parseLong(id));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }
}
