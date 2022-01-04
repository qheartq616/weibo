package org.example.weibo.service;

import com.github.pagehelper.PageInfo;
import org.example.weibo.pojo.CommentLike;
import org.example.weibo.pojo.Post;
import org.example.weibo.pojo.PostLike;
import org.example.weibo.pojo.User;

import java.util.List;
import java.util.Map;

public interface NewsService {
//    //给我点赞的人
//    Map<User,Post> getAllUserLikeMeAfterLast(Integer uid, Integer pageNum);
    //被点赞的 人-微博 在postLikes表
    List<PostLike> getLikedPost(Integer uid);
    //获取被点赞的评论 在 commentLike
    List<CommentLike> getCommentLike(Integer uid);
}
