package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.JsonUtils;
import com.taotao.pojo.TbOrder;
import com.taotao.portal.service.OrderService;

/**
 * 请求远程提交订单的服务
 * <p>Title: OrderServiceImpl</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-3-16下午12:30:48
 * @version  1.0
 */
@Service
public class OrderServiceImpl implements OrderService
{

	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;

	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;

	public String createOrder(TbOrder order)
	{
		//调用创建订单服务之前补全用户信息
		//从cookie中获取TT——TOKEN的内容,根据token调用sso系统的服务根据token换取用户信息
		
		String json = JsonUtils.objectToJson(order);
		//调用taotao-order的服务提交订单
		String String = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, json);
		//将json转换成taotaoResult类型(不带对象的转换)
		TaotaoResult taotaoResult = TaotaoResult.format(String);
		//根据json的实际内容加入强行转换的类型
		if (taotaoResult.getStatus() == 200)
		{
			//得到的不在是Long的是Integer
			Integer orderId = (Integer) taotaoResult.getData();
			return orderId.toString();
		}

		return "";
	}

}
