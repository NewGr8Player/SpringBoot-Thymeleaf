package com.xavier.api;

import com.xavier.bean.Role;
import com.xavier.common.page.Page;
import com.xavier.common.structure.AjaxResult;
import com.xavier.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys")
public class SysApi {

	@Autowired
	private RoleService roleService;

	@RequiresPermissions(value = {"sys:role:view"})
	@RequestMapping(path = "/role/queryList", method = {RequestMethod.GET, RequestMethod.POST})
	public Page<Role> roleListQuery(Page<Role> rolePage, Role role) {
		return roleService.selectRoleListPage(rolePage, role);
	}

	@RequiresPermissions(value = {"sys:role:form"})
	@RequestMapping(path = "/role/save", method = {RequestMethod.GET, RequestMethod.POST})
	public AjaxResult roleSave(Role role) {
		if (roleService.insert(role)) {
			return AjaxResult.sucess("保存角色数据成功！");
		} else {
			return AjaxResult.sucess("保存角色数据失败！");
		}
	}
}
