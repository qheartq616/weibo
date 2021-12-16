package org.example.weibo.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.weibo.pojo.Last;
import org.example.weibo.pojo.LastExample;

import java.util.List;

public interface LastMapper {
    int countByExample(LastExample example);

    int deleteByExample(LastExample example);

    int deleteByPrimaryKey(Integer lastId);

    int insert(Last record);

    int insertSelective(Last record);

    List<Last> selectByExample(LastExample example);

    Last selectByPrimaryKey(Integer lastId);

    int updateByExampleSelective(@Param("record") Last record, @Param("example") LastExample example);

    int updateByExample(@Param("record") Last record, @Param("example") LastExample example);

    int updateByPrimaryKeySelective(Last record);

    int updateByPrimaryKey(Last record);
}