package com.xavier.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xavier.bean.OperateLog;
import com.xavier.dao.OperateLogDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 操作记录Servier
 *
 * @author NewGr8Player
 */
@Service
@Transactional(readOnly = true)
public class OperateLogService extends ServiceImpl<OperateLogDao, OperateLog> {
}
