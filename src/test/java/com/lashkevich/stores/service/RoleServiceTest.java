package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.RoleDao;
import com.lashkevich.stores.entity.Role;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.exception.ServiceStoreException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoleServiceTest {
    private RoleService roleService;
    private RoleDao roleDao;
    private Role firstTestRole;
    private Role firstChangeTestRole;
    private Role secondTestRole;

    @Before
    public void setUp() {
        roleService = new RoleService();
        roleDao = mock(RoleDao.class);
        roleService.setRoleDao(roleDao);
        firstTestRole = new Role(1, "Admin");
        firstChangeTestRole = new Role(1, "Dog");
        secondTestRole = new Role(2, "User");
    }

    @Test
    public void addRolePositiveTest() throws DaoStoreException, ServiceStoreException {
        when(roleDao.add(firstTestRole)).thenReturn(true);
        List<Role> roles = new ArrayList<>();
        roles.add(secondTestRole);
        when(roleDao.findAll()).thenReturn(roles);
        Assert.assertTrue(roleService.addRole(firstTestRole));
    }

    @Test
    public void addRoleNegativeTest() throws DaoStoreException, ServiceStoreException {
        firstTestRole.setName("");
        when(roleDao.add(firstTestRole)).thenReturn(true);
        List<Role> roles = new ArrayList<>();
        roles.add(secondTestRole);
        when(roleDao.findAll()).thenReturn(roles);
        Assert.assertFalse(roleService.addRole(firstTestRole));
    }

    @Test
    public void findAllRolesTest() throws DaoStoreException, ServiceStoreException {
        List<Role> roles = new ArrayList<>();
        roles.add(firstTestRole);
        when(roleDao.findAll()).thenReturn(roles);
        Assert.assertEquals(roleService.findAllRoles(), roles);
    }

    @Test
    public void findRoleByIdPositiveTest() throws DaoStoreException, ServiceStoreException {
        when(roleDao.findById(1)).thenReturn(firstTestRole);
        Assert.assertEquals(roleService.findRoleById("1"), firstTestRole);
    }

    @Test(expected = ServiceStoreException.class)
    public void findRoleByIdWithInvalidRoleIdTest() throws ServiceStoreException {
        roleService.findRoleById("ege");
    }

    @Test
    public void removeRoleTest() throws DaoStoreException, ServiceStoreException {
        when(roleDao.remove(1)).thenReturn(true);
        Assert.assertTrue(roleService.removeRole("1"));
    }

    @Test(expected = ServiceStoreException.class)
    public void removeUserWithInvalidRemoveTest() throws ServiceStoreException {
        roleService.removeRole("Ivan");
    }

    @Test
    public void updateRolePositiveTest() throws DaoStoreException, ServiceStoreException {
        List<Role> roles = new ArrayList<>();
        roles.add(firstTestRole);
        roles.add(secondTestRole);
        when(roleDao.findAll()).thenReturn(roles);
        when(roleDao.update(firstChangeTestRole)).thenReturn(true);
        Assert.assertTrue(roleService.updateRole(firstChangeTestRole));
    }

    @Test
    public void updateRoleNegativeTest() throws DaoStoreException, ServiceStoreException {
        List<Role> roles = new ArrayList<>();
        roles.add(firstTestRole);
        roles.add(secondTestRole);
        when(roleDao.findAll()).thenReturn(roles);
        firstChangeTestRole.setName("User");
        when(roleDao.update(firstChangeTestRole)).thenReturn(true);
        Assert.assertFalse(roleService.updateRole(firstChangeTestRole));
    }
}
