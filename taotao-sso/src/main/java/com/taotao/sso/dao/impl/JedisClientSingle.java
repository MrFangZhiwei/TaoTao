package com.taotao.sso.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.taotao.sso.dao.JedisClient;

/**
 * 单机版jedis类
 * <p>Title: JedisClientImpl</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-3-4上午9:12:38
 * @version  1.0
 */
public class JedisClientSingle implements JedisClient
{
	@Autowired
	private JedisPool jedisPool;
	@Override
	public String get(String key)
	{
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get(key);
		jedis.close();
		return string;
	}

	@Override
	public String set(String key, String value)
	{
		Jedis jedis = jedisPool.getResource();
		String string = jedis.set(key,value);
		jedis.close();
		return string;

	}

	@Override
	public String hget(String hkey, String key)
	{
		Jedis jedis = jedisPool.getResource();
		String string = jedis.hget(hkey,key);
		jedis.close();
		return string;

	}

	@Override
	public long hset(String heky, String key, String value)
	{
		Jedis jedis = jedisPool.getResource();
		Long hset = jedis.hset(heky, key, value);
		jedis.close();
		return hset;
	}

	@Override
	public long incr(String key)
	{
		Jedis jedis = jedisPool.getResource();
		Long incr = jedis.incr(key);
		jedis.close();
		return incr;
	}

	@Override
	public long expire(String key, Integer second)
	{
		Jedis jedis = jedisPool.getResource();
		Long expire = jedis.expire(key, second);
		jedis.close();
		return expire;
	}

	@Override
	public long ttl(String key)
	{
		Jedis jedis = jedisPool.getResource();
		Long ttl = jedis.ttl(key);
		jedis.close();
		return ttl;
	}

}
