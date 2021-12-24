package org.example.weibo.service;

import org.example.weibo.pojo.Comment;

import java.util.List;

public interface CommentService {
	public List<Comment> showAllComment(Integer pid);
	public List<Comment> showLatestComment();
}
