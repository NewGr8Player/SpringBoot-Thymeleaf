package com.xavier.service.impl;

import com.xavier.bean.Permission;
import com.xavier.dao.PermissionDao;
import com.xavier.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    @Cacheable(cacheNames = {"permission"}, unless = "#result eq null")
    public Permission findById(String id) {
        return this.permissionDao.findById(id);
    }
}
