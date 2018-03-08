package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.JsonUtils;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 商品信息管Service
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-3-12下午6:50:51
 * @version  1.0
 */
@Service
public class ItemServiceImpl implements ItemService
{
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	@Value("${ITEM_DESC_URL}")
	private String ITEM_DESC_URL;
	@Value("${ITEM_PARAM_URL}")
	private String ITEM_PARAM_URL;
	/**
	 * 取商品基本信息
	 * <p>Title: getItemDesc</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @return
	 */
	@Override
	public ItemInfo getItemById(Long itemId)
	{
		try
		{
			//调用rest的服务查询商品基本信息
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
			if (!StringUtils.isBlank(json))
			{
				TaotaoResult result = TaotaoResult.formatToPojo(json, ItemInfo.class);
				ItemInfo itemInfo = (ItemInfo) result.getData();
				return itemInfo;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取商品描述
	 * <p>Title: getItemDesc</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @return
	 */
	@Override
	public String getItemDesc(Long itemId)
	{
		try
		{
			//调用rest的服务查询商品基本信息
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
			TaotaoResult result = TaotaoResult.formatToPojo(json, TbItemDesc.class);
			if (result.getStatus() == 200)
			{
				TbItemDesc itemDesc = (TbItemDesc) result.getData();
				return itemDesc.getItemDesc();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取商品类别
	 * <p>Title: getItemParam</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @return
	 */
	public String getItemParam(Long itemId)
	{
		
		try
		{
			//调用rest的服务查询商品基本信息
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);
			TaotaoResult result = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
			if (result.getStatus() == 200)
			{
				TbItemParamItem itemParam = (TbItemParamItem) result.getData();
				String paramData=itemParam.getParamData();
				//生成html
				//将paramDatd转json对象
				StringBuffer sb=new StringBuffer();
				List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
				sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\"class=\"Ptable\">\n");
						sb.append("    <tbody>\n");
						for(Map m1:jsonList){
							sb.append("        <tr>\n");
							sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
							sb.append("        </tr> \n");
							List<Map> jsonList2= (List<Map>) m1.get("params");
							for(Map m2:jsonList2){
								sb.append("  <tr>\n");
								sb.append("      <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
								sb.append("      <td>"+m2.get("v")+"</td>\n");
								sb.append("  </tr>\n");
							}
						}
						sb.append("    </tbody>\n");
						sb.append("</table>");
						//返回html片段
				return sb.toString();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}

}
