package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.UserDao;
import com.lashkevich.stores.dao.impl.UserDaoImpl;
import com.lashkevich.stores.entity.*;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.util.provider.impl.TestConnectionProviderImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserDaoImplTest {
    private User firstExpectedUser;
    private User secondExpectedUser;
    private User thirdExpectedUser;
    private User fourthExpectedUser;
    private User firstChangeExpectedUser;
    private UserDao userDao;

    @Before
    public void setUp() {
        Map<Good, Integer> firstGoods = new HashMap<>();
        firstGoods.put(new Good(22, "Apple", "Apple", "Apple"), 2);
        firstGoods.put(new Good(23, "Android", "Android", "Android"), 1);
        Basket firstBasket = new Basket(firstGoods);
        firstExpectedUser = new User(8, "A", "B", "C", "D",
                "E", LocalDate.of(2000,02,20), new Role(1, "Admin"),
                new City(1, "Minsk", new Country(1, "Belarus")), firstBasket);

        firstChangeExpectedUser = new User(8, "A", "B", "C", "DOM",
                "E", LocalDate.of(2000,02,20), new Role(1, "Admin"),
                new City(1, "Minsk", new Country(1, "Belarus")), firstBasket);

        Map<Good, Integer> secondGoods = new HashMap<>();
        secondGoods.put(new Good(22, "Apple", "Apple", "Apple"), 2);
        secondGoods.put(new Good(23, "Android", "Android", "Android"), 1);
        Basket secondBasket = new Basket(secondGoods);
        secondExpectedUser = new User(9, "F", "G", "H", "I",
                "J", LocalDate.of(1111,11,11), new Role(2, "User"),
                new City(2, "Moscow", new Country(2, "Russia")), secondBasket);

        thirdExpectedUser = new User(10, "K", "L", "M", "N",
                "O", LocalDate.of(1010,10,10), new Role(2, "User"),
                new City(2, "Moscow", new Country(2, "Russia")));

        fourthExpectedUser = new User(11, "P", "U", "H", "C",
                "V", LocalDate.of(2010,04,12), new Role(2, "User"),
                new City(1, "Minsk", new Country(1, "Belarus")));

        userDao = new UserDaoImpl();
        userDao.setConnectionProvider(new TestConnectionProviderImpl());
    }

    @Test
    public void findByIdTest() throws DaoStoreException {
        Assert.assertEquals(firstExpectedUser, userDao.findById(8));
    }

    @Test
    public void findAllTest() throws DaoStoreException {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(firstExpectedUser);
        expectedUsers.add(secondExpectedUser);
        expectedUsers.add(thirdExpectedUser);
        Assert.assertEquals(expectedUsers, userDao.findAll());
    }

    @Test
    public void addTest() throws DaoStoreException {
        Assert.assertTrue(userDao.add(fourthExpectedUser));
    }

    @Test
    public void updateTest() throws DaoStoreException {
        Assert.assertTrue(userDao.update(firstChangeExpectedUser));
    }

    @Test
    public void removeTest() throws DaoStoreException{
        Assert.assertTrue(userDao.remove(10));
    }
}
