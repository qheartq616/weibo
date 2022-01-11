package org.example.weibo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.weibo.mapper.*;
import org.example.weibo.pojo.*;
import org.example.weibo.utils.ListUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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


    /**
     * 获取被点赞的评论
     * @param uid
     * @return
     */
    @Override
    public List<PostLike> getLikedPost(Integer uid) {
      //PageHelper.startPage(pageNum,5);
        PostLikeExample postLikeExample=new PostLikeExample();
        Last lastLeave = lastService.getLastLeave(uid);
        Date date=new Date();
        System.out.println("最后厉害点赞时间"+lastLeave.getLltime());
        System.out.println(date);
        //查取获取给自己微博最近点赞的 数据
        postLikeExample.createCriteria().andLikeTimeBetween(lastLeave.getLltime(),date).andUpidLike(""+uid+"-%");
        List<PostLike> postLikes=postLikeMapper.selectByExample(postLikeExample);
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
        return commentLikes;
    }

    @Override
    public PageInfo<Object> allLike(Integer uid, Integer pageNum) {
        List<CommentLike> commentLike = this.getAllCommentLike(uid);
        List<PostLike> likedPost = this.getAllLikedPost(uid);
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
        Collections.sort(list, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                //由大到小排序
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dd1=new Date();
                Date dd2=new Date();
                if(o1 instanceof CommentLike ){
                     dd1=((CommentLike) o1).getLikeTime();
                }else {
                     dd1=((PostLike) o1).getLikeTime();
                }
                if(o2 instanceof CommentLike ){
                     dd2=((CommentLike) o2).getLikeTime();
                }else {
                     dd2=((PostLike) o2).getLikeTime();
                }
                if (dd1.getTime()<dd2.getTime()){
                    return 1;
                }else if (dd1.getTime()>dd2.getTime()){
                    return -1;
                }else {
                    return 0;
                }
            }
        });
        PageInfo pageInfo=new PageInfo(list);
        return pageInfo;
    }

    /**
     * 我的微博 的评论
     * @return
     */
    @Override
    public List<Comment> getAllComments(Integer uid) {
        CommentExample commentExample=new CommentExample();
        Last lastLeave = lastService.getLastLeave(uid);
        commentExample.createCriteria().andUpidLike(""+uid+"-%").andCommentTimeBetween(lastLeave.getLctime(),new Date());
        List<Comment> comments=commentMapper.selectByExample(commentExample);
        return comments;
    }
    @Override
    public List<PostLike> getAllLikedPost(Integer uid) {
        PostLikeExample postLikeExample=new PostLikeExample();
        Last lastLeave = lastService.getLastLeave(uid);
        Date date=new Date();
        System.out.println("最后厉害点赞时间"+lastLeave.getLltime());
        System.out.println(date);
        //查取获取给自己微博最近点赞的 数据
        postLikeExample.createCriteria().andUpidLike(""+uid+"-%");
        postLikeExample.setOrderByClause("like_time desc");
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
    public List<CommentLike> getAllCommentLike(Integer uid) {
        CommentLikeExample commentLikeExample=new CommentLikeExample();
        commentLikeExample.createCriteria().andUcidLike(""+uid+"-%");
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
    public PageInfo<Comment> getComments(Integer pageStart, Integer size, Integer uid) {
        PageHelper.startPage(pageStart,size);
        CommentExample commentExample=new CommentExample();
        commentExample.createCriteria().andUpidLike(""+uid+"-%");
        commentExample.setOrderByClause("comment_time desc");
        List<Comment> comments=commentMapper.selectByExample(commentExample);
        //评论的人
        User user=new User();
        for (Comment comment : comments) {
            comment.setUser( userMapper.selectByPrimaryKey(comment.getUid()));
            int postId= Integer.parseInt(comment.getUpid().substring(comment.getUpid().indexOf("-") + 1));
            comment.setPost(postMapper.selectByPrimaryKey(postId));
        }
        PageInfo<Comment> pageInfo=new PageInfo<>(comments);
        return pageInfo;
    }


}
