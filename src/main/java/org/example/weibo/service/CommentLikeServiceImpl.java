package org.example.weibo.service;

import org.example.weibo.mapper.CommentLikeMapper;
import org.example.weibo.pojo.Comment;
import org.example.weibo.pojo.CommentLike;
import org.example.weibo.pojo.CommentLikeExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CommentLikeServiceImpl implements CommentLikeService{
	@Resource
	CommentLikeMapper commentLikeMapper;

	@Override
	//某条评论点赞数
	public int countCommentLike(String ucid) {
		CommentLikeExample commentLikeExample=new CommentLikeExample();
		commentLikeExample.createCriteria().andUcidEqualTo(ucid);
		int i = commentLikeMapper.countByExample(commentLikeExample);
		return i;
	}

	@Override
	//某条评论是否点赞
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

	@Override
	//点赞或取消赞，原理同微博点赞
	public boolean doCommentLike(String ucid,Integer uid) {
		boolean ifCommentLike = ifCommentLike(ucid, uid);
		if (!ifCommentLike){
			CommentLike commentLike=new CommentLike();
			commentLike.setUid(uid);
			commentLike.setUcid(ucid);
			commentLike.setLikeTime(new Date());
			commentLikeMapper.insertSelective(commentLike);
			return true;
		}else {
			CommentLikeExample commentLikeExample=new CommentLikeExample();
			commentLikeExample.createCriteria().andUidEqualTo(uid).andUcidEqualTo(ucid);
			commentLikeMapper.deleteByExample(commentLikeExample);
			return false;
		}
	}
}
