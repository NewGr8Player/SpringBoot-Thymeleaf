package com.xavier.dao;

import com.xavier.bean.Permission;
import org.springframework.data.repository.CrudRepository;

/**
 * 权限Dao
 *
 * @author NewGr8Player
 */
public interface PermissionDao extends CrudRepository<Permission, String> {
}
