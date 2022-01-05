package org.example.weibo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.weibo.mapper.*;
import org.example.weibo.pojo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class NewsServiceImpl implements  NewsService {
    @Resource
    PostLikeMapper postLikeMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    PostMapper postMapper;
    @Resource
    LastService lastService;
    @Resource
    CommentLikeMapper commentLikeMapper;
    @Resource
    CommentMapper commentMapper;

//    @Override
//    public Map<User,Post> getAllUserLikeMeAfterLast(Integer uid, Integer pageNum) {
//        PageHelper.startPage(pageNum,5);
//
//        List<PostLike> postLikes = getLikedPost(uid);
//
//        List<User> users =new ArrayList<>();
//        List<Post> posts=new ArrayList<>();
//        Map<User,Post> map=new HashMap<>();
//        for (PostLike postLike : postLikes) {
//            users.add(userMapper.selectByPrimaryKey(postLike.getUid()));
//            int postId = Integer.parseInt(postLike.getUpid().substring(postLike.getUpid().indexOf("-") + 1));
//            posts.add(postMapper.selectByPrimaryKey(postId));
//            postLike.setPost(postMapper.selectByPrimaryKey(postId));
//        }
//        return map;
//    }

    /**
     * 获取被点赞的评论
     * @param uid
     * @return
     */
    @Override
    public List<PostLike> getLikedPost(Integer uid) {
     // PageHelper.startPage(pageNum,5);
        PostLikeExample postLikeExample=new PostLikeExample();
        Last lastLeave = lastService.getLastLeave(uid);
        Date date=new Date();
        System.out.println("最后厉害点赞时间"+lastLeave.getLltime());
        System.out.println(date);
        //查取获取给自己微博最近点赞的 数据
        postLikeExample.createCriteria().andLikeTimeBetween(lastLeave.getLltime(),date).andUpidLike(""+uid+"-%");
        List<PostLike> postLikes=postLikeMapper.selectByExample(postLikeExample);
        System.out.println("瞒组条件的点赞微博数"+postLikes.size());
        for (PostLike postLike : postLikes) {
            postLike.setUser(userMapper.selectByPrimaryKey(postLike.getUid()));
            int postId = Integer.parseInt(postLike.getUpid().substring(postLike.getUpid().indexOf("-") + 1));
            postLike.setPost(postMapper.selectByPrimaryKey(postId));
            System.out.println(postLike.getUpid());
            System.out.println(postLike.getLikeTime());
        }
    //    PageInfo<PostLike> pageInfo =new PageInfo<>(postLikes);
        return postLikes;
    }

    @Override
    public List<CommentLike> getCommentLike(Integer uid) {
        Date date=new Date();
        CommentLikeExample commentLikeExample=new CommentLikeExample();
        Last lastLeave = lastService.getLastLeave(uid);
        commentLikeExample.createCriteria().andLikeTimeBetween(lastLeave.getLltime(),date).andUcidLike(""+uid+"-%");
        List<CommentLike> commentLikes=commentLikeMapper.selectByExample(commentLikeExample);
        System.out.println("符合条件的评论点赞个数"+commentLikes.size());
        Comment comment=new Comment();
        for (CommentLike commentLike : commentLikes) {
            int commentId = Integer.parseInt(commentLike.getUcid().substring(commentLike.getUcid().indexOf("-") + 1));
            comment = commentMapper.selectByPrimaryKey(commentId);
            commentLike.setComment(comment);
            int postId= Integer.parseInt(comment.getUpid().substring(comment.getUpid().indexOf("-") + 1));
            comment.setPost(postMapper.selectByPrimaryKey(postId));
            commentLike.setUser(userMapper.selectByPrimaryKey(commentLike.getUid()));
        }

        return commentLikes;
    }

    @Override
    public PageInfo<Object> allLike(Integer uid, Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<CommentLike> commentLike = this.getCommentLike(uid);
        List<PostLike> likedPost = this.getLikedPost(uid);
        List list=new ArrayList<>();

        Iterator<CommentLike> it1=commentLike.iterator();
        while(it1.hasNext()){
            list.add(it1.next());
        }
        Iterator<PostLike> it2=likedPost.iterator();
        while (it2.hasNext()){
            list.add(it2.next());
        }
        System.out.println("所有点赞数"+list.size());
        PageInfo pageInfo=new PageInfo(list);
        return pageInfo;
    }

    /**
     * 我的微博 的评论
     * @param pageStart
     * @param size
     * @return
     */
    @Override
    public PageInfo<Comment> getAllComments(Integer pageStart, Integer size,Integer uid) {


        return null;
    }

//    @Override
//    public List<Post> allPost(Integer uid) {
//        List<PostLike> likedPost = getLikedPost(uid);
//        List<Post> posts=new ArrayList<>();
//        for (PostLike postLike : likedPost) {
//            int postId = Integer.parseInt(postLike.getUpid().substring(postLike.getUpid().indexOf("-") + 1));
//            posts.add(postMapper.selectByPrimaryKey(postId));
//        }
//        return posts;
//    }
}
