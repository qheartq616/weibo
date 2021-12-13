package org.example.weibo.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.weibo.pojo.SubComment;
import org.example.weibo.pojo.SubCommentExample;

import java.util.List;

public interface SubCommentMapper {
    int countByExample(SubCommentExample example);

    int deleteByExample(SubCommentExample example);

    int deleteByPrimaryKey(Integer scid);

    int insert(SubComment record);

    int insertSelective(SubComment record);

    List<SubComment> selectByExample(SubCommentExample example);

    SubComment selectByPrimaryKey(Integer scid);

    int updateByExampleSelective(@Param("record") SubComment record, @Param("example") SubCommentExample example);

    int updateByExample(@Param("record") SubComment record, @Param("example") SubCommentExample example);

    int updateByPrimaryKeySelective(SubComment record);

    int updateByPrimaryKey(SubComment record);
}