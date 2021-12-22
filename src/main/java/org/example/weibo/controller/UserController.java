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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RequestMapping("user")
@Controller
public class UserController {
	@Resource
	PostService postService;
	@Resource
	FollowService followService;
	@Resource
	PostLikeService postLikeService;
	@Resource
	UserMapper userMapper;
	@Resource
	UserService userService;

	@RequestMapping("{userCurUid}/{pageNum}")
	//*的个人主页  显示uid的所有微博
	public String showAllPost(Model model, HttpSession session,
	                          @PathVariable("userCurUid")Integer userCurUid,
	                          @PathVariable("pageNum") Integer pageNum){
		User user = (User) session.getAttribute("user");

		User userCur = userMapper.selectByPrimaryKey(userCurUid);

		User fillUserCur = userService.fillUserInfo(user, userCur);
		model.addAttribute("userCur",fillUserCur);

		Post topPost = postService.showTopPost(userCurUid);
		model.addAttribute("topPost",topPost);

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

		return "index";
	}

	@RequestMapping("*/doLike")
	@ResponseBody
	//点赞取消赞功能
	public boolean doLike(@RequestParam(name = "upid")String upid,
	                      @RequestParam(name = "uid")Integer uid){
		//System.out.println("pid = " + pid);
		//System.out.println("uid = " + uid);
		boolean b = postLikeService.doPostLike(upid, uid);
		return b;
	}

	@RequestMapping("*/doFollow")
	@ResponseBody
	//关注取关功能
	public int doFollow(@RequestParam(name = "uid")Integer uid,
	                    @RequestParam(name = "followUid")Integer followUid){
		return followService.doFollow(uid, followUid);
	}

	@RequestMapping("*/doDelete")
	@ResponseBody
	//关注取关功能
	public void doDelete(@RequestParam(name = "pid")Integer pid){
		postService.doDelete(pid);
	}

	@RequestMapping("*/doTopPost")
	//关注取关功能
	public String doTopPost(@RequestParam(name = "pid")Integer pid,
	                      HttpSession session){
		User user = (User) session.getAttribute("user");
		postService.doTopPost(user.getUid(),pid);
		return "redirect:/user/"+user.getUid()+"/1";
	}
}
