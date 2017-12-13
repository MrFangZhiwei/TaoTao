package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;

/**
 * 内容分类管理
 * <p>Title: TbContentCategoryControl</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-2-28下午7:56:34
 * @version  1.0
 */
@SuppressWarnings("unused")
@Controller
@RequestMapping("/content/category")
public class TbContentCategoryController
{
	@Autowired
	private ContentCategoryService categoryService;
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getCategoryList(@RequestParam(value="id",defaultValue="0")Long parentId){
		List<EUTreeNode> list = categoryService.getCategoryList(parentId);
		return list;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult insertContentCategory(Long parentId,String name){
		TaotaoResult result = categoryService.insertContentCategory(parentId, name);
		return result;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContentCategory(Long parentId,Long id){
		TaotaoResult result = categoryService.deleteContentCategory(parentId, id);
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult updateContentCategory(Long id,String name){
		TaotaoResult result = categoryService.updateContentCategory(id, name);
		return result;
	}
}
