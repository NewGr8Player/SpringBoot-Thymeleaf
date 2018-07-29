package com.xavier.dao;

import com.xavier.bean.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * 用户角色Dao
 *
 * @author NewGr8Player
 */
public interface UserRoleDao extends CrudRepository<UserRole, String> {

    /**
     * 根据用户Id查找角色
     *
     * @param userId
     * @return
     */
    List<UserRole> findByUserId(String userId);
}
