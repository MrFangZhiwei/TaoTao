package com.taotao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Title: TaoTao
 * @Description:
 * @Company:www.keyonecn.com
 * @author:fzw
 * @date:2018/3/8 8:43
 * @version:1.0
 */
public class PageHelperTset
{
    @Test
    public  void testPageTest(){
        //创建一个spring容器
        ApplicationContext  applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        //从spring容器中获得Mapper的代理对象
        TbItemMapper tbItemMapper = applicationContext.getBean(TbItemMapper.class);
        //执行查询，并分页
        TbItemExample tbItemExample = new TbItemExample();
        //分页处理
        PageHelper.startPage(1,10);
        List<TbItem> tbItemList = tbItemMapper.selectByExample(tbItemExample);
        //取商品列表
        for (TbItem tbItem : tbItemList)
        {
            System.out.println(tbItem.getTitle());
        }
        //取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItemList);
        System.out.println("总共商品数量"+pageInfo.getTotal());

    }
}