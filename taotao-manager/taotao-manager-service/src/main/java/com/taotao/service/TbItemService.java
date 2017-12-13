package com.taotao.service;

import com.taotao.common.pojo.EUDDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface TbItemService
{
	TbItem getItemById(long id);
	EUDDataGridResult getItemList(int page,int rows);
	TaotaoResult deleteItem(long itemid);
	TaotaoResult createItem(TbItem Item,String desc,String paramData) throws Exception;
	TaotaoResult selectItem(long itemid);
	TaotaoResult selectItemDesc(long itemid);

}
