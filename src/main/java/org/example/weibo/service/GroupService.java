package org.example.weibo.service;

import org.example.weibo.pojo.Group;
import org.example.weibo.pojo.User;

import java.util.List;

public interface GroupService {
	public List<Group> showAllGroupList(Integer uid);
	public List<User> showAllFollowUser(Integer gid);
	public void createGroup(Group group);
	public void modifyGroupName(Integer gid,String name);
	public void deleteUserFromGroup(Integer gid,Integer uid);
	public void deleteUserFromAllGroup(Integer uid,Integer followUid);
	public List<User> showAllNoGroupUser(Integer uid);
	public User showSpUser(Integer uid);
}
