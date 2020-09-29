package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.dao.impl.NNSGoodDao;
import com.lashkevich.stores.entity.Currency;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.service.CurrencyService;
import com.lashkevich.stores.service.GoodService;
import com.lashkevich.stores.util.converter.NNSGoodPriceConverter;
import com.lashkevich.stores.util.validator.NNSGoodValidator;

import java.util.List;
import java.util.Optional;

public class NNSGoodService implements GoodService {
    private static final String STANDARD_IMG = "https://www.everythingvacuums.com/mm5/graphics/00000001/iStock-922962354.jpg";

    private GoodDao goodDao;
    private CurrencyService currencyService;

    @Override
    public GoodDao getGoodDao() {
        return goodDao;
    }

    @Override
    public void setGoodDao(GoodDao goodDao) {
        this.goodDao = goodDao;
    }

    @Override
    public CurrencyService getCurrencyService() {
        return currencyService;
    }

    public NNSGoodService() {
        goodDao = new NNSGoodDao();
        currencyService = new NNSCurrencyService();
    }

    @Override
    public boolean addGood(Good good) throws NNSServiceStoreException {
        try {
            if (good.getImgURL() == null) {
                good.setImgURL(STANDARD_IMG);
            }

            if (NNSGoodValidator.validate(good)) {
                return goodDao.add(good);
            }

            return false;
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public List<Good> findAllGoods(String currencyId) throws NNSServiceStoreException {
        try {
            return convertGoodList(goodDao.findAll(), currencyService.findCurrencyById(currencyId));
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public Good findGoodById(String goodId, String currencyId) throws NNSServiceStoreException {
        try {
            Optional<Good> goodOptional = goodDao.findById(Long.parseLong(goodId));
            if (!goodOptional.isPresent()) {
                throw new NNSServiceStoreException();
            }

            return NNSGoodPriceConverter.convert(goodOptional.get(), currencyService.findCurrencyById(currencyId));
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

    private static List<Good> convertGoodList(List<Good> goods, Currency currency) {
        goods.forEach(currentGood -> currentGood = NNSGoodPriceConverter.convert(currentGood, currency));
        return goods;
    }
}
