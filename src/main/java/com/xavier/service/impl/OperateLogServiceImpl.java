package com.xavier.service.impl;

import com.xavier.bean.OperateLog;
import com.xavier.common.service.impl.BaseServiceImpl;
import com.xavier.dao.OperateLogDao;
import com.xavier.service.OperateLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OperateLogServiceImpl extends BaseServiceImpl<OperateLogDao, OperateLog> implements OperateLogService {
}
