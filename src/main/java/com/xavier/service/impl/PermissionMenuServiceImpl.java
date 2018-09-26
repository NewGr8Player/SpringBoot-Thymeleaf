package com.xavier.service.impl;

import com.xavier.bean.PermissionMenu;
import com.xavier.common.service.impl.BaseServiceImpl;
import com.xavier.dao.PermissionMenuDao;
import com.xavier.service.PermissionMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PermissionMenuServiceImpl extends BaseServiceImpl<PermissionMenuDao, PermissionMenu> implements PermissionMenuService {

    @Override
    public List<PermissionMenu> findByBatchPermissionIds(List<String> permissionIdList) {
        return dao.findByBatchPermissionIds(permissionIdList);
    }
}
