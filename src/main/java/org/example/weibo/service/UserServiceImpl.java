package org.example.weibo.service;

import org.example.weibo.mapper.UserMapper;
import org.example.weibo.pojo.User;
import org.example.weibo.pojo.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	UserMapper userMapper;
	@Resource
	PostService postService;
	@Resource
	PostLikeService postLikeService;
	@Resource
	FollowService followService;

	@Override
	//登陆
	public User login(String name, String password) {
		if (name!=null&&password!=null){
			UserExample userExample=new UserExample();
			userExample.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
			List<User> userList = userMapper.selectByExample(userExample);
			if (userList.isEmpty()){
				return null;
			}else {
				return userList.get(0);
			}
		}else {
			return null;
		}
	}

	@Override
	//检查是否重名
	public boolean ifRegister(String name) {
		UserExample userExample=new UserExample();
		userExample.createCriteria().andNameEqualTo(name);
		List<User> users = userMapper.selectByExample(userExample);
		if (users==null){
			return true;
		}else {
			return false;
		}
	}

	@Override
	//注册
	public void registerUser(User user) {
		userMapper.insertSelective(user);
	}

	@Override
	//检查距离上次改名时间是否大于100天
	public boolean ifUpdateNameGreaterThan100(String name) {
		UserExample userExample=new UserExample();
		userExample.createCriteria().andNameEqualTo(name);
		List<User> users = userMapper.selectByExample(userExample);
		Date updateNameTime = users.get(1).getUpdateNameTime();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dateOld = simpleDateFormat.parse(updateNameTime + "");
			Date dateNew = simpleDateFormat.parse(new Date()+"");
			if (dateOld.getTime()>dateNew.getTime()){
				//时间戳比较不了  以后再说
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	//更新个人信息
	public void updateUserInfo(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	//改密码
	public void updatePassword(Integer uid, String password) {
		User user=new User();
		user.setUid(uid);
		user.setPassword(password);
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	//填充信息
	//粉丝量、关注量、微博量、点赞量、session用户对当前页用户的关注状态
	public User fillUserInfo(User user,User userCur) {
		Integer userCurUid=userCur.getUid();

		List<User> allFollowUser = followService.showAllFollowUser(userCurUid);
		userCur.setCountAllFollowUser(allFollowUser.size());

		List<User> allFans = followService.showAllFans(userCurUid);
		userCur.setCountAllFans(allFans.size());

		int countAllLike = postLikeService.countAllPostLike(userCurUid);
		userCur.setCountAllLike(countAllLike);

		int countPost = postService.countPost(userCurUid);
		userCur.setCountAllPost(countPost);
		//当前访问页面的uid（不一定为session.user.uid）
		//model.addAttribute("uid",curUid);

		int followStatus = followService.followStatus(user.getUid(), userCurUid);
		userCur.setFollowStatus(followStatus);

		return userCur;
	}

	@Override
	//信息卡片，通过uid获取
	public User getInfoByUid(Integer uid,Integer curUid) {
		User user = userMapper.selectByPrimaryKey(uid);
		User userCur = userMapper.selectByPrimaryKey(curUid);

		return fillUserInfo(user,userCur);
	}


}
