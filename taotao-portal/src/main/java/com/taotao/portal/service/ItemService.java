package com.taotao.portal.service;

import com.taotao.portal.pojo.ItemInfo;

public interface ItemService
{
	ItemInfo getItemById(Long itemId);
	String getItemDesc(Long itemId);
	String getItemParam(Long itemId);
}