package com.xavier.service.impl;

import com.xavier.bean.Role;
import com.xavier.common.service.impl.BaseServiceImpl;
import com.xavier.dao.RoleDao;
import com.xavier.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, Role> implements RoleService {

    @Override
    @Cacheable(cacheNames = {"role"})
    public Role selectById(String id) {
        return this.dao.findById(id);
    }
}
