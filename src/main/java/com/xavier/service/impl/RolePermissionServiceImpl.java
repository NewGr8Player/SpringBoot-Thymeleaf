package com.xavier.service.impl;


import com.xavier.bean.RolePermission;
import com.xavier.dao.RolePermissionDao;
import com.xavier.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    @Cacheable(cacheNames = {"rolePermission"}, unless = "#result eq null")
    public List<RolePermission> findByRoleId(String roleId){
        return this.rolePermissionDao.findByRoleId(roleId);
    }
}
