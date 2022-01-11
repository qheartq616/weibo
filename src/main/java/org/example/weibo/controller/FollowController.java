package org.example.weibo.controller;

import com.github.pagehelper.PageHelper;
import org.example.weibo.mapper.UserMapper;
import org.example.weibo.pojo.Group;
import org.example.weibo.pojo.R;
import org.example.weibo.pojo.User;
import org.example.weibo.service.FollowService;
import org.example.weibo.service.GroupService;
import org.example.weibo.service.PostService;
import org.example.weibo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FollowController {
    @Resource
    FollowService followService;
    @Resource
    UserMapper userMapper;
    @Resource
    UserService userService;
    @Resource
    PostService postService;
    @Resource
    GroupService groupService;

    @RequestMapping("/follow/{userCurUid}/{type}")
    public String getAllFollow(@PathVariable Integer userCurUid,@PathVariable Integer type, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");

        User userCur = userMapper.selectByPrimaryKey(userCurUid);

        User fillUserCur = userService.fillUserInfo(user, userCur);
        model.addAttribute("userCur",fillUserCur);

        List<Group> list = groupService.showAllGroupList(userCurUid);
        model.addAttribute("userCurGroup",list);
        for (Group group : list) {
            System.out.println(group.getName()+"--"+group.getGid());
        }

        List<User> users=new ArrayList<>();
        if(type==1){
           users = followService.showAllFollowUser(userCurUid);
        }else{
           users = followService.showAllFans(userCurUid);
        }
        for (User user1 : users) {
            List<User> users1 = followService.showAllFollowUser(user1.getUid());
            user1.setCountAllFollowUser(users1.size());
            List<User> users2 = followService.showAllFans(user1.getUid());
            user1.setCountAllFans(users2.size());
            int i = postService.countPost(user1.getUid());
            user1.setCountAllPost(i);
        }
        model.addAttribute("users",users);
        return "personal";
    }

    @ResponseBody
    @RequestMapping("/group/add/{uid}")
    public  R addGroup(@PathVariable Integer uid, @RequestParam("groupName") String groupName ){
        List<Group> list = groupService.showAllGroupList(uid);
        if(list.size()>0){
            for (Group group : list) {
                if(groupName.equals(group.getName())){
                    return R.ok().addData("result","该分组已存在");
                }
            }
        }
        Group group =new Group();
        group.setUid(uid);
        group.setName(groupName);
        Integer maxGid = groupService.getMaxGid();
        group.setGid(maxGid);
        groupService.createGroup(group);
        return R.ok().addData("result","添加分组成功");
    }


}
