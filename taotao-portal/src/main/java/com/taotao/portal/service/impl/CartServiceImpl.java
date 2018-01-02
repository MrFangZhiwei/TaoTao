package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车Service
 * <p>Title: CarServiceImpl</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-3-14下午12:34:00
 * @version  1.0
 */
@Service
public class CartServiceImpl implements CartService
{
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;

	/**
	 * 添加购物车商品
	 * <p>Title: addCartItem</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @param num
	 * @return
	 */
	@Override
	public TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response)
	{
		//取商品信息
		CartItem cartItem = null;
		//取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		//判断购物车商品列表中是否存在此商品
		for (CartItem cItem : itemList)
		{
			//如果存在此商品
			if (cItem.getId() == itemId)
			{
				//增加商品数量
				cItem.setNum(cItem.getNum() + num);
				cartItem = cItem;
				break;
			}
		}
		//如果购物车没有这个商品
		if (cartItem == null)
		{
			cartItem = new CartItem();
			//根据商品id查询商品的基本信息
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
			//将json转换为java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItem.class);
			if (taotaoResult.getStatus() == 200)
			{
				TbItem tbItem = (TbItem) taotaoResult.getData();
				cartItem.setId(tbItem.getId());
				cartItem.setTitle(tbItem.getTitle());
				cartItem.setImage(tbItem.getImage() == null ? "" : tbItem.getImage().split(",")[0]);
				cartItem.setNum(num);
				cartItem.setPrice(tbItem.getPrice());
			}
			//添加入商品列表 
			itemList.add(cartItem);
		}
		// 把购物车列表写入Cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
		return TaotaoResult.ok();
	}

	/**
	 * 从Cookie中取商品列表
	 * <p>Title: getCartItemList</p>
	 * <p>Description: </p>
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<CartItem> getCartItemList(HttpServletRequest request)
	{
		//从cookie中取商品列表
		String cartJson = CookieUtils.getCookieValue(request, "TT_CART", true);
		if (StringUtils.isBlank(cartJson))
		{
			return new ArrayList<>();
		}
		try
		{
			//把json转换为商品列表
			List<CartItem> list = JsonUtils.jsonToList(cartJson, CartItem.class);
			return list;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	/**
	 * 拿出购物车内的商品
	 * <p>Title: getCatItemList</p>
	 * <p>Description: </p>
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response)
	{
		List<CartItem> itemList = getCartItemList(request);

		return itemList;
	}

	/**
	 * 删除购物车商品
	 * <p>Title: deleteCarItem</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	public TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response)
	{
		//从cookie中取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		//从列表中找到此商品
		for (CartItem cartItem : itemList)
		{
			if (cartItem.getId() == itemId)
			{
				itemList.remove(cartItem);
				break;
			}

		}
		//将购物车列表重新写入Cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
		
		
		return TaotaoResult.ok();
	}

}
