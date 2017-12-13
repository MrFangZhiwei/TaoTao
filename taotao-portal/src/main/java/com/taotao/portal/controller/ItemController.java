package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.Item;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;

@Controller
public class ItemController
{
	@Autowired
	private ItemService itemService;
	@RequestMapping("/item/{itemId}")
	public String getItemById(@PathVariable Long itemId,Model model){
		ItemInfo itemInfo = itemService.getItemById(itemId);
		
		model.addAttribute("item", itemInfo);
		return "item";
	}
	//ResponseBody里面如果有中文返回就会出现乱码，因为默认是用iso8859-1返回的
	@RequestMapping(value="/item/desc/{itemId}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId){
		String desc = itemService.getItemDesc(itemId);
		return desc;
	}
	
	@RequestMapping(value="/item/param/{itemId}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId){
		String param = itemService.getItemParam(itemId);
		return param;
	}
}
