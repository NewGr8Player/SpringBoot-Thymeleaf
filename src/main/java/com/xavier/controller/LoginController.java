package com.xavier.controller;

import com.xavier.bean.User;
import com.xavier.common.util.PasswordUtil;
import com.xavier.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.PoolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView loginMethod(User user) {
        ModelAndView modelAndView = new ModelAndView();
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
                        modelAndView.setViewName("index");
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


}
