package com.xavier.service;

import com.xavier.bean.User;

import java.util.Optional;

public interface UserService {

    /**
     * 根据用户名查找
     * @param username 用户名
     * @return
     */
    Optional<User> findByUserName(String username);
}
