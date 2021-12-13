package org.example.weibo.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.weibo.pojo.PostLike;
import org.example.weibo.pojo.PostLikeExample;

import java.util.List;

public interface PostLikeMapper {
    int countByExample(PostLikeExample example);

    int deleteByExample(PostLikeExample example);

    int deleteByPrimaryKey(Integer lid);

    int insert(PostLike record);

    int insertSelective(PostLike record);

    List<PostLike> selectByExample(PostLikeExample example);

    PostLike selectByPrimaryKey(Integer lid);

    int updateByExampleSelective(@Param("record") PostLike record, @Param("example") PostLikeExample example);

    int updateByExample(@Param("record") PostLike record, @Param("example") PostLikeExample example);

    int updateByPrimaryKeySelective(PostLike record);

    int updateByPrimaryKey(PostLike record);
}