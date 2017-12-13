package com.keyone.mapper;

import com.keyone.pojo.KoUser;
import com.keyone.pojo.KoUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KoUserMapper {
    int countByExample(KoUserExample example);

    int deleteByExample(KoUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(KoUser record);

    int insertSelective(KoUser record);

    List<KoUser> selectByExample(KoUserExample example);

    KoUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") KoUser record, @Param("example") KoUserExample example);

    int updateByExample(@Param("record") KoUser record, @Param("example") KoUserExample example);

    int updateByPrimaryKeySelective(KoUser record);

    int updateByPrimaryKey(KoUser record);
}