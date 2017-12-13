package com.taotao.common.pojo;

/**
 * EasyUI树形控件节点
 * <p>Title: EUTreeNode</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-2-1下午8:51:39
 * @version  1.0
 */
public class EUTreeNode
{
	private long id;
	private String text;
	private String state;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

}
