package org.example.weibo.service;

import com.github.pagehelper.PageInfo;
import org.example.weibo.pojo.Comment;

import java.util.List;

public interface CommentService {
	public List<Comment> showAllComment(String upid);
	public PageInfo<Comment> showLatestComment(String upid,String upcid,Integer uid,Integer pageNum,Integer pageSize);
	public List<Comment> showHotComment(String upid,String upcid,Integer uid,Integer some);
	public List<Comment> fillCommentInfo(List<Comment> commentList,Integer uid,Integer some);
	public Comment doComment(Comment comment);
	public int countComment(String upid,String upcid);
	public void doDeleteComment(Integer cid);
	public Comment findComment(Integer cid);
}
