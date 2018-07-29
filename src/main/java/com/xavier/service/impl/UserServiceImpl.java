package com.xavier.service.impl;

import com.xavier.bean.User;
import com.xavier.dao.UserDao;
import com.xavier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Cacheable(cacheNames = {"user"}, unless = "#result eq null")
    public Optional<User> findByUserName(String username) {
        return this.userDao.findByUsername(username);
    }
}
