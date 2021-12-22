package org.example.weibo.service;

public interface PostLikeService {
	public int countPostLike(String upid);
	public boolean ifPostLike(String upid, Integer uid);
	public boolean doPostLike(String upid, Integer uid);
	public int countAllPostLike(Integer uid);
}
