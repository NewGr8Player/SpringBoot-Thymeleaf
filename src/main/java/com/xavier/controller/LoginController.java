package com.xavier.controller;

import com.xavier.bean.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginController {

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@PostMapping("/login")
	public ModelAndView loginMethod(User user) {
		ModelAndView modelAndView = new ModelAndView();
		if (null != user && StringUtils.isNotBlank(user.getUsername()) && StringUtils.isNotBlank(user.getPassword())) {
			Subject subject = SecurityUtils.getSubject();
			try {
				subject.login(new UsernamePasswordToken(user.getUsername(), user.getEncryptedPassword()));
				modelAndView.setViewName("index");
			} catch (AuthenticationException authException) {
				modelAndView.setViewName("login");
				modelAndView.addObject("message", "用户不存在或用户名密码错误!");
			} finally {
				return modelAndView;
			}
		}
		modelAndView.setViewName("login");
		return modelAndView;
	}


}
