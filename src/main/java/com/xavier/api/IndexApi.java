package com.xavier.api;

import com.xavier.bean.*;
import com.xavier.common.structure.TreeNode;
import com.xavier.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class IndexApi {

	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RolePermissionService rolePermissionService;
	@Autowired
	private PermissionMenuService permissionMenuService;

	/**
	 * 当前菜单
	 *
	 * @param menu
	 * @return
	 */
	@RequiresPermissions({"sys:root:index"})
	@RequestMapping(path = "/modelMenu", method = {RequestMethod.GET, RequestMethod.POST})
	public List<TreeNode> searchModelMenu(Menu menu) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		List<UserRole> userRoleList = userRoleService.findByUserId(user.getId());
		List<Role> roleList = roleService.selectBatchIds(
				userRoleList.stream().map(usr -> usr.getRoleId()).collect(Collectors.toList())
		);
		List<RolePermission> rolePermissionList = rolePermissionService.findByBatchRoleIds(
				roleList.stream().map(rp -> rp.getId()).collect(Collectors.toList())
		);
		List<Permission> permissionList = permissionService.selectBatchIds(
				rolePermissionList.stream().map(p -> p.getPermissionId()).collect(Collectors.toList())
		);
		List<PermissionMenu> permissionMenuList = permissionMenuService.findByBatchPermissionIds(
				permissionList.stream().map(p -> p.getId()).collect(Collectors.toList())
		);
		return menuService.selectMenuTree(menu, permissionMenuList.stream().map(pm -> pm.getMenuId()).collect(Collectors.toList()));
	}

}
