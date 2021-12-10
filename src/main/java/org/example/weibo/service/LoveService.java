package org.example.weibo.service;

public interface LoveService {
	public int countLove(Integer pid);
	public boolean ifLove(Integer pid, Integer uid);
	public boolean doLove(Integer pid, Integer uid);
	public int countAllLove(Integer uid);
}
