package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;

/**
 * 商品分类服务
 * <p>Title: ItemCatService</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-2-27下午2:15:36
 * @version  1.0
 */
@Service
public class ItemCatServiceImpl implements com.taotao.rest.service.ItemCatService
{

	@Autowired
	private TbItemCatMapper itemCatMapper;

	public CatResult getItemCatList()
	{
		CatResult catResult = new CatResult();
		//查询分类列表
		catResult.setData(getCatList(0));
		return catResult;
	}

	/**
	 * 查询分类列表
	 * <p>Title: getCatList</p>
	 * <p>Description: </p>
	 * @param parentId
	 * @return
	 */
	private List<?> getCatList(long parentId)
	{
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//返回值
		List resultList = new ArrayList<>();
		int count = 0;
		//构建list添加节点
		for (TbItemCat itemCat : list)
		{
			if (itemCat.getIsParent())
			{
				CatNode node = new CatNode();
				if (parentId == 0)
				{
					node.setName("<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
				} else
				{
					node.setName(itemCat.getName());
				}
				node.setUrl("/products/" + itemCat.getId() + ".html");
				node.setItem(getCatList(itemCat.getId()));
				resultList.add(node);
				count++;
				//第一层只取14条记录
				if (count >= 14 && parentId == 0)
				{
					break;
				}
			} else
			{
				//如果是叶子节点
				resultList.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName());
			}
		}
		return resultList;
	}
}
