package org.example.weibo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.weibo.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class PersonalServiceImpl implements PersonalService {
    @Resource
    FollowService followService;
    @Resource
    PostService postService;

    @Override
    public PageInfo<User> getAllFollow(Integer page,Integer uid) {
        PageHelper.startPage(page,10);
        List<User> users = followService.showAllFollowUser(uid);
        int followSize = users.size();

        List<User> allFans = followService.showAllFans(uid);
        int fansSize = allFans.size();

        int countPost = postService.countPost(uid);

        for (User user : users) {

        }


        return null;
    }
}
