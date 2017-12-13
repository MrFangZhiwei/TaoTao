package com.keyone.mapper;

import com.keyone.pojo.KoParameter;
import com.keyone.pojo.KoParameterExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KoParameterMapper {
    int countByExample(KoParameterExample example);

    int deleteByExample(KoParameterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(KoParameter record);

    int insertSelective(KoParameter record);

    List<KoParameter> selectByExample(KoParameterExample example);

    KoParameter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") KoParameter record, @Param("example") KoParameterExample example);

    int updateByExample(@Param("record") KoParameter record, @Param("example") KoParameterExample example);

    int updateByPrimaryKeySelective(KoParameter record);

    int updateByPrimaryKey(KoParameter record);
}