package org.example.weibo.service;

import com.github.pagehelper.PageInfo;
import org.example.weibo.pojo.Post;

import java.util.List;

public interface PostService {
	public PageInfo<Post> showUserAllPost(Integer uid, Integer curUid, Integer pageNum);
	public PageInfo<Post> showAllFollowUserPost(Integer uid,Integer pageNum);
	public int doPost(Post post);
	public int countPost(Integer uid);
	public PageInfo<Post> showGroupAllPost(Integer uid,Integer gid,Integer pageNum);
}
