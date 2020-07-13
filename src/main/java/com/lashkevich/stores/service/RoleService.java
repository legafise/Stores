package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.RoleDao;
import com.lashkevich.stores.entity.Role;
import com.lashkevich.stores.exception.NNSServiceStoreException;

import java.util.List;

public interface RoleService {

    RoleDao getRoleDao();

    void setRoleDao(RoleDao roleDao);

    boolean addRole(Role role) throws NNSServiceStoreException;

    List<Role> findAllRoles() throws NNSServiceStoreException;

    Role findRoleById(String id) throws NNSServiceStoreException;

    boolean removeRole(String id) throws NNSServiceStoreException;

    boolean updateRole(Role role) throws NNSServiceStoreException;
}
