package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

/**
 * httpclient doget调用search服务器
 * <p>Title: SearchServiceImpl</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-3-9下午3:19:34
 * @version  1.0
 */
@Service
public class SearchServiceImpl implements SearchService
{

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;

	public SearchResult search(String queryString, int page)
	{
		//调用taotao-search的服务
		Map<String, String> param = new HashMap<>();
		param.put("q", queryString);
		param.put("page", page + "");
		try
		{
			//调用服务
			String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
			//把字符串转为java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
			if (taotaoResult.getStatus() == 200)
			{
				SearchResult result = (SearchResult) taotaoResult.getData();
				return result;
			}

		} catch (Exception e)
		{

			e.printStackTrace();
		}

		return null;
	}

}
