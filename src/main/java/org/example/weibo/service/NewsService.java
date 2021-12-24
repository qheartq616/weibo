package org.example.weibo.service;

import com.github.pagehelper.PageInfo;
import org.example.weibo.pojo.User;

public interface NewsService {
    //给我点赞的人
    PageInfo<User> getAllUserLikeMeAfterLast(Integer uid);
}
