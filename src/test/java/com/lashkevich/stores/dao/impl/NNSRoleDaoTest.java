package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.RoleDao;
import com.lashkevich.stores.entity.Role;
import com.lashkevich.stores.exception.NNSConnectionPoolException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.pool.NNSConnectionPool;
import com.lashkevich.stores.util.reader.PropertiesReader;
import com.lashkevich.stores.util.reader.impl.NNSTestPropertiesReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NNSRoleDaoTest {
    private RoleDao roleDao;
    private Role firstExpectedRole;
    private Role secondExpectedRole;
    private Role thirdExpectedRole;
    private Role fourthExpectedRole;
    private Role firstChangeExpectedRole;

    @Before
    public void setUp() throws NNSConnectionPoolException {
        PropertiesReader testPropertiesReader = new NNSTestPropertiesReader();

        NNSConnectionPool.getInstance().setPropertiesReader(testPropertiesReader);
        NNSConnectionPool.getInstance().initializeConnectionPool(1);

        roleDao = new NNSRoleDao();
        roleDao.setPropertiesReader(testPropertiesReader);

        firstExpectedRole = new Role(1, "Admin");
        secondExpectedRole = new Role(2, "User");
        thirdExpectedRole = new Role(3, "Slave");
        fourthExpectedRole = new Role(4, "God");
        firstChangeExpectedRole = new Role(1, "Administrator");
    }

    @Test
    public void findAllTest() throws NSSDaoStoreException {
        List<Role> expectedRoles = new ArrayList<>();
        expectedRoles.add(firstExpectedRole);
        expectedRoles.add(secondExpectedRole);
        expectedRoles.add(thirdExpectedRole);
        Assert.assertEquals(expectedRoles, roleDao.findAll());
    }

    @Test
    public void findByIdTest() throws NSSDaoStoreException {
        Assert.assertEquals(firstExpectedRole, roleDao.findById(1).get());
    }

    @Test
    public void removeTest() throws NSSDaoStoreException {
        Assert.assertTrue(roleDao.remove(3));
    }

    @Test
    public void addTest() throws NSSDaoStoreException {
        Assert.assertTrue(roleDao.add(fourthExpectedRole));
    }

    @Test
    public void update() throws NSSDaoStoreException {
        Assert.assertTrue(roleDao.update(firstChangeExpectedRole));
    }
}
