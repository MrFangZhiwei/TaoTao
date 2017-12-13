package com.taotao.portal.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

/**
 * 商品搜索Controller
 * <p>Title: SearchController</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-3-9下午3:20:43
 * @version  1.0
 */
@Controller
public class SearchController
{
	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/search")
	public String search(@RequestParam("q") String queryString, @RequestParam(defaultValue = "1") Integer page,
			Model model) throws Exception 
	{
		//如果是Integer没有传来就是null，null是不能给空指针的
		//查询调用taotao_search返回的结果
		queryString=new String(queryString.getBytes("iso8859-1"),"utf-8");
		SearchResult searchResult = searchService.search(queryString, page);
		//配置视图模型
		model.addAttribute("query", queryString);
		model.addAttribute("totalPages", searchResult.getPageCount());
		//添加list对象让jstl来渲染出来
		model.addAttribute("itemList", searchResult.getList());
		model.addAttribute("page", page);
		return "search";
	}
}
