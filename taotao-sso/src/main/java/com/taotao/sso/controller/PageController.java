package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SSO系统页面跳转controller
 * <p>Title: PageController</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-3-4下午11:39:28
 * @version  1.0
 */
@Controller
@RequestMapping("/page")
public class PageController
{
	@RequestMapping("/showRegister")
	public String showRegister()
	{
		return "register";
	}

	@RequestMapping("/showLogin")
	public String showLogin(String redirect, Model model)
	{
		//实现回调页面跳转		
		model.addAttribute("redirect", redirect);
		return "login";
	}
}
