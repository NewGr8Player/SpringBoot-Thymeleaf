package com.xavier.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xavier.bean.Role;
import com.xavier.dao.RoleDao;
import org.apache.ibatis.session.RowBounds;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色Servier
 *
 * @author NewGr8Player
 */
@Service
@Transactional(readOnly = true)
public class RoleService extends ServiceImpl<RoleDao, Role> {

    /**
     * 根据idList查询列表
     *
     * @param idList
     * @return
     */
    @Cacheable(cacheNames = "roleList")
    public List<Role> selectBatchIds(List<String> idList) {
        return super.selectBatchIds(idList);
    }

    /**
     * 查询分页列表
     *
     * @param role
     * @param rowBounds
     * @return
     */
    public Page<Role> selectRoleListPage(Role role, RowBounds rowBounds) {
        EntityWrapper<Role> wrapper = new EntityWrapper();
        wrapper.setEntity(role);
        return new Page<Role>().setRecords(baseMapper.selectList(wrapper));
    }
}
