package org.example.weibo.controller;

import org.example.weibo.pojo.Comment;
import org.example.weibo.pojo.R;
import org.example.weibo.pojo.User;
import org.example.weibo.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("comment")
@Controller
public class CommentController {
	@Resource
	CommentService commentService;

	@RequestMapping("someComment")
	@ResponseBody
	public R showSomeComment(@RequestParam(name = "upid") String upid,
	                        @RequestParam(name = "kind") String kind,
	                                    HttpSession session){
		User user = (User) session.getAttribute("user");
		List<Comment> commentList=null;
		if (kind.equals("hot")){
			commentList = commentService.showHotComment(upid,"0",user.getUid(),3);
		}else {
			commentList = commentService.showLatestComment(upid,"0", user.getUid(),0, 3).getList();
		}
		/*System.out.println(upid);
		for (Comment comment : commentList) {
			System.out.println("comment.toString() = " + comment.toString());
		}*/
		R ok = R.ok();
		ok.addData("commentList",commentList);
		return ok;
	}

	@RequestMapping("sendComment")
	@ResponseBody
	public boolean sendComment(Comment comment){
		comment.setCommentTime(new Date());
		commentService.doComment(comment);
		//System.out.println(comment.toString());
		return true;
	}
}
