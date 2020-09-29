package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.BasketDao;
import com.lashkevich.stores.dao.impl.NNSBasketDao;
import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.entity.Currency;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.entity.User;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.service.BasketService;
import com.lashkevich.stores.service.CurrencyService;
import com.lashkevich.stores.service.GoodService;
import com.lashkevich.stores.service.UserService;
import com.lashkevich.stores.util.checker.NNSBasketDuplicationsChecker;
import com.lashkevich.stores.util.converter.NNSGoodPriceConverter;
import com.lashkevich.stores.util.validator.NNSBasketValidator;
import com.lashkevich.stores.util.validator.NNSGoodValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NNSBasketService implements BasketService {
    private static final String STANDARD_CURRENCY_ID = "1";

    private BasketDao basketDao;
    private GoodService goodService;
    private UserService userService;
    private CurrencyService currencyService;

    public NNSBasketService() {
        basketDao = new NNSBasketDao();
        goodService = new NNSGoodService();
        userService = new NNSUserService();
        currencyService = new NNSCurrencyService();
    }

    @Override
    public BasketDao getBasketDao() {
        return basketDao;
    }

    @Override
    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public void setBasketDao(BasketDao basketDao) {
        this.basketDao = basketDao;
    }

    public CurrencyService getCurrencyService() {
        return currencyService;
    }

    public GoodService getGoodService() {
        return goodService;
    }

    public void setGoodService(GoodService goodService) {
        this.goodService = goodService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean addBasket(Basket basket, String userId) throws NNSServiceStoreException {
        try {
            boolean isValidBasket = NNSBasketValidator.validate(basket);
            boolean isDuplicateBasket = NNSBasketDuplicationsChecker.check(basket, findBasketByUserId(userId, STANDARD_CURRENCY_ID));

            if (isValidBasket && isDuplicateBasket && isValidGood(basket.getGoods()) && isGoodCreated(basket, goodService.findAllGoods("1")) &&
                    isUserCreated(Long.parseLong(userId), userService.findAllUsers())) {
                return basketDao.add(basket, Long.parseLong(userId));
            }

            return false;
        } catch (NSSDaoStoreException | NumberFormatException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public List<Basket> findAllBaskets(String currencyId) throws NNSServiceStoreException {
        try {
            return convertGoodPriceInBasketList(basketDao.findAll(), currencyService.findCurrencyById(currencyId));
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public Basket findBasketByUserId(String userId, String currencyId) throws NNSServiceStoreException {
        try {
            Optional<Basket> basketOptional = basketDao.findByUser(Integer.parseInt(userId));
            if (!basketOptional.isPresent()) {
                throw new NNSServiceStoreException();
            }

            return convertGoodPriceInBasket(basketOptional.get(), currencyService.findCurrencyById(currencyId));
        } catch (NumberFormatException | NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public boolean removeBasket(String id) throws NNSServiceStoreException {
        try {
            return basketDao.remove(Long.parseLong(id));
        } catch (NSSDaoStoreException | NumberFormatException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public boolean updateBasket(Basket basket, String userId) throws NNSServiceStoreException {
        try {
            boolean isValidBasket = NNSBasketValidator.validate(basket);

            if (isValidBasket && isUserCreated(Long.parseLong(userId), userService.findAllUsers()) &&
                    isValidGood(basket.getGoods()) && isGoodCreated(basket, goodService.findAllGoods("1"))) {
                return basketDao.update(basket, Long.parseLong(userId));
            }

            return false;
        } catch (NSSDaoStoreException | NumberFormatException e) {
            throw new NNSServiceStoreException(e);
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
            if (!NNSGoodValidator.validate(good.getKey()) || good.getValue() == 0) {
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

    private static Basket convertGoodPriceInBasket(Basket basket, Currency currency) {
        basket.getGoods().keySet().forEach(currentGood -> currentGood = NNSGoodPriceConverter.convert(currentGood, currency));
        return basket;
    }

    private static List<Basket> convertGoodPriceInBasketList(List <Basket> basketList, Currency currency) {
        basketList.forEach(currentBasket -> currentBasket = convertGoodPriceInBasket(currentBasket, currency));
        return basketList;
    }
}

