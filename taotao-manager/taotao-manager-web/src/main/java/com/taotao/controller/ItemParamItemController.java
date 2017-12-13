package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.service.ItemParamItemService;

@Controller
public class ItemParamItemController
{
	@Autowired
	private ItemParamItemService itemParamItemService;

	@RequestMapping("/showitem/{itemId}")
	public String p(@PathVariable long itemId,Model model)
	{
		String paramData = itemParamItemService.getItemParamItemById(itemId);
		model.addAttribute("itemParam", paramData);
		return "item";

	}
}
