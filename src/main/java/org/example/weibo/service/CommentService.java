package org.example.weibo.service;

import org.example.weibo.pojo.Comment;

import java.util.List;

public interface CommentService {
	public List<Comment> showAllCommentList(Integer pid);
}
