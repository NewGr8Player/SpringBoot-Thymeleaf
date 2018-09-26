package com.xavier.service;

import com.xavier.bean.PermissionMenu;
import com.xavier.common.service.BaseService;

import java.util.List;

/**
 * 权限菜单关系表Service
 *
 * @author NewGr8Player
 */
public interface PermissionMenuService extends BaseService<PermissionMenu> {

    /**
     * 根据permissionIdList批量查找
     *
     * @param permissionIdList
     * @return
     */
    List<PermissionMenu> findByBatchPermissionIds(List<String> permissionIdList);
}
