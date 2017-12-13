package com.taotao.rest.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisCluster;

import com.taotao.rest.dao.JedisClient;

public class JedisClientCluster implements JedisClient
{

	@Autowired
	private JedisCluster jedisCluster;
	@Override
	public String get(String key)
	{
		return jedisCluster.get(key);
	}

	@Override
	public String set(String key, String value)
	{
		return jedisCluster.set(key, value);
	}

	@Override
	public String hget(String hkey, String key)
	{
		return jedisCluster.hget(hkey, key);
	}

	@Override
	public long hset(String heky, String key, String value)
	{
		return jedisCluster.hset(heky, key, value);
	}

	@Override
	public long incr(String key)
	{
		return jedisCluster.incr(key);
	}

	@Override
	public long expire(String key, int second)
	{
		return jedisCluster.expire(key, (int)second);
	}

	@Override
	public long ttl(String key)
	{
		return jedisCluster.ttl(key);
	}

}
