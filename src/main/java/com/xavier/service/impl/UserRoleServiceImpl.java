package com.xavier.service.impl;

import com.xavier.bean.UserRole;
import com.xavier.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    @Cacheable(cacheNames = {"userRole"}, unless = "#result eq null")
    public List<UserRole> findByUserId(String userId){
        return userRoleService.findByUserId(userId);
    }
}
