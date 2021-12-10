package org.example.weibo.service;

import org.example.weibo.mapper.FollowMapper;
import org.example.weibo.mapper.UserMapper;
import org.example.weibo.pojo.Follow;
import org.example.weibo.pojo.FollowExample;
import org.example.weibo.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService{
	@Resource
	FollowMapper followMapper;
	@Resource
	UserMapper userMapper;
	@Resource
	GroupService groupService;

	@Override
	//根据uid找出关注列表（所有人）
	public List<User> showAllFollowUser(Integer uid) {
		FollowExample followExample=new FollowExample();
		followExample.createCriteria().andUidEqualTo(uid);
		List<Follow> followList = followMapper.selectByExample(followExample);

		List<User> allFollowUserList=new ArrayList<>();
		for (Follow follow : followList) {
			User user = userMapper.selectByPrimaryKey(follow.getFollowUid());
			allFollowUserList.add(user);
		}

		return allFollowUserList;
	}

	@Override
	//关注
	public void doFollow(Integer uid, Integer followUid) {
		Follow follow=new Follow();
		follow.setUid(uid);
		follow.setFollowUid(followUid);

		followMapper.insertSelective(follow);
	}

	@Override
	//取消关注
	public void doNotFollow(Integer uid, Integer followUid) {
		FollowExample followExample=new FollowExample();
		followExample.createCriteria().andUidEqualTo(uid).andFollowUidEqualTo(followUid);

		followMapper.deleteByExample(followExample);
	}

	@Override
	//显示我的粉丝
	//uid关注了followUid
	//followUid被哪些uid关注着
	public List<User> showAllFans(Integer followUid) {
		FollowExample followExample=new FollowExample();
		followExample.createCriteria().andFollowUidEqualTo(followUid);
		List<Follow> followedList = followMapper.selectByExample(followExample);

		List<User> fanList=new ArrayList<>();
		for (Follow follow : followedList) {
			User user = userMapper.selectByPrimaryKey(follow.getFollowUid());
			fanList.add(user);
		}

		return fanList;
	}

	@Override
	//关注状态
	//1：已关注  0：未关注  2：互相关注  3：自己
	public int followStatus(Integer uid, Integer followUid) {
		if (uid==followUid){
			return 3;
		}else {
			FollowExample followExample=new FollowExample();
			followExample.createCriteria().andUidEqualTo(uid).andFollowUidEqualTo(followUid);
			List<Follow> follows = followMapper.selectByExample(followExample);
			if (follows.size()==0){
				return 0;
			}else {
				FollowExample followedExample=new FollowExample();
				followedExample.createCriteria().andUidEqualTo(followUid).andFollowUidEqualTo(uid);
				List<Follow> followeds = followMapper.selectByExample(followExample);
				if (followeds.size()==0){
					return 1;
				}else {
					return 2;
				}
			}
		}

	}
}
