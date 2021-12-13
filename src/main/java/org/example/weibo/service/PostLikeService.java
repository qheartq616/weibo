package org.example.weibo.service;

public interface PostLikeService {
	public int countPostLike(Integer pid);
	public boolean ifPostLike(Integer pid, Integer uid);
	public boolean doPostLike(Integer pid, Integer uid);
	public int countAllPostLike(Integer uid);
}
