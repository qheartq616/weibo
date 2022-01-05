package org.example.weibo.controller;

import com.github.pagehelper.PageInfo;
import org.example.weibo.mapper.UserMapper;
import org.example.weibo.pojo.Post;
import org.example.weibo.pojo.User;
import org.example.weibo.service.FollowService;
import org.example.weibo.service.PostLikeService;
import org.example.weibo.service.PostService;
import org.example.weibo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class cUserController {

    @Resource
    UserMapper userMapper;
    @Resource
    PostService postService;
    @Resource
    PostLikeService postLikeService;
    @Resource
    FollowService followService;
    @Resource
    UserService userService;

    @RequestMapping("/cuser/{userCurUid}/{pageNum}")
    //*的个人主页  显示uid的所有微博
    public String showAllPost(Model model, HttpSession session,
                              @PathVariable("userCurUid")Integer userCurUid,
                              @PathVariable("pageNum") Integer pageNum){
        User user = (User) session.getAttribute("user");

        User userCur = userMapper.selectByPrimaryKey(userCurUid);

        User fillUserCur = userService.fillUserInfo(user, userCur);
        model.addAttribute("userCur",fillUserCur);

        List<User> userList = followService.showAllFollowUser(userCurUid);
        model.addAttribute("userList",userList);

        //pagehelper一定要写在select方法前
        PageInfo<Post> pageInfo = postService.showUserAllPost(user.getUid(),userCurUid,pageNum);
		/*for (Post post : pageInfo.getList()) {
			System.out.println("post.getPostTime() = " + post.getPostTime());
		}
		System.out.println("=========");
		for (Post post : allFollowUserPost) {
			System.out.println("post = " + post.getPostTime());
		}*/
        model.addAttribute("pageInfo",pageInfo);

        return "followall";
    }
}
