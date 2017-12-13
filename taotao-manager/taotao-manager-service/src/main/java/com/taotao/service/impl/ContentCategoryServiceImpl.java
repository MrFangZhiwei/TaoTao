package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

/**
 * 内容分类管理
 * <p>Title: ContentCategoryServiceImpl</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-2-28下午7:46:39
 * @version  1.0
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService
{
	@Autowired
	private TbContentCategoryMapper categoryMapper;

	public List<EUTreeNode> getCategoryList(long parentId)
	{
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = categoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list)
		{
			//创建一个节点
			EUTreeNode node = new EUTreeNode();
			node.setId(tbContentCategory.getId());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			node.setText(tbContentCategory.getName());
			//将节点放入list<EUTreeNode>中
			resultList.add(node);
		}
		return resultList;
	}

	/**
	 * 插入ContentCategory
	 * <p>Title: insertContentCategory</p>
	 * <p>Description: </p>
	 * @param parentId
	 * @param name
	 * @return
	 */
	public TaotaoResult insertContentCategory(long parentId, String name)
	{
		//创建一个pojo
		TbContentCategory contentCategory = new TbContentCategory();
		//补全属性
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		//状态。可选值：1（正常）,2（删除）
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//添加记录
		categoryMapper.insert(contentCategory);
		//查看父节点的isParents是否true,如果不是true改成true
		//向上节点查询
		TbContentCategory category = categoryMapper.selectByPrimaryKey(parentId);
		if (!category.getIsParent())
		{
			category.setIsParent(true);
		}
		//更新父节点
		categoryMapper.updateByPrimaryKey(category);
		//返回结果
		return TaotaoResult.ok(contentCategory);
	}

	/**
	 * 删除ContentCategory
	 * <p>Title: insertContentCategory</p>
	 * <p>Description: </p>
	 * @param parentId
	 * @param name
	 * @return
	 */
	public TaotaoResult deleteContentCategory(long parentId, long id)
	{
		categoryMapper.deleteByPrimaryKey(id);
		TbContentCategory category = categoryMapper.selectByPrimaryKey(parentId);
		//得到父节点的id
		long newId = category.getId();
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(newId);
		List<TbContentCategory> list = categoryMapper.selectByExample(example);
		//删除后查看父节点是否还有子节点
		if (list.equals(null))
		{
			category.setIsParent(false);
		}
		//更新父节点
		categoryMapper.updateByPrimaryKey(category);

		return TaotaoResult.ok();
	}

	/**
	 * 修改ContentCategory
	 * <p>Title: insertContentCategory</p>
	 * <p>Description: </p>
	 * @param parentId
	 * @param name
	 * @return
	 */
	public TaotaoResult updateContentCategory(long id, String name)
	{
		TbContentCategory category = categoryMapper.selectByPrimaryKey(id);
		category.setName(name);
		categoryMapper.updateByPrimaryKey(category);
		return TaotaoResult.ok();
	}

}
