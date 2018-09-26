package com.xavier.common.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xavier.bean.base.BaseEntity;
import com.xavier.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Lazy
@Transactional(readOnly = true)
public abstract class BaseServiceImpl<D extends BaseMapper, T extends BaseEntity> implements BaseService<T> {

    @Autowired
    protected D dao;

    @Override
    @Transactional
    public Integer insert(BaseEntity entity) {
        return dao.insert(entity);
    }

    @Override
    @Transactional
    public Integer insertAllColumn(BaseEntity entity) {
        return dao.insertAllColumn(entity);
    }

    @Override
    @Transactional
    public Integer deleteById(String id) {
        return dao.deleteById(id);
    }

    @Override
    @Transactional
    public Integer deleteBatchIds(List<String> idList) {
        return dao.deleteBatchIds(idList);
    }

    @Override
    @Transactional
    public Integer updateById(BaseEntity entity) {
        return dao.updateById(entity);
    }

    @Override
    @Transactional
    public Integer updateAllColumnById(BaseEntity entity) {
        return dao.updateAllColumnById(entity);
    }

    @Override
    public T selectById(String id) {
        return (T)dao.selectOne(id);
    }

    @Override
    public List<T> selectBatchIds(List<String> idList) {
        return dao.selectBatchIds(idList);
    }

    @Override
    public T selectOne(BaseEntity entity) {
        return (T) dao.selectOne(entity);
    }
}
