package org.example.weibo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.weibo.mapper.CommentLikeMapper;
import org.example.weibo.mapper.CommentMapper;
import org.example.weibo.pojo.Comment;
import org.example.weibo.pojo.CommentExample;
import org.example.weibo.pojo.CommentLike;
import org.example.weibo.pojo.CommentLikeExample;
import org.example.weibo.utils.ListUtil;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
	@Resource
	CommentMapper commentMapper;
	@Resource
	CommentLikeService commentLikeService;

	@Override
	//查询pid微博下的所有评论  用分页
	public List<Comment> showAllComment(String upid) {
		CommentExample commentExample=new CommentExample();
		commentExample.createCriteria().andUpidEqualTo(upid).andUpcidEqualTo(""+0);
		List<Comment> allCommentList = commentMapper.selectByExample(commentExample);
		return allCommentList;
	}

	@Override
	//应该用分页
	public List<Comment> showLatestComment(String upid) {
		return null;
	}

	@Override
	//查询最热评论
	public List<Comment> showHotComment(String upid,String upcid,Integer uid) {
		CommentExample commentExample=new CommentExample();
		commentExample.createCriteria().andUpidEqualTo(upid).andUpcidEqualTo(upcid);
		List<Comment> commentList = commentMapper.selectByExample(commentExample);
		List<Comment> fillCommentInfo = fillCommentInfo(commentList,uid);

		return ListUtil.sortByHeat(fillCommentInfo);
	}

	@Override
	//补全微博信息
	public List<Comment> fillCommentInfo(List<Comment> commentList, Integer uid) {
		for (Comment comment : commentList) {
			//点赞数
			int countCommentLike = commentLikeService.countCommentLike(comment.getUid() + "-" + comment.getCid());
			comment.setCountCommentLike(countCommentLike);

			//是否点赞
			boolean ifCommentLike = commentLikeService.ifCommentLike(comment.getUid() + "-" + comment.getCid(), uid);
			comment.setCommentLike(ifCommentLike);

			//热门子评论
			if (comment.getUpcid().equals("0")){
				List<Comment> showHotComment = showHotComment(comment.getUpid(), comment.getUid() + "-" + comment.getCid(),uid);
				comment.setSubCommentList(showHotComment);
			}
		}

		return commentList;
	}

}
