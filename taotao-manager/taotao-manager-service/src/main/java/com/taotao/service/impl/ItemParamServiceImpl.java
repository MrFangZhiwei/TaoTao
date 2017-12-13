package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;
/**
 * 类目选择
 * <p>Title: ItemParamServiceImpl</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-2-3下午5:30:19
 * @version  1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService
{
	@Autowired
	private TbItemParamMapper itemParamMapper;

	/**
	 * 根据商品类别获取模板json
	 * <p>Title: getItemParamByCid</p>
	 * <p>Description: </p>
	 * @param cid
	 * @return
	 */
	@Override
	public TaotaoResult getItemParamByCid(long cid)
	{
		// TODO Auto-generated method stub
		TbItemParamExample example=new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否查询到结果
		if(list !=null&&list.size()>0){
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insetItemParm(TbItemParam itemParam)
	{
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

}
