package org.example.weibo.service;

import org.example.weibo.mapper.FollowMapper;
import org.example.weibo.mapper.UserMapper;
import org.example.weibo.pojo.Follow;
import org.example.weibo.pojo.FollowExample;
import org.example.weibo.pojo.User;
import org.example.weibo.utils.ListUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
	//关注或取关
	//5种状态码，操作后对应变成5种状态
	public int doFollow(Integer uid, Integer followUid) {
		int i = followStatus(uid, followUid);

		Follow follow=new Follow();
		follow.setUid(uid);
		follow.setFollowUid(followUid);

		FollowExample followExample=new FollowExample();
		followExample.createCriteria().andUidEqualTo(uid).andFollowUidEqualTo(followUid);

		int status=0;

		switch (i){
			case 0:
				List<Follow> followList1 = followMapper.selectByExample(followExample);
				if (followList1.size()==0){
					follow.setFollowTime(new Date());
					followMapper.insertSelective(follow);
				}
				//防止ajax跟不上  重复插入数据
				status = 1;
				break;
			case 1:
				followMapper.deleteByExample(followExample);
				groupService.deleteUserFromAllGroup(uid, followUid);
				status = 0;
				break;
			case 2:
				followMapper.deleteByExample(followExample);
				groupService.deleteUserFromAllGroup(uid, followUid);
				status = 4;
				break;
			case 3:
				status = 3;
				break;
			case 4:
				List<Follow> followList2 = followMapper.selectByExample(followExample);
				if (followList2.size()==0){
					follow.setFollowTime(new Date());
					followMapper.insertSelective(follow);
				}
				//防止ajax跟不上  重复插入数据
				status = 2;
				break;
		}
		return status;
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
			User user = userMapper.selectByPrimaryKey(follow.getUid());
			fanList.add(user);
		}

		return fanList;
	}

	@Override
	//关注状态
	//0：互相未关注  1：已关注  2：互相关注  3：自己  4：粉丝但未关注
	public int followStatus(Integer uid, Integer followUid) {
		if (uid==followUid){
			return 3;
		}else {
			FollowExample followExample=new FollowExample();
			followExample.createCriteria().andUidEqualTo(uid).andFollowUidEqualTo(followUid);
			List<Follow> follows = followMapper.selectByExample(followExample);

			FollowExample followedExample=new FollowExample();
			followedExample.createCriteria().andUidEqualTo(followUid).andFollowUidEqualTo(uid);
			List<Follow> followeds = followMapper.selectByExample(followedExample);
			//uid：session中的uid  followUid：当前被浏览的uid
			/*System.out.println("follows = " + follows.size());
			System.out.println("followeds = " + followeds.size());
			for (Follow follow : follows) {
				System.out.println("follow = " + follow.getUid());
				System.out.println("follow = " + follow.getFollowUid());
			}

			for (Follow followed : followeds) {
				System.out.println("followed = " + followed.getUid());
				System.out.println("followed = " + followed.getFollowUid());
			}*/

			if (follows.size()>0&&followeds.size()>0){
				return 2;
			}else if (followeds.size()==0&&follows.size()==0){
				return 0;
			}else if (follows.size()>0&&followeds.size()==0){
				return 1;
			}else {
				return 4;
			}
		}

	}

	@Override
	//互粉列表
	public List<User> showAllMutualFollowUser(Integer uid) {
		List<User> allFans = showAllFans(uid);
		List<User> allFollowUser = showAllFollowUser(uid);
		//所有粉丝和所有关注，重叠部分即为互相关注
		List<User> unionList = ListUtil.unionList(allFans, allFollowUser);
		ListUtil.sortByUid(unionList);
		/*System.out.println("1111111");
		System.out.println("allFans = " + allFans.size());
		System.out.println("allFollowUser = " + allFollowUser.size());
		for (User allFan : allFans) {
			System.out.println("allFan.getUid() = " + allFan.getUid());
		}
		for (User user : allFollowUser) {
			System.out.println("user.getUid() = " + user.getUid());
		}

		System.out.println("unionList = " + unionList.size());
		for (User user : unionList) {
			System.out.println("user = " + user.getUid());
		}
		System.out.println("2222222");*/
		return unionList;
	}
}
