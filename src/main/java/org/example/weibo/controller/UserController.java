package org.example.weibo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.weibo.mapper.UserMapper;
import org.example.weibo.pojo.Post;
import org.example.weibo.pojo.User;
import org.example.weibo.service.FollowService;
import org.example.weibo.service.LoveService;
import org.example.weibo.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("user")
@Controller
public class UserController {
	@Resource
	PostService postService;
	@Resource
	FollowService followService;
	@Resource
	LoveService loveService;
	@Resource
	UserMapper userMapper;

	@RequestMapping("{curUid}/{pageNum}")
	//*的个人主页  显示uid的所有微博
	public String showAllPost(Model model, HttpSession session,
	                          @PathVariable("curUid")Integer curUid,
	                          @PathVariable("pageNum") Integer pageNum){
		User user = (User) session.getAttribute("user");

		User userCur = userMapper.selectByPrimaryKey(curUid);
		model.addAttribute("userCur",userCur);

		//pagehelper一定要写在select方法前
		PageInfo<Post> pageInfo = postService.showUserAllPost(user.getUid(),curUid,pageNum);
		/*for (Post post : pageInfo.getList()) {
			System.out.println("post.getPostTime() = " + post.getPostTime());
		}
		System.out.println("=========");
		for (Post post : allFollowUserPost) {
			System.out.println("post = " + post.getPostTime());
		}*/
		model.addAttribute("pageInfo",pageInfo);

		List<User> allFollowUser = followService.showAllFollowUser(curUid);
		model.addAttribute("allFollowUser",allFollowUser);

		List<User> allFans = followService.showAllFans(curUid);
		model.addAttribute("allFans",allFans);

		int countAllLove = loveService.countAllLove(curUid);
		model.addAttribute("countAllLove",countAllLove);

		int countPost = postService.countPost(curUid);
		model.addAttribute("countPost",countPost);

		//当前访问页面的uid（不一定为session.user.uid）
		//model.addAttribute("uid",curUid);

		int followStatus = followService.followStatus(user.getUid(), curUid);
		model.addAttribute("followStatus",followStatus);

		return "index";
	}

	@RequestMapping("*/doLove")
	@ResponseBody
	//点赞功能
	public boolean doLove(@RequestParam(name = "pid")Integer pid,
	                      @RequestParam(name = "uid")Integer uid){
		//System.out.println("pid = " + pid);
		//System.out.println("uid = " + uid);
		boolean b = loveService.doLove(pid, uid);
		return b;
	}


}
