package com.xavier.config.advice;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.exceptions.TemplateProcessingException;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理Controller
 *
 * @author NewGr8Player
 */
@ControllerAdvice
public class ExceptionController {

	/**
	 * 错误页面
	 */
	public static final String ERROR_PAGE = "/error/error.html";

	@Value("${system.title}")
	private String SYSTEM_TITLE;
	@Value("${system.short-name}")
	private String SYSTEM_SHORT_NAME;
	/**
	 * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}

	/**
	 * 把值绑定到ModelAndView中，使全局@RequestMapping可以获取到该值
	 *
	 * @param modelAndView
	 */
	@ModelAttribute
	public void addAttributes(ModelAndView modelAndView) {
		modelAndView.addObject("SYSTEM_TITLE", SYSTEM_TITLE);
		modelAndView.addObject("SYSTEM_SHORT_NAME", SYSTEM_SHORT_NAME);
		//TODO 将全局内容放在这里
	}

	/**
	 * 权限异常
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = UnauthorizedException.class)
	public ModelAndView unauthorizedHandler(HttpServletRequest request, Exception ex) {
		return commonHandler(ERROR_PAGE, "403", ex.getMessage());
	}

	/**
	 * 全局异常捕捉处理
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = {Exception.class, TemplateProcessingException.class})
	public ModelAndView errorHandler(HttpServletRequest request, Exception ex) {
		return commonHandler(ERROR_PAGE, "500", ex.getMessage());
	}

	/**
	 * 通用处理
	 *
	 * @param viewName
	 * @param code
	 * @param message
	 * @return
	 */
	private ModelAndView commonHandler(String viewName, String code, String message) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("code", code);
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}