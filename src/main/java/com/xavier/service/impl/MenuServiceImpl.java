package com.xavier.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xavier.bean.Menu;
import com.xavier.common.service.impl.BaseServiceImpl;
import com.xavier.common.structure.TreeNode;
import com.xavier.dao.MenuDao;
import com.xavier.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl extends BaseServiceImpl<MenuDao, Menu> implements MenuService {

	@Override
	@Cacheable(cacheNames = "channelMenuList")
	public List<Menu> selectBatchIds(List<String> idList) {
		return super.selectBatchIds(idList);
	}

	@Override
	@Cacheable(cacheNames = "modelMenuList")
	public List<TreeNode> selectMenuTree(Menu menu, List<String> ids) {
		EntityWrapper entityWrapper = new EntityWrapper();
		entityWrapper.setEntity(menu);

		if (StringUtils.isNotBlank(menu.getMenuType())) { /* menu_type */
			entityWrapper.eq("menu_type", menu.getMenuType());
		}
		if (StringUtils.isNotBlank(menu.getMenuCode())) { /* menu_code */
			entityWrapper.eq("menu_code", menu.getMenuCode());
		}
		entityWrapper.in("id",ids);
		List<Menu> menuList =  dao.selectList(entityWrapper);
		Map<String,List<Menu>> childMap = new HashMap<>();
		List<TreeNode> treeNodeList = new ArrayList<>();
		for(Menu it:menuList){
			if ("0".equals(it.getParentId())){
				treeNodeList.add(new TreeNode(it));
			} else {
				if(childMap.containsKey(it.getParentId())){
					List<Menu> childList = childMap.get(it.getParentId());
					childList.add(it);
					childMap.put(it.getParentId(),childList);
				} else {
					childMap.put(it.getParentId(), Arrays.asList(it));
				}
			}
		}
		for (TreeNode<Menu> treeNode : treeNodeList){
			String parentId = treeNode.getCurrent().getId();
			if(childMap.containsKey(parentId)){
				treeNode.setChild(childMap.get(parentId));
			}
		}
		return treeNodeList;
	}
}
