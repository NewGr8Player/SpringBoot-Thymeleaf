package com.xavier.service;

import com.xavier.bean.Permission;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * 权限Service
 *
 * @author NewGr8Player
 */
public interface PermissionService {

    /**
     * 根据Id查找权限
     *
     * @param id
     * @return
     */
    Optional<Permission> findById(String id);
}
