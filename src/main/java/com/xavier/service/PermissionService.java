package com.xavier.service;

import com.xavier.bean.Permission;

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
	Permission findById(String id);
}
