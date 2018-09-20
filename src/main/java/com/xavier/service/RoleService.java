package com.xavier.service;

import com.xavier.bean.Role;

/**
 * 角色Service
 *
 * @author NewGr8player
 */
public interface RoleService {
	/**
	 * 根据Id查找
	 *
	 * @param id
	 * @return
	 */
	Role findById(String id);
}
