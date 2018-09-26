package com.xavier.service.impl;

import com.xavier.bean.Permission;
import com.xavier.common.service.impl.BaseServiceImpl;
import com.xavier.dao.PermissionDao;
import com.xavier.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PermissionServiceImpl extends BaseServiceImpl<PermissionDao, Permission> implements PermissionService {

    @Override
    public Permission selectById(String id) {
        return dao.findById(id);
    }

    @Override
    @Cacheable(cacheNames = "permissionList")
    public List<Permission> selectBatchIds(List<String> idList) {
        return super.selectBatchIds(idList);
    }
}
