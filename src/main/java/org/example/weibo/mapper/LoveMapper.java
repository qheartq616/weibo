package org.example.weibo.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.weibo.pojo.Love;
import org.example.weibo.pojo.LoveExample;

import java.util.List;

public interface LoveMapper {
    int countByExample(LoveExample example);

    int deleteByExample(LoveExample example);

    int deleteByPrimaryKey(Integer lid);

    int insert(Love record);

    int insertSelective(Love record);

    List<Love> selectByExample(LoveExample example);

    Love selectByPrimaryKey(Integer lid);

    int updateByExampleSelective(@Param("record") Love record, @Param("example") LoveExample example);

    int updateByExample(@Param("record") Love record, @Param("example") LoveExample example);

    int updateByPrimaryKeySelective(Love record);

    int updateByPrimaryKey(Love record);
}