package com.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;

/**
 * 搜索Service
 * <p>Title: SearchServiceImpl</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-3-8下午7:35:38
 * @version  1.0
 */
@Service
public class SearchServiceImpl implements SearchService
{
	@Autowired
	private SearchDao searchDao;

	public SearchResult search(String queryString, int page, int rows) throws Exception
	{
		//创建查询条件
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery(queryString);
		//设置查询分页
		query.setStart((page - 1) * rows);
		//设置记录数
		query.setRows(rows);
		//设置默认搜索域
		query.set("df", "item_keywords");
		//设置高亮实现
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		//执行查询
		SearchResult result = searchDao.search(query);
		//查询结果总页数
		long recordCount = result.getRecordCount();
		long pageCount = recordCount / rows;
		//如果余数大于0页码加1
		if (recordCount % rows > 0)
		{
			pageCount++;
		}
		//设置总页数
		result.setPageCount(pageCount);
		//设置当前页
		result.setCurPage(page);
		return result;
	}

}
