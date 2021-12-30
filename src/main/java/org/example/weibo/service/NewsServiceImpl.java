package org.example.weibo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.weibo.mapper.PostLikeMapper;
import org.example.weibo.mapper.PostMapper;
import org.example.weibo.mapper.UserMapper;
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

    @Override
    public PageInfo<PostLike> getLikedPost(Integer uid, Integer pageNum) {
        PageHelper.startPage(pageNum,5);
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
        PageInfo<PostLike> pageInfo =new PageInfo<>(postLikes);
        return pageInfo;
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
