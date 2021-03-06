package org.example.weibo.controller;

import com.github.pagehelper.PageInfo;
import org.example.weibo.mapper.PostMapper;
import org.example.weibo.pojo.Group;
import org.example.weibo.pojo.Post;
import org.example.weibo.pojo.User;
import org.example.weibo.service.GroupService;
import org.example.weibo.service.PostService;
import org.example.weibo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("main")
@Controller
public class MainController {
	@Resource
	PostService postService;
	@Resource
	GroupService groupService;
	@Resource
	UserService userService;

	@RequestMapping("{type}/{pageNum}")
	public String showAll(HttpSession session, Model model,
	                      @PathVariable Integer pageNum,
	                      @PathVariable("type") String type) {
		User user = (User) session.getAttribute("user");

		if (type != null) {
			model.addAttribute("type", type);
		}

		if (type.equals("all")) {
			//PageHelper.startPage(pageNum,5);
			PageInfo<Post> pageInfo = postService.showAllFollowUserPostRandom(user.getUid(), pageNum);
			//PageInfo<Post> pageInfo=new PageInfo<>(postList);
			model.addAttribute("pageInfo", pageInfo);
			//System.out.println(1111);
		} else if (type.equals("latest")) {
			PageInfo<Post> pageInfo = postService.showAllFollowUserPostLatest(user.getUid(), pageNum);
			model.addAttribute("pageInfo", pageInfo);
			//System.out.println(2222);
		} else if (type.equals("mutual")) {
			PageInfo<Post> pageInfo = postService.showAllFollowUserPostMutual(user.getUid(), pageNum);
			model.addAttribute("pageInfo", pageInfo);
			//System.out.println(3333);
		} else if (type.equals("hottest")){
			PageInfo<Post> pageInfo = postService.showAllFollowUserPostHottest(user.getUid(), pageNum);
			model.addAttribute("pageInfo", pageInfo);
		}

		List<Group> groupList = groupService.showAllGroupList(user.getUid());
		model.addAttribute("groupList", groupList);

		return "main";
	}

	@RequestMapping("myGroups/{gid}/{pageNum}")
	public String showGroupPost(HttpSession session, Model model,
								@PathVariable("gid") Integer gid,
								@PathVariable("pageNum") Integer pageNum) {
		User user = (User) session.getAttribute("user");

		PageInfo<Post> pageInfo = postService.showGroupAllPost(user.getUid(), gid, pageNum);
		model.addAttribute("pageInfo", pageInfo);

		List<Group> groupList = groupService.showAllGroupList(user.getUid());
		model.addAttribute("groupList", groupList);

		return "main";
	}

	@RequestMapping("submit")
	@ResponseBody
	public Map<String, Object> submitNewPost(Post post) {
		post.setPostTime(new Date());
		Post postNew = postService.doPost(post);
		User user = postNew.getUser();
		User filledUser = userService.fillUserInfo(user, user);
		postNew.setUser(filledUser);

		Map<String, Object> map = new HashMap<>();
		map.put("post", postNew);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String formatTime = simpleDateFormat.format(postNew.getPostTime());
		map.put("formatTime", formatTime);
		return map;
	}

	@RequestMapping("delete")
	@ResponseBody
	public void deletePost(Integer pid) {
		postService.doDelete(pid);
	}

	@RequestMapping("detail/{pid}")
	public String detail(HttpSession session,
	                     @PathVariable("pid") Integer pid,
	                     Model model){
		Post post = postService.showPost(pid);

		Post hotPost = postService.hotPost(post.getUid());
		model.addAttribute("hotPost",hotPost);

		List<Post> posts=new ArrayList<>();
		posts.add(post);
		User user = (User) session.getAttribute("user");
		List<Post> posts1 = postService.fillPostInfo(posts, user.getUid());

		User user1 = userService.fillUserInfo(user, post.getUser());
		posts1.get(0).setUser(user1);

		model.addAttribute("post",posts1.get(0));

		return "detail";
	}
}
