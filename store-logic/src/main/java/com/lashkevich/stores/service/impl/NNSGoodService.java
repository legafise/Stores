package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.dao.impl.NNSGoodDao;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.service.GoodService;
import com.lashkevich.stores.util.validator.NNSGoodValidator;

import java.util.List;
import java.util.Optional;

public class NNSGoodService implements GoodService {
    private GoodDao goodDao;

    @Override
    public GoodDao getGoodDao() {
        return goodDao;
    }

    @Override
    public void setGoodDao(GoodDao goodDao) {
        this.goodDao = goodDao;
    }

    public NNSGoodService() {
        goodDao = new NNSGoodDao();
    }

    @Override
    public boolean addGood(Good good) throws NNSServiceStoreException {
        try {
            if (NNSGoodValidator.validate(good)) {
                return goodDao.add(good);
            }

            return false;
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public List<Good> findAllGoods() throws NNSServiceStoreException {
        try {
            return goodDao.findAll();
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public Good findGoodById(String id) throws NNSServiceStoreException {
        try {
            Optional<Good> goodOptional = goodDao.findById(Long.parseLong(id));
            if (!goodOptional.isPresent()) {
                throw new NNSServiceStoreException();
            }

            return goodOptional.get();
        } catch (NumberFormatException | NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public boolean updateGood(Good good) throws NNSServiceStoreException {
        try {
            if (NNSGoodValidator.validate(good)) {
                return goodDao.update(good);
            }

            return false;
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public boolean removeGood(String id) throws NNSServiceStoreException {
        try {
            return goodDao.remove(Long.parseLong(id));
        } catch (NSSDaoStoreException | NumberFormatException e) {
            throw new NNSServiceStoreException(e);
        }
    }
}
