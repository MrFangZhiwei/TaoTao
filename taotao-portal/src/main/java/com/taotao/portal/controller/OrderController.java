package com.taotao.portal.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.pojo.TbUser;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.OrderService;

/**
 * 订单确认展示页面控制
 * <p>Title: OrderController</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-3-16上午9:34:53
 * @version  1.0
 */
@Controller
@RequestMapping("/order")
public class OrderController
{

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;

	/**
	 * 显示购物车列表
	 * <p>Title: showOrderCart</p>
	 * <p>Description: </p>
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/order-cart")
	public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		//取购物车商品列表
		List<CartItem> list = cartService.getCartItemList(request, response);
		//传递给页面
		model.addAttribute("cartList", list);
		return "order-cart";
	}
	/**
	 * 提交订单
	 * <p>Title: createOrder</p>
	 * <p>Description: </p>
	 * @param order
	 * @param model
	 * @return
	 */
	@RequestMapping("/create")
	public String createOrder(Order order, Model model,HttpServletRequest request)
	{
		try
		{
			//从Request中取用户信息
			TbUser user= (TbUser) request.getAttribute("user");
			//补全用户信息
			order.setUserId(user.getId());
			order.setBuyerNick(user.getUsername());
			//调用服务
			String orderId = orderService.createOrder(order);
			model.addAttribute("orderId", orderId);
			model.addAttribute("payment", order.getPayment());
			//返回三天后的日期
			model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
			return "success";
		} catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("message", "创建订单出错。请稍后重试!");
			return "error/exception";
		}
	}
}
