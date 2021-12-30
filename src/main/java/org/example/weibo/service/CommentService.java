package org.example.weibo.service;

import org.example.weibo.pojo.Comment;

import java.util.List;

public interface CommentService {
	public List<Comment> showAllComment(String upid);
	public List<Comment> showLatestComment(String upid);
	public List<Comment> showHotComment(String upid,String upcid,Integer uid);
	public List<Comment> fillCommentInfo(List<Comment> commentList,Integer uid);
}
