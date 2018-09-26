package com.xavier.service.impl;

import com.xavier.bean.Role;
import com.xavier.common.service.impl.BaseServiceImpl;
import com.xavier.dao.RoleDao;
import com.xavier.service.RoleService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, Role> implements RoleService {

    @Override
    public Role selectById(String id) {
        return this.dao.selectById(id);
    }

    @Override
    @Cacheable(cacheNames = "roleList")
    public List<Role> selectBatchIds(List<String> idList) {
        return super.selectBatchIds(idList);
    }
}
