package com.xavier.service;


import com.xavier.bean.RolePermission;

import java.util.List;

/**
 * 角色权限Service
 *
 * @author NewGr8Player
 */
public interface RolePermissionService {

    /**
     * 根据roleId查找
     *
     * @param roleId
     * @return
     */
    List<RolePermission> findByRoleId(String roleId);
}
