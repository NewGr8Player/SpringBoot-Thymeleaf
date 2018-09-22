package com.xavier.service;


import com.xavier.bean.RolePermission;
import com.xavier.common.service.BaseService;

import java.util.List;

/**
 * 角色权限Service
 *
 * @author NewGr8Player
 */
public interface RolePermissionService extends BaseService<RolePermission> {

    /**
     * 根据roleId查找
     *
     * @param roleId
     * @return
     */
    List<RolePermission> findByRoleId(String roleId);
}
