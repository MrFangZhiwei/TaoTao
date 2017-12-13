package com.taotao.search.mapper;

import java.util.List;

import com.taotao.search.pojo.Item;

/**
 * solr操作数据库
 * <p>Title: ItemMapper</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-3-8上午11:08:23
 * @version  1.0
 */
public interface ItemMapper
{
	List<Item> searchItemList();
}
