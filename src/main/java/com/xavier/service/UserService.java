package com.xavier.service;

import com.xavier.bean.User;

public interface UserService {

	/**
	 * 根据用户名查找
	 *
	 * @param username 用户名
	 * @return
	 */
	User findByUserName(String username);
}
