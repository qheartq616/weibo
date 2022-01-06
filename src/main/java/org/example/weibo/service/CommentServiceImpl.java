package org.example.weibo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.weibo.mapper.CommentMapper;
import org.example.weibo.pojo.Comment;
import org.example.weibo.pojo.CommentExample;
import org.example.weibo.utils.ListUtil;
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
	//查询最近几条，some即为pageSize，pageNum为0
	public PageInfo<Comment> showLatestComment(String upid,String upcid,Integer uid, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		CommentExample commentExample=new CommentExample();
		commentExample.setOrderByClause("comment_time desc");
		commentExample.createCriteria().andUpidEqualTo(upid).andUpcidEqualTo(upcid);
		List<Comment> commentList = commentMapper.selectByExample(commentExample);

		PageInfo<Comment> pageInfo=new PageInfo<>(commentList);
		//显示四条热门评论
		List<Comment> fillCommentInfo = fillCommentInfo(pageInfo.getList(), uid, 4);
		pageInfo.setList(fillCommentInfo);

		return pageInfo;
	}

	@Override
	//查询最热评论
	public List<Comment> showHotComment(String upid,String upcid,Integer uid,Integer some) {
		CommentExample commentExample=new CommentExample();
		commentExample.setOrderByClause("comment_time desc");
		commentExample.createCriteria().andUpidEqualTo(upid).andUpcidEqualTo(upcid);
		List<Comment> commentList = commentMapper.selectByExample(commentExample);

		List<Comment> fillCommentInfo = fillCommentInfo(commentList,uid,some);

		/*for (Comment comment : commentList) {
			System.out.println(comment.toString());
		}*/
		return ListUtil.sortByHeat(fillCommentInfo,some);
	}

	@Override
	//补全评论信息
	public List<Comment> fillCommentInfo(List<Comment> commentList, Integer uid,Integer some) {
		for (Comment comment : commentList) {
			//点赞数
			int countCommentLike = commentLikeService.countCommentLike(comment.getUid() + "-" + comment.getCid());
			comment.setCountCommentLike(countCommentLike);

			//是否点赞
			boolean ifCommentLike = commentLikeService.ifCommentLike(comment.getUid() + "-" + comment.getCid(), uid);
			comment.setCommentLike(ifCommentLike);

			if (comment.getUpcid().equals("0")){
				//父级评论情况下
				//热门子评论
				List<Comment> showHotComment = showHotComment(comment.getUpid(), comment.getUid() + "-" + comment.getCid(),uid,some);
				comment.setSubCommentList(showHotComment);

				//评论数
				int i = countComment(comment.getUpid(), comment.getUid()+"-"+comment.getCid());
				comment.setCountComment(i);
			}
		}

		return commentList;
	}

	@Override
	//发评论
	public void doComment(Comment comment) {
		commentMapper.insertSelective(comment);
	}

	@Override
	//查评论数
	//数据库三个参数  upid：uid-pid  upcid：uid-parentcid  urcid：uid-replycid
	//只需要前两个参数  对应两种需求
	public int countComment(String upid, String upcid) {
		CommentExample commentExample=new CommentExample();
		if (upcid!=null){
			commentExample.createCriteria().andUpidEqualTo(upid).andUpcidEqualTo(upcid);
		}else {
			commentExample.createCriteria().andUpidEqualTo(upid);
		}
		int i = commentMapper.countByExample(commentExample);
		return i;
	}

	@Override
	//状态码默认1，正常；0，删除不可见；-1，被举报
	public void doDeleteComment(Integer cid) {
		Comment comment=new Comment();
		comment.setCid(cid);
		comment.setStatus(0);
		commentMapper.updateByPrimaryKeySelective(comment);
	}

}
