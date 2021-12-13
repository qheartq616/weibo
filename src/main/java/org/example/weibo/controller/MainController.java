package org.example.weibo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.weibo.mapper.PostMapper;
import org.example.weibo.pojo.Group;
import org.example.weibo.pojo.Post;
import org.example.weibo.pojo.User;
import org.example.weibo.service.GroupService;
import org.example.weibo.service.PostService;
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

@RequestMapping("main")
@Controller
public class MainController {
	@Resource
	PostService postService;
	@Resource
	GroupService groupService;
	@Resource
	PostMapper postMapper;

	@RequestMapping("*/{type}/{pageNum}")
	public String showAll(HttpSession session,Model model,
	                      @RequestParam(defaultValue = "1") @PathVariable Integer pageNum,
	                      @RequestParam(defaultValue = "all") @PathVariable String type){
		User user = (User) session.getAttribute("user");

		if (type.equals("all")){
			//PageHelper.startPage(pageNum,5);
			PageInfo<Post> pageInfo = postService.showAllFollowUserPost(user.getUid(),pageNum);
			//PageInfo<Post> pageInfo=new PageInfo<>(postList);
			model.addAttribute("pageInfo",pageInfo);
		}else if (type.equals("latest")){

		}else if (type.equals("friend")){

		}

		List<Group> groupList = groupService.showAllGroupList(user.getUid());
		/*for (Group group : groupList) {
			System.out.println("group.getName() = " + group.getName());
		}*/
		/*List<Group> groupList1=new ArrayList<>();
		List<Group> groupList2=new ArrayList<>();
		if (groupList.size()<=4){
			groupList1=groupList;
		}else {
			for (int i=0;i<groupList.size();i++){
				if(i<4){
					groupList1.add(groupList.get(i));
				}else {
					groupList2.add(groupList.get(i));
				}
			}
		}
		model.addAttribute("groupList1",groupList1);
		model.addAttribute("groupList2",groupList2);*/
		model.addAttribute("groupList",groupList);

		return "main";
	}

	@RequestMapping("{uid}/myGroups/{gid}/{pageNum}")
	public String showGroupPost(HttpSession session,Model model,
	                            @PathVariable("uid") Integer uid,
	                            @PathVariable("gid") Integer gid,
	                            @PathVariable("pageNum") Integer pageNum){
		User user = (User) session.getAttribute("user");

		PageInfo<Post> pageInfo = postService.showGroupAllPost(uid,gid,pageNum);
		model.addAttribute("pageInfo",pageInfo);

		List<Group> groupList = groupService.showAllGroupList(user.getUid());
		model.addAttribute("groupList",groupList);

		return "main";
	}

	@RequestMapping("submit")
	@ResponseBody
	public Post submitNewPost(Post post){
		int i = postService.doPost(post);
		Post postNew = postMapper.selectByPrimaryKey(i);
		return postNew;
	}
}
