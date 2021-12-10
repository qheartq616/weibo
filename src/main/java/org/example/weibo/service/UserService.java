package org.example.weibo.service;

import org.example.weibo.pojo.User;

public interface UserService {
	public User login(String name, String password);
	public boolean ifRegister(String name);
	public void registerUser(User user);
	public boolean ifUpdateNameGreaterThan100(String name);
	public void updateUserInfo(User user);
	public void updatePassword(Integer uid,String password);
}
