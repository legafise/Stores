package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.BasketDao;
import com.lashkevich.stores.entity.*;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.service.BasketService;
import com.lashkevich.stores.service.GoodService;
import com.lashkevich.stores.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NNSBasketServiceTest {
    private BasketService basketService;
    private BasketDao basketDao;
    private GoodService goodService;
    private UserService userService;
    private Basket firstTestBasket;
    private Optional<Basket> firstTestBasketOptional;
    private Basket secondTestBasket;
    private Optional<Basket> secondTestBasketOptional;
    private Good firstTestGood;
    private Good secondTestGood;
    private User firstTestUser;
    private User secondTestUser;
    private List<Basket> firstTestBasketList;
    private List<Good> goods;
    private List<User> users;

    @Before
    public void setUp() {
        basketService = new NNSBasketService();
        basketDao = mock(BasketDao.class);
        goodService = mock(GoodService.class);
        userService = mock(UserService.class);
        basketService.setBasketDao(basketDao);
        basketService.setGoodService(goodService);
        basketService.setUserService(userService);

        firstTestGood = new Good(23, "Android", "Android", "Android");
        secondTestGood = new Good(22, "Apple", "Apple", "Apple");
        goods = new ArrayList<>();
        goods.add(firstTestGood);
        goods.add(secondTestGood);

        Map<Good, Integer> firstGoods = new HashMap<>();
        firstGoods.put(firstTestGood, 1);
        firstTestBasket = new Basket(firstGoods);
        firstTestBasketOptional = Optional.of(firstTestBasket);

        firstTestBasketList = new ArrayList<>();
        firstTestBasketList.add(firstTestBasket);

        Map<Good, Integer> secondGoods = new HashMap<>();
        secondGoods.put(secondTestGood, 2);
        secondTestBasket = new Basket(secondGoods);
        secondTestBasketOptional = Optional.of(secondTestBasket);

        firstTestUser = new User(1, "df2", "dU", "Qd", "Casas",
                "tap0oc04@yandex.ru", LocalDate.of(2000, 04, 12), new Role(2, "User"),
                new City(1, "Minsk", new Country(1, "Belarus")));
        secondTestUser = new User(2, "wfw", "thth", "dfg", "dfgdfg",
                "lashkevich@yandex.ru", LocalDate.of(2005, 10, 4), new Role(2, "User"),
                new City(1, "Minsk", new Country(1, "Belarus")));
        users = new ArrayList<>();
        users.add(firstTestUser);
        users.add(secondTestUser);
    }

    @Test
    public void addBasketPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(goodService.findAllGoods()).thenReturn(goods);
        when(userService.findAllUsers()).thenReturn(users);
        when(basketDao.findByUser(1)).thenReturn(secondTestBasketOptional);
        when(basketDao.add(firstTestBasket, 1)).thenReturn(true);
        Assert.assertTrue(basketService.addBasket(firstTestBasket, "1"));
    }

    @Test
    public void addBasketNegativeTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(goodService.findAllGoods()).thenReturn(goods);
        when(userService.findAllUsers()).thenReturn(users);
        when(basketDao.findByUser(4)).thenReturn(secondTestBasketOptional);
        when(basketDao.add(firstTestBasket, 1)).thenReturn(true);
        Assert.assertFalse(basketService.addBasket(firstTestBasket, "4"));
    }

    @Test (expected = NNSServiceStoreException.class)
    public void addBasketWithInvalidUserIdTest() throws NSSDaoStoreException, NNSServiceStoreException {
        basketService.addBasket(firstTestBasket, "ertger");
    }

    @Test
    public void findAllBasketsTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(basketDao.findAll()).thenReturn(firstTestBasketList);
        Assert.assertEquals(firstTestBasketList, basketService.findAllBaskets());
    }

    @Test
    public void findBasketByUserIdPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(basketDao.findByUser(1)).thenReturn(firstTestBasketOptional);
        Assert.assertEquals(firstTestBasket, basketService.findBasketByUserId("1"));
    }

    @Test (expected = NNSServiceStoreException.class)
    public void findBasketByUserIdWithInvalidUserIdTest() throws NNSServiceStoreException {
        basketService.findBasketByUserId("wfwfw");
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
        when(goodService.findAllGoods()).thenReturn(goods);
        when(userService.findAllUsers()).thenReturn(users);
        when(basketDao.update(firstTestBasket, 1)).thenReturn(true);
        Assert.assertTrue(basketService.updateBasket(firstTestBasket, "1"));
    }

    @Test
    public void updateBasketNegativeTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(goodService.findAllGoods()).thenReturn(goods);
        when(userService.findAllUsers()).thenReturn(users);
        when(basketDao.update(firstTestBasket, 1)).thenReturn(true);
        Assert.assertFalse(basketService.updateBasket(firstTestBasket, "3"));
    }

    @Test (expected = NNSServiceStoreException.class)
    public void updateBasketWithInvalidUserIdTest() throws NNSServiceStoreException {
        basketService.updateBasket(firstTestBasket, "ehgeo");
    }
}
