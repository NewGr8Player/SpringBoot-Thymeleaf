package com.xavier.service;

import com.xavier.bean.UserRole;

import java.util.List;

/**
 * 用户角色Service
 *
 * @author NewGr8Player
 */
public interface UserRoleService {

    /**
     * 根据用户Id查找角色
     *
     * @param userId
     * @return
     */
    List<UserRole> findByUserId(String userId);
}
