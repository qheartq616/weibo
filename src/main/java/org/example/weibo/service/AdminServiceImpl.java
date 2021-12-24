package org.example.weibo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.weibo.mapper.AdminMapper;
import org.example.weibo.mapper.PostMapper;
import org.example.weibo.mapper.UserMapper;
import org.example.weibo.pojo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminMapper adminMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    PostMapper postMapper;


    @Override
    public Admin login(String name, String password) {
        if (name!=null&&password!=null){
            AdminExample adminExample=new AdminExample();
            adminExample.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
            List<Admin> adminList = adminMapper.selectByExample(adminExample);
            if (adminList.isEmpty()){
                return null;
            }else {
                return adminList.get(0);
            }
        }else {
            return null;
        }
    }

    @Override
    public Admin selectById(Integer aid) {
        return  adminMapper.selectByPrimaryKey(aid);
    }

    /**
     * 查询所有用户
     * @param pageStart
     * @return
     */
    @Override
    public PageInfo<User> getAllUser(Integer pageStart, Integer limit) {
        PageHelper.startPage(pageStart,limit);
        List<User> users = userMapper.selectByExample(null);
        PageInfo<User> pageInfo=new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public int updateUser(User user) {

        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int deleteUser(Integer uid) {
        return userMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public PageInfo<Post> getAllPost(Integer pageStart, Integer limit) {
        PageHelper.startPage(pageStart,limit);
        List<Post> posts=postMapper.selectByExample(null);
        PageInfo<Post> pageInfo=new PageInfo<>(posts);
        return pageInfo;
    }

    @Override
    public User getUserByPhone(Long phone) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andPhoneEqualTo(phone);
        List<User> users = userMapper.selectByExample(userExample);
        return users.get(0);
    }
}
