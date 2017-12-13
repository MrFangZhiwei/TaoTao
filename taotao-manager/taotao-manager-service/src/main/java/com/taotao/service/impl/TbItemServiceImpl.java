package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.TbItemService;

/**
 * 
 * <p>Title: TbItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2016-11-21下午6:39:06
 * @version  1.0
 */
@Service("tbItemServiceImpl")
public class TbItemServiceImpl implements TbItemService
{
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	/**
	 * 测试通过商品id查询商品
	 * <p>Title: getItemById</p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 */
	@Override
	public TbItem getItemById(long id)
	{
		//TbItem tbItem = itemMapper.selectByPrimaryKey(id);
		TbItemExample example = new TbItemExample();
		Criteria createCriteria = example.createCriteria();
		//		添加查询条件
		createCriteria.andIdEqualTo(id);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0)
		{
			TbItem tbItem = list.get(0);
			return tbItem;
		}

		return null;
	}

	/**
	 * 查询商品列表
	 * <p>Title: getTbItem</p>
	 * <p>Description: </p>
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public EUDDataGridResult getItemList(int page, int rows)
	{
		//查询商品列表
		TbItemExample example = new TbItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		EUDDataGridResult result = new EUDDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	/**
	 * 插入商品参数
	 * <p>Title: createItem</p>
	 * <p>Description: </p>
	 * @param Item
	 * @param desc
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	@Override
	public TaotaoResult createItem(TbItem Item, String desc, String paramData) throws Exception
	{
		// 将item补全
		//生成商品ID
		long itemId = IDUtils.genItemId();
		Item.setId(itemId);
		//商品状态，1-正常，2-下架，3-删除'
		Item.setStatus((byte) 1);
		Item.setCreated(new Date());
		Item.setUpdated(new Date());
		//插入数据库
		itemMapper.insert(Item);
		//插入商品描述
		TaotaoResult result = insertItemDesc(itemId, desc);
		insertItemParam(itemId, paramData);
		if (result.getStatus() != 200)
		{
			throw new Exception();
		}
		return TaotaoResult.ok();
	}

	/**
	 * 插入商品描述
	 * <p>Title: insertItemDesc</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @param desc
	 * @return
	 */
	private TaotaoResult insertItemDesc(long itemId, String desc)
	{
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(new Date());
		itemDesc.setCreated(new Date());
		itemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}

	/**
	 * 插入规格参数到TbitemParamItem表中
	 * <p>Title: insertItemParam</p>
	 * <p>Description: </p>
	 * @param itemid
	 * @param paramData
	 * @return
	 */
	private TaotaoResult insertItemParam(long itemid, String paramData)
	{
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemid);
		itemParamItem.setParamData(paramData);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		itemParamItemMapper.insert(itemParamItem);
		return TaotaoResult.ok();
	}

	/**
	 * 删除商品
	 * <p>Title: deleteItem</p>
	 * <p>Description: </p>
	 * @param itemid
	 * @return
	 */
	@Override
	public TaotaoResult deleteItem(long itemid)
	{
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemid);
		itemMapper.deleteByExample(example);
		return TaotaoResult.ok();
	}
	/**
	 * 编辑商品中的查询商品
	 * <p>Title: selectItem</p>
	 * <p>Description: </p>
	 * @param itemid
	 * @return
	 */
	@Override
	public TaotaoResult selectItem(long itemid)
	{
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemid);
		itemMapper.selectByExample(example);
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult selectItemDesc(long itemid)
	{
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemid);
		itemMapper.selectByExample(example);
		return TaotaoResult.ok();
	}
}
