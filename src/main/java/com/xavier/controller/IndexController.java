package com.xavier.controller;

import com.xavier.bean.*;
import com.xavier.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class IndexController {

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
	 * 首页
	 *
	 * @param modelAndView
	 * @return
	 */
	@RequiresPermissions({"sys:root:index"})
	@RequestMapping(path = "/index", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView indexPage(ModelAndView modelAndView, Menu menu) {
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
		List<Menu> menuList = menuService.selectBatchIds(
				permissionMenuList.stream().map(pm -> pm.getMenuId()).collect(Collectors.toList())
		);
		modelAndView.addObject("currentUser", user);
		modelAndView.addObject("menuList", menuList);
		modelAndView.setViewName("index");
		return modelAndView;
	}

	/**
	 * 默认展示页面
	 *
	 * @param modelAndView
	 * @return
	 */
	@RequiresPermissions({"sys:root:index"})
	@RequestMapping(path = "/default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView defaultViewpage(ModelAndView modelAndView) {
		modelAndView.setViewName("default");
		return modelAndView;
	}
}
