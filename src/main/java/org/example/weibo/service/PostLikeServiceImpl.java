package org.example.weibo.service;

import org.example.weibo.mapper.LoveMapper;
import org.example.weibo.mapper.PostLikeMapper;
import org.example.weibo.mapper.PostMapper;
import org.example.weibo.pojo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostLikeServiceImpl implements PostLikeService {
	@Resource
	PostLikeMapper postLikeMapper;
	@Resource
	PostMapper postMapper;

	@Override
	//点赞数
	public int countPostLike(Integer pid) {
		PostLikeExample postLikeExample=new PostLikeExample();
		postLikeExample.createCriteria().andPidEqualTo(pid);
		int i = postLikeMapper.countByExample(postLikeExample);
		return i;
	}

	@Override
	//是否点了赞（需要根据登陆的uid查数据，存入不同post对象中）
	//uid为登录的人（看见这条微博的人）的id，不是发这条微博的人的id！
	//没点赞，查不到数据，返回false，否则为true
	public boolean ifPostLike(Integer pid, Integer uid) {
		PostLikeExample postLikeExample=new PostLikeExample();
		postLikeExample.createCriteria().andPidEqualTo(pid).andUidEqualTo(uid);
		List<PostLike> loves = postLikeMapper.selectByExample(postLikeExample);
		//System.out.println("loves.size() = " + loves.size());
		if (loves.size()==0){
			return false;
		}else {
			return true;
		}
	}


	@Override
	//三种状态（好像不用点踩？），两个状态码
	//先判断是否点过赞  查有没有状态数据
	//再查状态为0还是1
	//可能需要重新写like表
	////////////////////////////////
	//改了，去掉了like列和like属性
	//点赞插入like表对应uid、pid，取消点赞根据uid、pid删除like表对应数据
	public boolean doPostLike(Integer pid, Integer uid) {
		boolean selectLove = this.ifPostLike(pid, uid);

		PostLike like1=new PostLike();
		like1.setPid(pid);
		like1.setUid(uid);

		if (!selectLove){
			//System.out.println("777");
			postLikeMapper.insertSelective(like1);
			return true;
		}else {
			//System.out.println("444");
			PostLikeExample postLikeExample=new PostLikeExample();
			postLikeExample.createCriteria().andPidEqualTo(pid).andUidEqualTo(uid);
			postLikeMapper.deleteByExample(postLikeExample);
			return false;
		}
	}

	@Override
	//uid总共收到多少个点赞？
	public int countAllPostLike(Integer uid) {
		PostExample postExample=new PostExample();
		postExample.createCriteria().andUidEqualTo(uid);
		List<Post> postList = postMapper.selectByExample(postExample);
		List<Integer> pidList=new ArrayList<>();
		for (Post post : postList) {
			pidList.add(post.getPid());
		}
		if (pidList.size()>0){
			PostLikeExample postLikeExample=new PostLikeExample();
			postLikeExample.createCriteria().andPidIn(pidList);

			return postLikeMapper.countByExample(postLikeExample);
		}else {
			return 0;
		}
	}


}
