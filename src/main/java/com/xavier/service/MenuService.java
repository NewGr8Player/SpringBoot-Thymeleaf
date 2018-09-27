package com.xavier.service;

import com.xavier.bean.Menu;
import com.xavier.common.service.BaseService;
import com.xavier.common.structure.TreeNode;

import java.util.List;

/**
 * 系统菜单Service
 *
 * @author NewGr8Player
 */
public interface MenuService extends BaseService<Menu> {

	/**
	 * <p>
	 * 根据 entity 条件，查询全部记录
	 * </p>
	 *
	 * @param menu 实体对象
	 * @param ids  id数组
	 * @return List&lt;Menu&gt;
	 */
	List<TreeNode> selectMenuTree(Menu menu, List<String> ids);
}
