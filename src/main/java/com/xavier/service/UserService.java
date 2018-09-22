package com.xavier.service;

import com.xavier.bean.User;
import com.xavier.common.service.BaseService;

public interface UserService extends BaseService<User> {

	/**
	 * 根据用户名查找
	 *
	 * @param username 用户名
	 * @return
	 */
	User findByUserName(String username);
}
