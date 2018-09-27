package com.xavier.service.impl;

import com.xavier.bean.User;
import com.xavier.common.service.impl.BaseServiceImpl;
import com.xavier.dao.UserDao;
import com.xavier.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {

    @Override
    @Cacheable(cacheNames = "user")
    public User findByUserName(String username) {
        return this.dao.findByUsername(username);
    }
}
