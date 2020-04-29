package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.RoleDao;
import com.lashkevich.stores.entity.Role;
import com.lashkevich.stores.exception.ServiceStoreException;

import java.util.List;

public interface RoleService {

    RoleDao getRoleDao();

    void setRoleDao(RoleDao roleDao);

    boolean addRole(Role role) throws ServiceStoreException;

    List<Role> findAllRoles() throws ServiceStoreException;

    Role findRoleById(String id) throws ServiceStoreException;

    boolean removeRole(String id) throws ServiceStoreException;

    boolean updateRole(Role role) throws ServiceStoreException;
}
