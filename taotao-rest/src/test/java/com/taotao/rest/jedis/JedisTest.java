package com.taotao.rest.jedis;

import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * Jedis测试类
 * <p>Title: JedisTest</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-3-3下午6:53:58
 * @version  1.0
 */

public class JedisTest
{
	@Test
	public void testJedisSingle()
	{
		//创建jedis的对象
		Jedis jedis = new Jedis("10.2.54.2", 6379);
		//直接调用jedis对象的方法, 方法名称和redis的命令一致
		jedis.set("key1", "jedis test");
		String string = jedis.get("key1");
		System.out.println(string);
		//关闭jedis
		jedis.close();
	}

	/**
	 * 使用连接池
	 */
	@Test
	public void testJedisPool()
	{
		//创建jedis连接池
		JedisPool pool = new JedisPool("10.2.54.2", 6379);
		//从连接池中获取jedis对象
		Jedis jedis = pool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		//关闭jedis
		jedis.close();
		pool.close();
	}

	/**
	 * 测试JedisCluster
	 * <p>Title: testJedisCluster</p>
	 * <p>Description: </p>
	 */
	@Test
	public void testJedisCluster()
	{
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("10.2.54.2", 7001));
		nodes.add(new HostAndPort("10.2.54.2", 7002));
		nodes.add(new HostAndPort("10.2.54.2", 7003));
		nodes.add(new HostAndPort("10.2.54.2", 7004));
		nodes.add(new HostAndPort("10.2.54.2", 7005));
		nodes.add(new HostAndPort("10.2.54.2", 7006));
		//创建jedis集群连接类
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("key1", "1000");
		String string = cluster.get("key1");
		System.out.println(string);
		cluster.close();
	}
	@Test
	public void testSpringJedisSingle(){
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
		Jedis jedis = pool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
		pool.close();
		
	}
}
