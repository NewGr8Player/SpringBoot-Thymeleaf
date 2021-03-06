package com.xavier.controller;

import com.xavier.bean.*;
import com.xavier.common.util.PasswordUtil;
import com.xavier.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private UserService userService;

	/**
	 * 跳转登陆页面
	 *
	 * @return
	 */
	@GetMapping(path = "/login")
	public ModelAndView loginPage(ModelAndView modelAndView) {
		modelAndView.setViewName("login");
		return modelAndView;
	}

	/**
	 * 登陆方法
	 *
	 * @param user
	 * @return
	 */
	@PostMapping(path = "/login")
	public ModelAndView loginMethod(ModelAndView modelAndView, User user) {
		if (null != user && StringUtils.isNotBlank(user.getUsername()) && StringUtils.isNotBlank(user.getPassword())) {
			try {
				User dbUser = userService.findByUserName(user.getUsername());
				if (null == dbUser) {
					modelAndView.setViewName("login");
					modelAndView.addObject("message", "用户不存在!");
				} else {
					if (PasswordUtil.passwordValidator(user.getPassword(), dbUser.getPassword())) {
						Subject subject = SecurityUtils.getSubject();
						subject.login(new UsernamePasswordToken(dbUser.getUsername(), dbUser.getPassword()));
						modelAndView.setViewName("redirect:/index?menuCode=root");
					} else {
						modelAndView.setViewName("login");
						modelAndView.addObject("message", "密码错误！");
					}
				}
			} catch (AuthenticationException authException) {
				modelAndView.setViewName("login");
				modelAndView.addObject("message", "用户认证失败!");
			} catch (Exception exception) {
				modelAndView.setViewName("login");
				modelAndView.addObject("message", "系统内部错误，请联系管理员!");
				exception.printStackTrace();
			}
		} else {
			modelAndView.setViewName("login");
			modelAndView.addObject("message", "请正确填写用户名与密码");
		}
		return modelAndView;
	}

	/**
	 * 注销登录
	 *
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(path = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView logoutMethod(ModelAndView modelAndView) {
		modelAndView.setViewName("login");
		return modelAndView;
	}
}
