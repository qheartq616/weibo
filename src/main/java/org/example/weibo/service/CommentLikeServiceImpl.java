package org.example.weibo.service;

import org.example.weibo.mapper.CommentLikeMapper;
import org.example.weibo.pojo.Comment;
import org.example.weibo.pojo.CommentLikeExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommentLikeServiceImpl implements CommentLikeService{
	@Resource
	CommentLikeMapper commentLikeMapper;

	@Override
	public int countCommentLike(String ucid) {
		CommentLikeExample commentLikeExample=new CommentLikeExample();
		commentLikeExample.createCriteria().andUcidEqualTo(ucid);
		int i = commentLikeMapper.countByExample(commentLikeExample);
		return i;
	}

	@Override
	public boolean ifCommentLike(String ucid, Integer uid) {
		CommentLikeExample commentLikeExample=new CommentLikeExample();
		commentLikeExample.createCriteria().andUcidEqualTo(ucid).andUidEqualTo(uid);
		int i = commentLikeMapper.countByExample(commentLikeExample);
		if (i==0){
			return false;
		}else {
			return true;
		}
	}
}
