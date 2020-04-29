package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.RoleDao;
import com.lashkevich.stores.entity.Role;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.util.provider.impl.TestConnectionProviderImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RoleDaoImplTest {
    private Role firstExpectedRole;
    private Role secondExpectedRole;
    private Role thirdExpectedRole;
    private Role fourthExpectedRole;
    private Role firstChangeExpectedRole;
    private RoleDao roleDao;

    @Before
    public void setUp() {
        firstExpectedRole = new Role(1, "Admin");
        secondExpectedRole = new Role(2, "User");
        thirdExpectedRole = new Role(3, "Slave");
        fourthExpectedRole = new Role(4, "God");
        firstChangeExpectedRole = new Role(1, "Administrator");

        roleDao = new RoleDaoImpl();
        roleDao.setConnectionProvider(new TestConnectionProviderImpl());
    }

    @Test
    public void findAllTest() throws DaoStoreException {
        List<Role> expectedRoles = new ArrayList<>();
        expectedRoles.add(firstExpectedRole);
        expectedRoles.add(secondExpectedRole);
        expectedRoles.add(thirdExpectedRole);
        Assert.assertEquals(expectedRoles, roleDao.findAll());
    }

    @Test
    public void findByIdTest() throws DaoStoreException {
        Assert.assertEquals(firstExpectedRole, roleDao.findById(1));
    }

    @Test
    public void removeTest() throws DaoStoreException {
        Assert.assertTrue(roleDao.remove(3));
    }

    @Test
    public void addTest() throws DaoStoreException {
        Assert.assertTrue(roleDao.add(fourthExpectedRole));
    }

    @Test
    public void update() throws DaoStoreException {
        Assert.assertTrue(roleDao.update(firstChangeExpectedRole));
    }
}
