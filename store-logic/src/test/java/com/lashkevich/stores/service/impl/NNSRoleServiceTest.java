package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.RoleDao;
import com.lashkevich.stores.entity.Role;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.service.RoleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NNSRoleServiceTest {
    private RoleService roleService;
    private RoleDao roleDao;
    private Role firstTestRole;
    private Optional<Role> firstTestRoleOptional;
    private Role firstChangeTestRole;
    private Role secondTestRole;

    @Before
    public void setUp() {
        roleService = new NNSRoleService();
        roleDao = mock(RoleDao.class);
        roleService.setRoleDao(roleDao);
        firstTestRole = new Role(1, "Admin");
        firstTestRoleOptional = Optional.of(firstTestRole);
        firstChangeTestRole = new Role(1, "Dog");
        secondTestRole = new Role(2, "User");
    }

    @Test
    public void addRolePositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(roleDao.add(firstTestRole)).thenReturn(true);
        List<Role> roles = new ArrayList<>();
        roles.add(secondTestRole);
        when(roleDao.findAll()).thenReturn(roles);
        Assert.assertTrue(roleService.addRole(firstTestRole));
    }

    @Test
    public void addRoleNegativeTest() throws NSSDaoStoreException, NNSServiceStoreException {
        firstTestRole.setName("");
        when(roleDao.add(firstTestRole)).thenReturn(true);
        List<Role> roles = new ArrayList<>();
        roles.add(secondTestRole);
        when(roleDao.findAll()).thenReturn(roles);
        Assert.assertFalse(roleService.addRole(firstTestRole));
    }

    @Test
    public void findAllRolesTest() throws NSSDaoStoreException, NNSServiceStoreException {
        List<Role> roles = new ArrayList<>();
        roles.add(firstTestRole);
        when(roleDao.findAll()).thenReturn(roles);
        Assert.assertEquals(roleService.findAllRoles(), roles);
    }

    @Test
    public void findRoleByIdPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(roleDao.findById(1)).thenReturn(firstTestRoleOptional);
        Assert.assertEquals(roleService.findRoleById("1"), firstTestRole);
    }

    @Test(expected = NNSServiceStoreException.class)
    public void findRoleByIdWithInvalidRoleIdTest() throws NNSServiceStoreException {
        roleService.findRoleById("ege");
    }

    @Test
    public void removeRoleTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(roleDao.remove(1)).thenReturn(true);
        Assert.assertTrue(roleService.removeRole("1"));
    }

    @Test(expected = NNSServiceStoreException.class)
    public void removeUserWithInvalidRemoveTest() throws NNSServiceStoreException {
        roleService.removeRole("Ivan");
    }

    @Test
    public void updateRolePositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        List<Role> roles = new ArrayList<>();
        roles.add(firstTestRole);
        roles.add(secondTestRole);
        when(roleDao.findAll()).thenReturn(roles);
        when(roleDao.update(firstChangeTestRole)).thenReturn(true);
        Assert.assertTrue(roleService.updateRole(firstChangeTestRole));
    }

    @Test
    public void updateRoleNegativeTest() throws NSSDaoStoreException, NNSServiceStoreException {
        List<Role> roles = new ArrayList<>();
        roles.add(firstTestRole);
        roles.add(secondTestRole);
        when(roleDao.findAll()).thenReturn(roles);
        firstChangeTestRole.setName("User");
        when(roleDao.update(firstChangeTestRole)).thenReturn(true);
        Assert.assertFalse(roleService.updateRole(firstChangeTestRole));
    }
}
