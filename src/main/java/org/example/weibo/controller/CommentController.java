package org.example.weibo.controller;

import org.example.weibo.pojo.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("comment")
@Controller
public class CommentController {
	@RequestMapping("hotComment")
	public List<Comment> showHotComment(){
		return null;
	}
}
