package com.xavier.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xavier.bean.OperateLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志
 *
 * @author NewGr8Player
 */
@Mapper
public interface OperateLogDao extends BaseMapper<OperateLog> {
}
