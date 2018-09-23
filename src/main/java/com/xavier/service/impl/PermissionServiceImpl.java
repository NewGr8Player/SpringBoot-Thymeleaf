package com.xavier.service.impl;

import com.xavier.bean.Permission;
import com.xavier.common.service.impl.BaseServiceImpl;
import com.xavier.dao.PermissionDao;
import com.xavier.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PermissionServiceImpl extends BaseServiceImpl<PermissionDao, Permission> implements PermissionService {

    @Override
    @Cacheable(cacheNames = {"permission"})
    public Permission selectById(String id) {
        return dao.findById(id);
    }
}
