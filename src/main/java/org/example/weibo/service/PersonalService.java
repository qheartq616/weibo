package org.example.weibo.service;

import com.github.pagehelper.PageInfo;
import org.example.weibo.pojo.User;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public interface PersonalService {
    //填充所有关注的人的信息 关注的人关注的人数 和 粉丝数 以及微博数的填充
    PageInfo<User> getAllFollow(Integer page , Integer uid);
}
