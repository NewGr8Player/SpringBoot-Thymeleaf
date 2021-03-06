package com.xavier.controller;

import com.xavier.bean.Role;
import com.xavier.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sys")
public class SysController {

	@Autowired
	private RoleService roleService;

	@RequiresPermissions(value = {"sys:set:view"})
	@RequestMapping(path = {"/role", "/role/list"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView roleListPage(ModelAndView modelAndView, Role role) {
		modelAndView.setViewName("role/roleList");
		return modelAndView;
	}

	@RequiresPermissions(value = {"sys:role:form"})
	@RequestMapping(path = "/role/form", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView roleEditPage(ModelAndView modelAndView, Role role) {
		if (null != role && StringUtils.isNotBlank(role.getId())) {
			modelAndView.addObject("role", roleService.selectById(role.getId()));
		}
		modelAndView.setViewName("role/roleForm");
		return modelAndView;
	}
}
