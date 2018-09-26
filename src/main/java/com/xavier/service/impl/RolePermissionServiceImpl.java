package com.xavier.service.impl;


import com.xavier.bean.RolePermission;
import com.xavier.common.service.impl.BaseServiceImpl;
import com.xavier.dao.RolePermissionDao;
import com.xavier.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermissionDao, RolePermission> implements RolePermissionService {

    @Override
    public List<RolePermission> findByRoleId(String roleId) {
        return dao.findByRoleId(roleId);
    }

    @Override
    @Cacheable(cacheNames = "rolePermissionList")
    public List<RolePermission> findByBatchRoleIds(List<String> roleIdList) {
        return dao.findByBatchRoleIds(roleIdList);
    }
}
