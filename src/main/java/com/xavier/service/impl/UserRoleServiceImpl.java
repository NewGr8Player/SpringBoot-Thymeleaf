package com.xavier.service.impl;

import com.xavier.bean.UserRole;
import com.xavier.common.service.impl.BaseServiceImpl;
import com.xavier.dao.UserRoleDao;
import com.xavier.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

    @Override
    @Cacheable(cacheNames = "userRoleList")
    public List<UserRole> findByUserId(String userId) {
        return dao.findByUserId(userId);
    }

    @Override
    @Cacheable(cacheNames = "userRoleList")
    public List<UserRole> selectBatchIds(List<String> idList) {
        return super.selectBatchIds(idList);
    }
}
