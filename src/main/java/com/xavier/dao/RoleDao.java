package com.xavier.dao;

import com.xavier.bean.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * 角色Dao
 *
 * @author NewGr8Player
 */
public interface RoleDao extends CrudRepository<Role, String> {
}
