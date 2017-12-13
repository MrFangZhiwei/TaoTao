package com.taotao.order.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.dao.JedisClient;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

/**
 * 订单管理service
 * <p>Title: OrderServiceImpl</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-3-15下午9:14:22
 * @version  1.0
 */
@Service
public class OrderServiceImpl implements OrderService
{
	@Autowired
	private TbOrderMapper orderMapper;

	@Autowired
	private TbOrderItemMapper orderItemMapper;

	@Autowired
	private TbOrderShippingMapper orderShippingMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${ORDER_GEN_KEY}")
	private String ORDER_GEN_KEY;

	@Value("${ORDER_INIT_ID}")
	private String ORDER_INIT_ID;
	
	@Value("${ORDER_DETAIL_GEN_KEY}")
	private String ORDER_DETAIL_GEN_KEY;

	public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping)
	{
		//插入订单表
		//获得订单号
		String string = jedisClient.get(ORDER_GEN_KEY);
		if (StringUtils.isBlank(string))
		{
			jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
		}
		long orderId = jedisClient.incr(ORDER_GEN_KEY);

		//补全pojo属性
		order.setOrderId(orderId+"");
		//状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭',
		order.setStatus(1);
		order.setCreateTime(new Date());
		order.setUpdateTime(new Date());
		//0是未评价、1是评价
		order.setBuyerRate(0);
		//插入订单表数据库
		orderMapper.insert(order);
		//插入订单明细
		for (TbOrderItem tbOrderItem : itemList)
		{
			//补全订单明细
			//取订单明细id,不需要展现给用户不需要设置初始化值 直接从1开始就可以了
			long orderDetailId=jedisClient.incr(ORDER_DETAIL_GEN_KEY);
			//补全订单信息
			tbOrderItem.setOrderId(orderId+"");
			tbOrderItem.setId(orderDetailId+"");
			//向订单明细表插入订单明细数据
			orderItemMapper.insert(tbOrderItem);
		}
		//插入物流表
		//补全物流表的属性
		orderShipping.setOrderId(orderId+"");
		orderShipping.setCreated(new Date());
		orderShipping.setUpdated(new Date());
		//向物流表插入数据
		orderShippingMapper.insert(orderShipping);
		return TaotaoResult.ok(orderId);
	}

}
