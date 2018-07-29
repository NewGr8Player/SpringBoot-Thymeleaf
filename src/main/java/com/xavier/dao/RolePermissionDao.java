package com.xavier.dao;


import com.xavier.bean.RolePermission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 角色权限Dao
 *
 * @author NewGr8Player
 */
public interface RolePermissionDao extends CrudRepository<RolePermission, String> {
    /**
     * 根据roleId查找
     *
     * @param roleId
     * @return
     */
    List<RolePermission> findByRoleId(String roleId);
}
