package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转Controller
 * <p>Title: PageController</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2016-11-21下午8:11:42
 * @version  1.0
 */
@Controller
public class PageController
{
	/**
	 * 开始首页
	 */
	@RequestMapping("/")
	public String showIndex()
	{

		return "index";
	}

	/**
	 * 单个管理页面的跳转
	 * <p>Title: showPage</p>
	 * <p>Description: </p>
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page)
	{
		return page;
	}
}
