package org.example.weibo.controller;

import org.example.weibo.pojo.Comment;
import org.example.weibo.pojo.User;
import org.example.weibo.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("comment")
@Controller
public class CommentController {
	@Resource
	CommentService commentService;

	@RequestMapping("hotComment")
	@ResponseBody
	public List<Comment> showHotComment(@RequestParam(name = "upid") String upid,
	                                    HttpSession session){
		User user = (User) session.getAttribute("user");
		List<Comment> commentList = commentService.showHotComment(upid,"0",user.getUid());
		/*System.out.println(upid);
		for (Comment comment : commentList) {
			System.out.println("comment.toString() = " + comment.toString());
		}*/

		return commentList;
	}
}
