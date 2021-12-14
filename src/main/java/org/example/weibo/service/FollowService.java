package org.example.weibo.service;

import org.example.weibo.pojo.User;

import java.util.List;

public interface FollowService {
	public List<User> showAllFollowUser(Integer uid);
	public void doFollow(Integer uid,Integer followUid);
	public void doNotFollow(Integer uid,Integer followUid);
	public List<User> showAllFans(Integer followUid);
	public int followStatus(Integer uid,Integer followUid);
	public List<User> showAllMutualFollowUser(Integer uid);
}
