package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.CityDao;
import com.lashkevich.stores.dao.UserDao;
import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.entity.Role;
import com.lashkevich.stores.entity.User;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.exception.ServiceStoreException;
import com.lashkevich.stores.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    private UserService userService;
    private UserDao userDao;
    private User firstTestUser;
    private User firstChangeTestUser;
    private User secondTestUser;
    private CityDao cityDao;
    private City testCity;
    private List<City> cities;

    @Before
    public void setUp() {
        userService = new UserServiceImpl();
        userDao = mock(UserDao.class);
        cityDao = mock(CityDao.class);
        userService.setUserDao(userDao);
        userService.setCityDao(cityDao);
        firstTestUser = new User(1, "df2", "dU", "Qd", "Casas",
                "t.a_p-0oc04@yan.dx.ru", LocalDate.of(2000, 04, 12), new Role(2, "User"),
                new City(1, "Minsk", new Country(1, "Belarus")));
        firstChangeTestUser = new User(1, "df2", "dU", "Qd", "Casas",
                "tap0oc04@yandex.ru", LocalDate.of(2000, 04, 12), new Role(2, "User"),
                new City(1, "Minsk", new Country(1, "Belarus")));
        secondTestUser = new User(2, "wfw", "thth", "dfg", "dfgdfg",
                "lashkevich@yandex.ru", LocalDate.of(2005, 10, 4), new Role(2, "User"),
                new City(1, "Minsk", new Country(1, "Belarus")));
        testCity = new City(1, "Minsk", new Country(1, "Belarus"));
        cities = new ArrayList<>();
        cities.add(testCity);
    }

    @Test
    public void findByIdTest() throws DaoStoreException, ServiceStoreException {
        when(userDao.findById(1)).thenReturn(firstTestUser);
        Assert.assertEquals(userService.findUserById("1"), firstTestUser);
    }

    @Test
    public void findAllTest() throws DaoStoreException, ServiceStoreException {
        List<User> users = new ArrayList<>();
        users.add(firstTestUser);
        when(userDao.findAll()).thenReturn(users);
        Assert.assertEquals(userService.findAllUsers(), users);
    }

    @Test(expected = ServiceStoreException.class)
    public void findByIdWithInvalidUserIdTest() throws ServiceStoreException {
        userService.findUserById("s");
    }

    @Test
    public void addUserPositiveTest() throws DaoStoreException, ServiceStoreException {
        when(userDao.add(firstTestUser)).thenReturn(true);
        when(cityDao.findAll()).thenReturn(cities);
        Assert.assertTrue(userService.addUser(firstTestUser));
    }

    @Test
    public void addUserNegativeTest() throws ServiceStoreException, DaoStoreException {
        firstTestUser.setEmail("tapoc04@@yandex.ru");
        when(cityDao.findAll()).thenReturn(cities);
        Assert.assertFalse(userService.addUser(firstTestUser));
    }

    @Test
    public void updateUserPositiveTest() throws DaoStoreException, ServiceStoreException {
        List<User> users = new ArrayList<>();
        users.add(firstTestUser);
        users.add(secondTestUser);
        when(userDao.findAll()).thenReturn(users);
        when(cityDao.findAll()).thenReturn(cities);
        when(userDao.update(firstChangeTestUser)).thenReturn(true);
        Assert.assertTrue(userService.updateUser(firstChangeTestUser));
    }

    @Test
    public void updateUserNegativeTest() throws ServiceStoreException, DaoStoreException {
        List<User> users = new ArrayList<>();
        users.add(firstTestUser);
        users.add(secondTestUser);
        when(userDao.findAll()).thenReturn(users);
        when(cityDao.findAll()).thenReturn(cities);
        firstChangeTestUser.setEmail(null);
        when(userDao.update(firstChangeTestUser)).thenReturn(true);
        Assert.assertFalse(userService.updateUser(firstChangeTestUser));
    }

    @Test
    public void removeUserPositiveTest() throws DaoStoreException, ServiceStoreException {
        when(userDao.remove(1)).thenReturn(true);
        Assert.assertTrue(userService.removeUser("1"));
    }

    @Test (expected = ServiceStoreException.class)
    public void removeUserWithInvalidRemoveTest() throws ServiceStoreException {
        Assert.assertFalse(userService.removeUser("ehgeh"));
    }
}
