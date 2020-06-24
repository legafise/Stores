package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.BasketDao;
import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.dao.UserDao;
import com.lashkevich.stores.entity.*;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.exception.ServiceStoreException;
import com.lashkevich.stores.service.impl.BasketServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasketServiceImplTest {
    private BasketService basketService;
    private BasketDao basketDao;
    private GoodDao goodDao;
    private UserDao userDao;
    private Basket firstTestBasket;
    private Basket secondTestBasket;
    private Good firstTestGood;
    private Good secondTestGood;
    private User firstTestUser;
    private User secondTestUser;
    private List<Good> goods;
    private List<Basket> firstTestBasketList;
    private List<Basket> secondTestBasketList;
    private List<User> users;

    @Before
    public void setUp() {
        basketService = new BasketServiceImpl();
        basketDao = mock(BasketDao.class);
        goodDao = mock(GoodDao.class);
        userDao = mock(UserDao.class);
        basketService.setBasketDao(basketDao);
        basketService.setGoodDao(goodDao);
        basketService.setUserDao(userDao);

        firstTestGood = new Good(23, "Android", "Android", "Android");
        secondTestGood = new Good(22, "Apple", "Apple", "Apple");
        goods = new ArrayList<>();
        goods.add(firstTestGood);
        goods.add(secondTestGood);

        Map<Good, Integer> firstGoods = new HashMap<>();
        firstGoods.put(firstTestGood, 1);
        firstTestBasket = new Basket(firstGoods);

        firstTestBasketList = new ArrayList<>();
        firstTestBasketList.add(firstTestBasket);

        Map<Good, Integer> secondGoods = new HashMap<>();
        secondGoods.put(secondTestGood, 2);
        secondTestBasket = new Basket(secondGoods);

        secondTestBasketList = new ArrayList<>();
        secondTestBasketList.add(secondTestBasket);

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
    public void addBasketPositiveTest() throws DaoStoreException, ServiceStoreException {
        when(goodDao.findAll()).thenReturn(goods);
        when(userDao.findAll()).thenReturn(users);
        when(basketDao.findByUser(1)).thenReturn(secondTestBasketList);
        when(basketDao.add(firstTestBasket, 1)).thenReturn(true);
        Assert.assertTrue(basketService.addBasket(firstTestBasket, "1"));
    }

    @Test
    public void addBasketNegativeTest() throws DaoStoreException, ServiceStoreException {
        when(goodDao.findAll()).thenReturn(goods);
        when(userDao.findAll()).thenReturn(users);
        when(basketDao.findByUser(1)).thenReturn(secondTestBasketList);
        when(basketDao.add(firstTestBasket, 1)).thenReturn(true);
        Assert.assertFalse(basketService.addBasket(firstTestBasket, "4"));
    }

    @Test (expected = ServiceStoreException.class)
    public void addBasketWithInvalidUserIdTest() throws DaoStoreException, ServiceStoreException {
        basketService.addBasket(firstTestBasket, "ertger");
    }

    @Test
    public void findAllBasketsTest() throws DaoStoreException, ServiceStoreException {
        when(basketDao.findAll()).thenReturn(firstTestBasketList);
        Assert.assertEquals(firstTestBasketList, basketService.findAllBaskets());
    }

    @Test
    public void findBasketByUserIdPositiveTest() throws DaoStoreException, ServiceStoreException {
        when(basketDao.findByUser(1)).thenReturn(firstTestBasketList);
        Assert.assertEquals(firstTestBasketList, basketService.findBasketByUserId("1"));
    }

    @Test (expected = ServiceStoreException.class)
    public void findBasketByUserIdWithInvalidUserIdTest() throws DaoStoreException, ServiceStoreException {
        basketService.findBasketByUserId("wfwfw");
    }

    @Test
    public void removeBasketPositiveTest() throws DaoStoreException, ServiceStoreException {
        when(basketDao.remove(1)).thenReturn(true);
        Assert.assertTrue(basketService.removeBasket("1"));
    }

    @Test (expected = ServiceStoreException.class)
    public void removeBasketWithInvalidUserIdTest() throws DaoStoreException, ServiceStoreException {
        basketService.removeBasket("gfweg");
    }

    @Test
    public void updateBasketPositiveTest() throws DaoStoreException, ServiceStoreException {
        when(goodDao.findAll()).thenReturn(goods);
        when(userDao.findAll()).thenReturn(users);
        when(basketDao.update(firstTestBasket, 1)).thenReturn(true);
        Assert.assertTrue(basketService.updateBasket(firstTestBasket, "1"));
    }

    @Test
    public void updateBasketNegativeTest() throws DaoStoreException, ServiceStoreException {
        when(goodDao.findAll()).thenReturn(goods);
        when(userDao.findAll()).thenReturn(users);
        when(basketDao.update(firstTestBasket, 1)).thenReturn(true);
        Assert.assertFalse(basketService.updateBasket(firstTestBasket, "3"));
    }

    @Test (expected = ServiceStoreException.class)
    public void updateBasketWithInvalidUserIdTest() throws ServiceStoreException {
        basketService.updateBasket(firstTestBasket, "ehgeo");
    }
}
