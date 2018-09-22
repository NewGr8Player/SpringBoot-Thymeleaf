package com.xavier.service;

import com.xavier.bean.UserRole;
import com.xavier.common.service.BaseService;

import java.util.List;

/**
 * 用户角色Service
 *
 * @author NewGr8Player
 */
public interface UserRoleService extends BaseService<UserRole> {

    /**
     * 根据用户Id查找角色
     *
     * @param userId
     * @return
     */
    List<UserRole> findByUserId(String userId);
}
