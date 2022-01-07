package org.example.weibo.controller;

import org.example.weibo.pojo.Comment;
import org.example.weibo.pojo.Post;
import org.example.weibo.pojo.R;
import org.example.weibo.pojo.User;
import org.example.weibo.service.CommentLikeService;
import org.example.weibo.service.CommentService;
import org.example.weibo.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@RequestMapping("comment")
@Controller
public class CommentController {
	@Resource
	CommentService commentService;
	@Resource
	CommentLikeService commentLikeService;
	@Resource
	PostService postService;

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
			commentList = commentService.showLatestComment(upid,"0", user.getUid(),0, 5).getList();
		}
		/*System.out.println(upid);
		for (Comment comment : commentList) {
			System.out.println("comment.toString() = " + comment.toString());
		}*/
		if (commentList.size()==0){
			return R.empty();
		}else {
			return R.ok().addData("commentList",commentList);
		}
	}

	@RequestMapping("sendComment")
	@ResponseBody
	public R sendComment(Comment comment,HttpSession session){
		User user = (User) session.getAttribute("user");
		comment.setUid(user.getUid());
		comment.setCommentTime(new Date());
		Comment comment1 = commentService.doComment(comment);
		//System.out.println(comment.toString());
		if (!comment1.getUpcid().equals("0")){
			String[] split = comment1.getUpcid().split("-");
			int pcid = Integer.parseInt(split[1]);
			Comment pcomment = commentService.findComment(pcid);
			Calendar beforeTime = Calendar.getInstance();
			beforeTime.add(Calendar.MINUTE, +5);// 5分钟后的时间
			Date beforeD = beforeTime.getTime();
			if (pcomment.getCommentTime().after(beforeD)){
				return R.ok().addData("comment",comment1).addData("kind","latest");
			}
		}
		return R.ok().addData("comment",comment1).addData("kind","hot");
	}

	@RequestMapping("doLike")
	@ResponseBody
	public boolean doLike(@RequestParam(name = "uid") Integer uid,
	                      @RequestParam(name = "cid") Integer cid,
	                      HttpSession session){
		User user = (User) session.getAttribute("user");
		boolean commentLike = commentLikeService.doCommentLike(uid + "-" + cid, user.getUid());
		return commentLike;
	}

	@RequestMapping("getNewPostCountComment")
	@ResponseBody
	public int countComment(@RequestParam(name = "uid")Integer uid,@RequestParam(name = "pid")Integer pid){
		int countComment = commentService.countComment(uid + "-" + pid, null);
		return countComment;
	}

	@RequestMapping("deleteComment")
	@ResponseBody
	public R doDelete(@RequestParam(name = "cid")Integer cid,
	                  @RequestParam(name = "upid")String upid,
	                  HttpSession session){
		commentService.doDeleteComment(cid);
		int i = upid.indexOf("-");
		String[] split = upid.split("-");
		int pid=Integer.parseInt(split[split.length-1]);
		Post post = postService.showPost(pid);
		R r = R.ok().addData("post", post);
		r.addData("user",session.getAttribute("user"));
		int countComment = commentService.countComment(upid, null);
		r.addData("countComment",countComment);
		return r;
	}
}
