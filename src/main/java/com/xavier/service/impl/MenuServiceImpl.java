package com.xavier.service.impl;

import com.xavier.bean.Menu;
import com.xavier.common.service.impl.BaseServiceImpl;
import com.xavier.dao.MenuDao;
import com.xavier.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl extends BaseServiceImpl<MenuDao, Menu> implements MenuService {
}
