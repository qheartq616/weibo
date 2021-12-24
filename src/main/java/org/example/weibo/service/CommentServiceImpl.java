package org.example.weibo.service;

import org.example.weibo.mapper.CommentMapper;
import org.example.weibo.pojo.Comment;
import org.example.weibo.pojo.CommentExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
	@Resource
	CommentMapper commentMapper;

	@Override
	//查询pid微博下的所有评论
	public List<Comment> showAllComment(Integer pid) {
		CommentExample commentExample=new CommentExample();
		commentExample.createCriteria().andPidEqualTo(pid).andPidEqualTo(0);
		List<Comment> allCommentList = commentMapper.selectByExample(commentExample);
		return allCommentList;
	}

	@Override
	public List<Comment> showLatestComment() {
		return null;
	}
}
