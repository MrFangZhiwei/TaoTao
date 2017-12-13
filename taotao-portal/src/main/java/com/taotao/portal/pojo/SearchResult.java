package com.taotao.portal.pojo;

import java.util.List;

public class SearchResult
{
	//商品列表
	private List<Item> list;
	//总记录数
	private long recordCount;
	//当前的总页数
	private long pageCount;
	//当前页页码
	private long curPage;

	public List<Item> getList()
	{
		return list;
	}

	public void setList(List<Item> list)
	{
		this.list = list;
	}

	public long getRecordCount()
	{
		return recordCount;
	}

	public void setRecordCount(long recordCount)
	{
		this.recordCount = recordCount;
	}

	public long getPageCount()
	{
		return pageCount;
	}

	public void setPageCount(long pageCount)
	{ 
		this.pageCount = pageCount;
	}

	public long getCurPage()
	{
		return curPage;
	}

	public void setCurPage(long curPage)
	{
		this.curPage = curPage;
	}

}
