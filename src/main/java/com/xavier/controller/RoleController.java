package com.xavier.controller;

import com.xavier.bean.Role;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/role")
public class RoleController {

	@RequiresPermissions(value = {"sys:role:view"})
	@RequestMapping(path = {"","/list"},method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView roleListPage(ModelAndView modelAndView, Role role){
		System.out.println(role);
		modelAndView.setViewName("role/roleList");
		return modelAndView;
	}
}
