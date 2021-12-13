package org.example.weibo.service;

import com.github.pagehelper.PageInfo;
import org.example.weibo.pojo.Post;

import java.util.List;

public interface PostService {
	//状态码：-2仅自己可见，-1置顶，0删除，1正常，2好友圈可见，3粉丝可见
	public PageInfo<Post> showUserAllPost(Integer uid, Integer curUid, Integer pageNum);
	public PageInfo<Post> showAllFollowUserPost(Integer uid,Integer pageNum);
	public Post doPost(Post post);
	public int countPost(Integer uid);
	public PageInfo<Post> showGroupAllPost(Integer uid,Integer gid,Integer pageNum);
	//点赞为实际删除（对数据库进行增加删除）
	//删微博为逻辑删除（修改状态码）
	public void delete();
}
