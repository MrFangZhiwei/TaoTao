package com.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.util.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import com.taotao.portal.service.impl.UserServiceImpl;

public class LoginInterceptor implements HandlerInterceptor
{
	@Autowired
	private UserServiceImpl userService;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception
	{
		//返回ModelView之后
		//反应用户之后
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception
	{
		//handler执行之后,返回ModelView之前

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception
	{
		//handler执行之前
		//返回值绝对handler是否执行。ture执行 false不执行
		//判断用户是否登录
		//从cookie中去token
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		//根据用户信息,调用sso系统的接口
		TbUser user = userService.getUserByToken(token);
		//取不到用户信息
		if (user == null)
		{
			//跳转到登陆界面，把请求的url作为参数传递给登录界面
			response.sendRedirect(userService.SSO_BASE_URL + userService.SSO_PAGE_LOGIN + "?redirect="
					+ request.getRequestURI());
			//返回false
			return false;
		}
		//取出用户信息
		//将用户信息放入Request
		request.setAttribute("user", user);
		//放行true
		return true;
	}

}
