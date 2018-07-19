package com.taotao.service;

import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.pojo.BasePojo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @Title: TaoTao
 * @Description: 所有服务层的基类
 * @Company:www.keyonecn.com
 * @author:fzw
 * @date:2018/7/19 9:42
 * @version:1.0
 */
public abstract class BaseService<T extends BasePojo>
{
    //使用spring4.0之后的特有属性泛型注入
    @Autowired
    private Mapper<T> mapper;


    //使用泛型
    //public abstract Mapper<T> getMapper();

    public T queryById(Long id)
    {
        /**
         * @Author:fzw
         * @Description: 根据id查询数据
         * @param:id
         * @Date:2018/7/19 14:35
         */
        return mapper.selectByPrimaryKey(id);
    }


    public List<T> queryAll()
    {
        /**
         * @Author:fzw
         * @Description: 查询所有数据
         * @param:
         * @Date:2018/7/19 14:35
         */
        return mapper.select(null);
    }

    public T queryOne(T record)
    {
        /**
         * @Author:fzw
         * @Description: 更具查询一条数据 如果查出多个会抛出异常
         * @param:
         * @Date:2018/7/19 14:35
         */
        return mapper.selectOne(record);
    }

    public List<T> queryListByWhere(T record)
    {
        /**
         * @Author:fzw
         * @Description: 根据条件查询集合数据
         * @param:
         * @Date:2018/7/19 14:35
         */
        return mapper.select(record);
    }


    public PageInfo<T> queryPageListByWhere(T record, Integer page, Integer rows)
    {
        /**
         * @Author:fzw
         * @Description:根据条件分页查询数据
         * @param:record
         * @param:page
         * @param:rows
         * @Date:2018/7/19 15:09
         */
        //设置分页参数
        PageHelper.startPage(page, rows);
        List<T> list = mapper.select(record);
        return new PageInfo<T>(list);
    }


    public Integer save(T t)
    {
        /**
         * @Author:fzw
         * @Description:新增数据
         * @param:null
         * @Date:2018/7/19 15:11
         */
        t.setCreated(new Date());
        t.setUpdated(t.getCreated());
        return mapper.insert(t);
    }

    public Integer saveSelective(T t)
    {
        /**
         * @Author:fzw
         * @Description:选择不为null的字段插入数据
         * @param:null
         * @Date:2018/7/19 15:11
         */
        t.setCreated(new Date());
        t.setUpdated(t.getCreated());
        return mapper.insertSelective(t);
    }


    public Integer update(T t)
    {
        /**
         * @Author:fzw
         * @Description:修改对应id全部数据
         * @param:null
         * @Date:2018/7/19 15:11
         */
        t.setUpdated(new Date());
        return mapper.updateByPrimaryKey(t);
    }

    public Integer updateSelective(T t)
    {
        /**
         * @Author:fzw
         * @Description:选择不为null的字段修改数据对应id
         * @param:null
         * @Date:2018/7/19 15:11
         */
        t.setUpdated(new Date());
        t.setCreated(null);   //创建时间永远不会被更新
        return mapper.updateByPrimaryKeySelective(t);
    }


    public Integer deleteById(Long id)
    {
        /**
         * @Author:fzw
         * @Description:根据主键删除数据
         * @param:null
         * @Date:2018/7/19 15:11
         */
        return mapper.deleteByPrimaryKey(id);
    }


    public Integer deleteByIds(List<Object> ids, Class<T> clazz, String property)
    {
        /**
         * @Author:fzw
         * @Description:删除多条数据
         * @param:null
         * @Date:2018/7/19 15:11
         */
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, ids);
        return mapper.deleteByExample(example);
    }

    public Integer deleteBywhere(T record)
    {
        /**
         * @Author:fzw
         * @Description:根据条件删除数据
         * @param:null
         * @Date:2018/7/19 15:11
         */
        return mapper.delete(record);
    }
}