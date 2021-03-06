package org.example.weibo.service;

import com.github.pagehelper.PageInfo;
import org.example.weibo.pojo.*;

import java.util.List;
import java.util.Map;

public interface NewsService {
    //被点赞的 人-微博 在postLikes表
    List<PostLike> getLikedPost(Integer uid);
    //获取被点赞的评论 在 commentLike
    List<CommentLike> getCommentLike(Integer uid);
    //合并 微博点赞和评论点赞
    PageInfo<Object> allLike(Integer uid,Integer pageNum);
    //评论我的人
    List<Comment> getAllComments(Integer uid);


    List<PostLike> getAllLikedPost(Integer uid);
    //获取被点赞的评论 在 commentLike
    List<CommentLike> getAllCommentLike(Integer uid);
    //评论我的人
    PageInfo<Comment> getComments(Integer pageStart,Integer size,Integer uid);
}
