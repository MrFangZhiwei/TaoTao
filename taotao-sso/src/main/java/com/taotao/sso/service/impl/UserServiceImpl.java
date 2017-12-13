package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.JsonUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.UserService;

/**
 * 用户管理Service
 * <p>Title: UserServiceImpl</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-3-4下午1:06:03
 * @version  1.0
 */
@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;
	//加载Resourse.properties文件
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIR;

	/**
	 * 数据校验
	 * <p>Title: checkData</p>
	 * <p>Description: </p>
	 * @param content
	 * @param type
	 * @return
	 */
	@Override
	public TaotaoResult checkData(String content, Integer type)
	{
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		//创建查询条件
		//数据进行校验1、2、3分别代表username,phone.email
		if (1 == type)
		{
			//用户名校验
			criteria.andUsernameEqualTo(content);
		} else if (2 == type)
		{
			//电话校验
			criteria.andPhoneEqualTo(content);
		} else if (3 == type)
		{
			//email校验
			criteria.andEmailEqualTo(content);
		}
		//执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		if (list == null || list.size() == 0)
		{
			//可用返回true
			return TaotaoResult.ok(true);
		}
		//不可用返回false
		return TaotaoResult.ok(false);
	}

	/**
	 * 注册用户
	 * <p>Title: createUser</p>
	 * <p>Description: </p>
	 * @param user
	 * @return
	 */
	@Override
	public TaotaoResult createUser(TbUser user)
	{
		user.setUpdated(new Date());
		user.setCreated(new Date());
		//MD5加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userMapper.insert(user);
		return TaotaoResult.ok();
	}

	/**
	 * 用户登录写入redis
	 * <p>Title: userLogin</p>
	 * <p>Description: </p>
	 * @param name
	 * @param password
	 * @return
	 */
	@Override
	public TaotaoResult userLogin(String username, String password,HttpServletRequest request,HttpServletResponse response)
	{
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		//没有查询到结果
		if (list.size() == 0 || list == null)
		{
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		TbUser user = list.get(0);
		//比对密码
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword()))
		{
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		//生成token	
		String token = UUID.randomUUID().toString();
		//在保存用户之前,把用户对象中的密码清空
		user.setPassword(null);
		//把用户信息写入redis
		jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
		//设置session的过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIR);
		//添加写cookie的逻辑,cookie的有效期是关闭浏览器就失效
		CookieUtils.setCookie(request, response, "TT_TOKEN", token);
		//返回token
		return TaotaoResult.ok(token);
	}

	/**
	 * 根据token获取用户信息
	 * <p>Title: getUserByToken</p>
	 * <p>Description: </p>
	 * @param token
	 * @return
	 */
	@Override
	public TaotaoResult getUserByToken(String token)
	{
		//根据tokenredis中获取用户信息
		String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
		//判断json是否空
		if (StringUtils.isBlank(json))
		{
			return TaotaoResult.build(400, "此session已经过期,器重新登录");
		}
		//更新过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIR);
		return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}

}
