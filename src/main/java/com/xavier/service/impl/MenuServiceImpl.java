package com.xavier.service.impl;

import com.xavier.bean.Menu;
import com.xavier.common.service.impl.BaseServiceImpl;
import com.xavier.dao.MenuDao;
import com.xavier.service.MenuService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl extends BaseServiceImpl<MenuDao, Menu> implements MenuService {

    @Override
    @Cacheable(cacheNames = "menuList")
    public List<Menu> selectBatchIds(List<String> idList) {
        return super.selectBatchIds(idList);
    }

}
