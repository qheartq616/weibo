package org.example.weibo.service;

public interface CommentLikeService {
	public int countCommentLike(String ucid);
	public boolean ifCommentLike(String ucid, Integer uid);
}
