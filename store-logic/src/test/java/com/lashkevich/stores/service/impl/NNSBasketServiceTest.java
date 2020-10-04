package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.BasketDao;
import com.lashkevich.stores.entity.Currency;
import com.lashkevich.stores.entity.*;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.service.BasketService;
import com.lashkevich.stores.service.CurrencyService;
import com.lashkevich.stores.service.GoodService;
import com.lashkevich.stores.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NNSBasketServiceTest {
    private static final String STANDARD_CURRENCY_ID = "1";
    private BasketService basketService;
    private BasketDao basketDao;
    private CurrencyService currencyService;
    private GoodService goodService;
    private UserService userService;
    private Basket firstTestBasket;
    private Basket firstTestBasketAfterConversion;
    private Optional<Basket> firstTestBasketOptional;
    private Optional<Basket> firstTestBasketAfterConversionOptional;
    private Basket secondTestBasket;
    private Optional<Basket> secondTestBasketOptional;
    private Good firstTestGood;
    private Good firstTestGoodAfterConversion;
    private Good secondTestGood;
    private User firstTestUser;
    private User secondTestUser;
    private Currency firstTestCurrency;
    private Currency secondTestCurrency;
    private List<Basket> firstTestBasketList;
    private List<Basket> firstTestBasketListAfterConversion;
    private List<Good> goods;
    private List<User> users;

    @Before
    public void setUp() {
        basketService = new NNSBasketService();
        basketDao = mock(BasketDao.class);
        goodService = mock(GoodService.class);
        userService = mock(UserService.class);
        currencyService = mock(CurrencyService.class);
        basketService.setBasketDao(basketDao);
        basketService.setGoodService(goodService);
        basketService.setUserService(userService);
        basketService.setCurrencyService(currencyService);

        firstTestGood = new Good(23, "Android", new BigDecimal("2.0"), "Android", "Android", "Android");
        firstTestGoodAfterConversion = new Good(23, "Android", new BigDecimal("5.20"), "Android", "Android", "Android");
        secondTestGood = new Good(22, "Apple", new BigDecimal("1.0"), "Apple", "Apple", "Apple");
        goods = new ArrayList<>();
        goods.add(firstTestGood);
        goods.add(secondTestGood);

        Map<Good, Integer> firstGoods = new HashMap<>();
        firstGoods.put(firstTestGood, 1);
        firstTestBasket = new Basket(firstGoods);
        firstTestBasketOptional = Optional.of(firstTestBasket);

        Map<Good, Integer> firstGoodsAfterConversion = new HashMap<>();
        firstGoodsAfterConversion.put(firstTestGoodAfterConversion, 1);
        firstTestBasketAfterConversion = new Basket(firstGoodsAfterConversion);
        firstTestBasketAfterConversionOptional = Optional.of(firstTestBasketAfterConversion);

        firstTestBasketList = new ArrayList<>();
        firstTestBasketList.add(firstTestBasket);

        firstTestBasketListAfterConversion = new ArrayList<>();
        firstTestBasketListAfterConversion.add(firstTestBasketAfterConversion);

        firstTestCurrency = new Currency(1, "United States Dollar", new BigDecimal("1.0"), "$");
        secondTestCurrency = new Currency(2, "Belarusian ruble", new BigDecimal("2.6"), "BYN");

        Map<Good, Integer> secondGoods = new HashMap<>();
        secondGoods.put(secondTestGood, 2);
        secondTestBasket = new Basket(secondGoods);
        secondTestBasketOptional = Optional.of(secondTestBasket);

        firstTestUser = new User(1, "df2", "dU", "Qd", "Casas",
                "tap0oc04@yandex.ru", LocalDate.of(2000, 04, 12), new Role(2, "User"),
                new City(1, "Minsk", new Country(1, "Belarus", new Currency(3, "Russian ruble", new BigDecimal("76.8"), "RUB"))));
        secondTestUser = new User(2, "wfw", "thth", "dfg", "dfgdfg",
                "lashkevich@yandex.ru", LocalDate.of(2005, 10, 4), new Role(2, "User"),
                new City(1, "Minsk", new Country(1, "Belarus", new Currency(3, "Russian ruble", new BigDecimal("76.8"), "RUB"))));
        users = new ArrayList<>();
        users.add(firstTestUser);
        users.add(secondTestUser);
    }

    @Test
    public void addBasketPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(goodService.findAllGoods(STANDARD_CURRENCY_ID)).thenReturn(goods);
        when(currencyService.findCurrencyById(STANDARD_CURRENCY_ID)).thenReturn(firstTestCurrency);
        when(userService.findAllUsers()).thenReturn(users);
        when(basketDao.findByUser(1)).thenReturn(secondTestBasketOptional);
        when(basketDao.add(firstTestBasket, 1)).thenReturn(true);
        Assert.assertTrue(basketService.addBasket(firstTestBasket, "1"));
    }

    @Test
    public void addBasketNegativeTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(goodService.findAllGoods(STANDARD_CURRENCY_ID)).thenReturn(goods);
        when(currencyService.findCurrencyById(STANDARD_CURRENCY_ID)).thenReturn(firstTestCurrency);
        when(userService.findAllUsers()).thenReturn(users);
        when(basketDao.findByUser(4)).thenReturn(secondTestBasketOptional);
        when(basketDao.add(firstTestBasket, 1)).thenReturn(true);
        Assert.assertFalse(basketService.addBasket(firstTestBasket, "4"));
    }

    @Test (expected = NNSServiceStoreException.class)
    public void addBasketWithInvalidUserIdTest() throws NNSServiceStoreException {
        basketService.addBasket(firstTestBasket, "ertger");
    }

    @Test
    public void findAllBasketsTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(basketDao.findAll()).thenReturn(firstTestBasketList);
        when(currencyService.findCurrencyById("2")).thenReturn(secondTestCurrency);
        Assert.assertEquals(firstTestBasketListAfterConversion.get(0), basketService.findAllBaskets("2").get(0));
    }

    @Test
    public void findBasketByUserIdPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(basketDao.findByUser(1)).thenReturn(firstTestBasketOptional);
        when(currencyService.findCurrencyById("2")).thenReturn(secondTestCurrency);
        Assert.assertEquals(firstTestBasketAfterConversion, basketService.findBasketByUserId("1", "2"));
    }

    @Test (expected = NNSServiceStoreException.class)
    public void findBasketByUserIdWithInvalidUserIdTest() throws NNSServiceStoreException {
        basketService.findBasketByUserId("wfwfw", "d2f2wf");
    }

    @Test
    public void removeBasketPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(basketDao.remove(1)).thenReturn(true);
        Assert.assertTrue(basketService.removeBasket("1"));
    }

    @Test (expected = NNSServiceStoreException.class)
    public void removeBasketWithInvalidUserIdTest() throws NSSDaoStoreException, NNSServiceStoreException {
        basketService.removeBasket("gfweg");
    }

    @Test
    public void updateBasketPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(goodService.findAllGoods(STANDARD_CURRENCY_ID)).thenReturn(goods);
        when(currencyService.findCurrencyById(STANDARD_CURRENCY_ID)).thenReturn(firstTestCurrency);
        when(userService.findAllUsers()).thenReturn(users);
        when(basketDao.update(firstTestBasket, 1)).thenReturn(true);
        Assert.assertTrue(basketService.updateBasket(firstTestBasket, "1"));
    }

    @Test
    public void updateBasketNegativeTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(goodService.findAllGoods(STANDARD_CURRENCY_ID)).thenReturn(goods);
        when(currencyService.findCurrencyById(STANDARD_CURRENCY_ID)).thenReturn(firstTestCurrency);
        when(userService.findAllUsers()).thenReturn(users);
        when(basketDao.update(firstTestBasket, 1)).thenReturn(true);
        Assert.assertFalse(basketService.updateBasket(firstTestBasket, "3"));
    }

    @Test (expected = NNSServiceStoreException.class)
    public void updateBasketWithInvalidUserIdTest() throws NNSServiceStoreException {
        basketService.updateBasket(firstTestBasket, "ehgeo");
    }
}
