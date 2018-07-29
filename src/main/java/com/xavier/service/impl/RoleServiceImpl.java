package com.xavier.service.impl;

import com.xavier.bean.Role;
import com.xavier.dao.RoleDao;
import com.xavier.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    @Cacheable(cacheNames = {"role"}, unless = "#result eq null")
    public Optional<Role> findById(String id) {
        return this.roleDao.findById(id);
    }
}
