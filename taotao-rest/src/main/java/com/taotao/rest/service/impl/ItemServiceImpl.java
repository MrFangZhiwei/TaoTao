package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;

/**
 * 商品信息管理Service
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-3-11下午10:18:56
 * @version  1.0
 */
@Service
public class ItemServiceImpl implements ItemService
{

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private JedisClient jedisClient;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;

	public TaotaoResult getItemBaseInfo(long itemId)
	{
		try
		{
			//添加缓存逻辑
			//从缓存中去商品信息,商品id对应的信息
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
			//判断是否有值
			if (!StringUtils.isBlank(json))
			{
				TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
				return TaotaoResult.ok(tbItem);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		//根据商品id查询商品信息
		TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
		//添加缓存不呢影响正常的业务逻辑所以要拦截异常
		try
		{
			//把商品信息写入缓存
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(tbItem));
			//设置key的有效期
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":base", REDIS_ITEM_EXPIRE);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		//使用TaotaoResult包装一下
		return TaotaoResult.ok(tbItem);
	}

	@Override
	public TaotaoResult getItemDesc(long itemId)
	{
		//添加缓存逻辑
		try
		{
			//把商品信息写入缓存
			//添加缓存逻辑
			//从缓存中去商品信息,商品id对应的信息
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
			//判断是否有值
			if (!StringUtils.isBlank(json))
			{
				TbItemDesc tbItemdesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return TaotaoResult.ok(tbItemdesc);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		//创建查询条件
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		//添加缓存不呢影响正常的业务逻辑所以要拦截异常
		try
		{
			//把商品信息写入缓存
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(itemDesc));
			//设置key的有效期
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXPIRE);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return TaotaoResult.ok(itemDesc);
	}

	public TaotaoResult getItemParam(long itemId)
	{
		//添加缓存逻辑
		try
		{
			//把商品信息写入缓存
			//添加缓存逻辑
			//从缓存中去商品信息,商品id对应的信息
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
			//判断是否有值
			if (!StringUtils.isBlank(json))
			{
				TbItemParamItem tbItemparam = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
				return TaotaoResult.ok(tbItemparam);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		//创建查询条件
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0)
		{
			TbItemParamItem itemParamItem = list.get(0);
			try
			{
				//添加缓存不呢影响正常的业务逻辑所以要拦截异常
				//把商品信息写入缓存
				jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJson(itemParamItem));
				//设置key的有效期
				jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param", REDIS_ITEM_EXPIRE);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			return TaotaoResult.ok(itemParamItem);
		}
		return TaotaoResult.build(400, "未查询到商品信息");
	}

}
