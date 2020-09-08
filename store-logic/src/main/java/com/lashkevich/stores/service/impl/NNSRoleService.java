package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.RoleDao;
import com.lashkevich.stores.dao.impl.NNSRoleDao;
import com.lashkevich.stores.entity.Role;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.service.RoleService;
import com.lashkevich.stores.util.checker.NNSRoleDuplicationsChecker;
import com.lashkevich.stores.util.validator.NNSRoleValidator;

import java.util.List;
import java.util.Optional;

public class NNSRoleService implements RoleService {
    private RoleDao roleDao;

    public NNSRoleService() {
        roleDao = new NNSRoleDao();
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
    public boolean addRole(Role role) throws NNSServiceStoreException {
        try {
            if (NNSRoleValidator.validate(role) && NNSRoleDuplicationsChecker.check(role, roleDao.findAll())) {
                return roleDao.add(role);
            }

            return false;
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public List<Role> findAllRoles() throws NNSServiceStoreException {
        try {
            return roleDao.findAll();
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public Role findRoleById(String id) throws NNSServiceStoreException {
        try {
            Optional<Role> roleOptional = roleDao.findById(Long.parseLong(id));
            if (!roleOptional.isPresent()) {
                throw new NNSServiceStoreException();
            }

            return roleOptional.get();
        } catch (NumberFormatException | NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public boolean removeRole(String id) throws NNSServiceStoreException {
        try {
            return roleDao.remove(Long.parseLong(id));
        } catch (NSSDaoStoreException | NumberFormatException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public boolean updateRole(Role role) throws NNSServiceStoreException {
        try {
            if (NNSRoleValidator.validate(role) && NNSRoleDuplicationsChecker.check(role, roleDao.findAll())) {
                return roleDao.update(role);
            }

            return false;
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }
}
