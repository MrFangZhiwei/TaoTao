package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.TbItemService;
import com.taotao.service.impl.TbItemServiceImpl;

/**
 * 商品管理Controler
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2016-11-21下午6:43:29
 * @version  1.0
 */
@Controller
public class ItemController
{
	@Autowired
	private TbItemService itemService;

	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId)
	{
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}

	@RequestMapping("/item/list")
	@ResponseBody
	public EUDDataGridResult getItemList(Integer page, Integer rows)
	{
		EUDDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}

	@RequestMapping(value = "/item/save", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createItem(TbItem item, String desc,String itemParams) throws Exception
	{
		TaotaoResult result = itemService.createItem(item, desc,itemParams);
		return result;
	}
	
	@RequestMapping(value ="/rest/item/delete",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteItem(long ids)
	{
		TaotaoResult result = itemService.deleteItem(ids);
		return result;
	}
	
	@RequestMapping(value ="/rest/item/query/item/desc/{itemid}")
	@ResponseBody
	public TaotaoResult selectItem(@PathVariable long itemid)
	{
		TaotaoResult result = itemService.selectItem(itemid);
		return result;
	}
	
	@RequestMapping(value ="/rest/item/param/item/query/{itemid}")
	@ResponseBody
	public TaotaoResult selectItemDesc(@PathVariable long itemid)
	{
		TaotaoResult result = itemService.selectItemDesc(itemid);
		return result;
	}
	
}
