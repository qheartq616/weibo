package org.example.weibo.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.weibo.pojo.CommentLike;
import org.example.weibo.pojo.CommentLikeExample;

import java.util.List;

public interface CommentLikeMapper {
    int countByExample(CommentLikeExample example);

    int deleteByExample(CommentLikeExample example);

    int deleteByPrimaryKey(Integer clid);

    int insert(CommentLike record);

    int insertSelective(CommentLike record);

    List<CommentLike> selectByExample(CommentLikeExample example);

    CommentLike selectByPrimaryKey(Integer clid);

    int updateByExampleSelective(@Param("record") CommentLike record, @Param("example") CommentLikeExample example);

    int updateByExample(@Param("record") CommentLike record, @Param("example") CommentLikeExample example);

    int updateByPrimaryKeySelective(CommentLike record);

    int updateByPrimaryKey(CommentLike record);
}