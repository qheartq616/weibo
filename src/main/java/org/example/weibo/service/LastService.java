package org.example.weibo.service;

import org.example.weibo.pojo.Last;
import org.example.weibo.pojo.User;

public interface LastService {
    //如果这个人呢没有查看信息就添加
    Integer addLastLeave(Integer uid);
    //获取用户离开时间
    Last getLastLeave(Integer uid);
    //如果这个人查看信息了 就修改为当前日期
    Integer updateLast(Last last);
}
