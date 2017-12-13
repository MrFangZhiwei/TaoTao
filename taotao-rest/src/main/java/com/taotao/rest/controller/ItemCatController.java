package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * 商品分类列表
 * <p>Title: ItemCatControl</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-2-27下午2:52:11
 * @version  1.0
 */
@Controller
public class ItemCatController
{
	@Autowired
	private ItemCatService catService;

	/*	@RequestMapping(value="/itemcat/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
		@ResponseBody
		public String getItemCatList(String callback)
		{
			CatResult list = catService.getItemCatList();
			//将pojo转为json字符串
			String json = JsonUtils.objectToJson(list);
			//拼装返回值
			String result=callback+"("+json+")";
			return result;
		}*/

	@RequestMapping(value = "/itemcat/list")
	@ResponseBody
	public Object getItemCatList(String callback)
	{
		CatResult catResult = catService.getItemCatList();
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(catResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
}
