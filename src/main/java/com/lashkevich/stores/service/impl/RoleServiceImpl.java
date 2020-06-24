package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.RoleDao;
import com.lashkevich.stores.dao.impl.RoleDaoImpl;
import com.lashkevich.stores.entity.Role;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.exception.ServiceStoreException;
import com.lashkevich.stores.service.RoleService;
import com.lashkevich.stores.util.checker.RoleDuplicationsChecker;
import com.lashkevich.stores.util.validator.RoleValidator;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public RoleServiceImpl() {
        roleDao = new RoleDaoImpl();
    }

    @Override
    public RoleDao getRoleDao() {
        return roleDao;
    }

    @Override
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public boolean addRole(Role role) throws ServiceStoreException {
        try {
            if (RoleValidator.validate(role) && RoleDuplicationsChecker.check(role, roleDao.findAll())) {
                return roleDao.add(role);
            }

            return false;
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public List<Role> findAllRoles() throws ServiceStoreException {
        try {
            return roleDao.findAll();
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public Role findRoleById(String id) throws ServiceStoreException {
        try {
            return roleDao.findById(Long.parseLong(id));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public boolean removeRole(String id) throws ServiceStoreException {
        try {
            return roleDao.remove(Long.parseLong(id));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public boolean updateRole(Role role) throws ServiceStoreException {
        try {
            if (RoleValidator.validate(role) && RoleDuplicationsChecker.check(role, roleDao.findAll())) {
                return roleDao.update(role);
            }

            return false;
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }
}
