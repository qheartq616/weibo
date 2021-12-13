package org.example.weibo.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.weibo.pojo.SubCommentLike;
import org.example.weibo.pojo.SubCommentLikeExample;

import java.util.List;

public interface SubCommentLikeMapper {
    int countByExample(SubCommentLikeExample example);

    int deleteByExample(SubCommentLikeExample example);

    int deleteByPrimaryKey(Integer sclid);

    int insert(SubCommentLike record);

    int insertSelective(SubCommentLike record);

    List<SubCommentLike> selectByExample(SubCommentLikeExample example);

    SubCommentLike selectByPrimaryKey(Integer sclid);

    int updateByExampleSelective(@Param("record") SubCommentLike record, @Param("example") SubCommentLikeExample example);

    int updateByExample(@Param("record") SubCommentLike record, @Param("example") SubCommentLikeExample example);

    int updateByPrimaryKeySelective(SubCommentLike record);

    int updateByPrimaryKey(SubCommentLike record);
}