package org.example.weibo.service;

import com.github.pagehelper.PageInfo;
import org.example.weibo.mapper.PostLikeMapper;
import org.example.weibo.mapper.PostMapper;
import org.example.weibo.mapper.UserMapper;
import org.example.weibo.pojo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewsServiceImpl implements  NewsService {
    @Resource
    PostLikeMapper postLikeMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    PostMapper postMapper;

    @Override
    public PageInfo<User> getAllUserLikeMeAfterLast(Integer uid) {

        PostExample postExample =new PostExample();

        postExample.createCriteria().andUidEqualTo(uid);

        PostLikeExample postLikeExample=new PostLikeExample();

        List<Post> posts = postMapper.selectByExample(postExample);

        List<User> users =null;
        PageInfo<User> pageInfo=new PageInfo<>(users);
        return pageInfo;
    }
}
