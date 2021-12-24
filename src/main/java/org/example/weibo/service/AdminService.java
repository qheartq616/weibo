package org.example.weibo.service;

import com.github.pagehelper.PageInfo;
import org.example.weibo.pojo.Admin;
import org.example.weibo.pojo.Post;
import org.example.weibo.pojo.User;

public interface AdminService {
    //管理员登录
    public Admin login(String name, String password);
    //根据id查询管理
    Admin selectById(Integer aid);
    //查询所有用户
    PageInfo<User> getAllUser(Integer pageStart, Integer limit);
    //修改用户信息
    int updateUser(User user);
    //添加用户
    int addUser(User user);
    //删除用户
    int deleteUser(Integer uid);

    //查询所有的微博
    PageInfo<Post> getAllPost(Integer pageStart, Integer limit);

    //前台用户短信登录
    User getUserByPhone(Long phone);

}
