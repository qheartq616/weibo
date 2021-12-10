package org.example.weibo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.weibo.mapper.PostMapper;
import org.example.weibo.pojo.Post;
import org.example.weibo.pojo.PostExample;
import org.example.weibo.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{
	@Resource
	PostMapper postMapper;
	@Resource
	FollowService followService;
	@Resource
	LoveService loveService;
	@Resource
	GroupService groupService;

	@Override
	//curUid的首页  查询curUid的所有微博
	public PageInfo<Post> showUserAllPost(Integer uid,Integer curUid,Integer pageNum) {
		PageHelper.startPage(pageNum,7);
		PostExample postExample=new PostExample();
		postExample.setOrderByClause("post_time desc");
		postExample.createCriteria().andUidEqualTo(curUid);
		List<Post> postList = postMapper.selectByExample(postExample);
		PageInfo<Post> pageInfo=new PageInfo<>(postList);

		for (Post post : pageInfo.getList()) {
			//加入点赞状态
			boolean b = loveService.ifLove(post.getPid(), uid);
			post.setLike(b);

			//加入点赞数
			int i = loveService.countLove(post.getPid());
			post.setCountLike(i);
		}
		/*for (Post post : postList) {
			System.out.println("post.getPostTime() = " + post.getPostTime());
		}*/


		return pageInfo;
	}

	@Override
	//首页  查询所有关注的人的最近所有微博
	//pagehelper在sql语句中插入limit进行分页，因此本方法不能用pagehelper分页
	//貌似也可以。。。用in查询
	public PageInfo<Post> showAllFollowUserPost(Integer uid,Integer pageNum) {
		List<User> allFollowUserList = followService.showAllFollowUser(uid);

		List<Integer> uidList=new ArrayList<>();
		for (User user : allFollowUserList) {
			uidList.add(user.getUid());
		}

		PageHelper.startPage(pageNum,7);
		PostExample postExample=new PostExample();
		postExample.setOrderByClause("post_time desc");
		postExample.createCriteria().andUidIn(uidList);
		/*for (User followUser : allFollowUserList) {
			Integer followUserUid = followUser.getUid();
			List<Post> postList = showUserAllPost(followUserUid);

			for (Post post : postList) {
				//加入点赞状态
				boolean b = loveService.ifLove(post.getPid(), uid);
				post.setLike(b);

				//加入点赞数
				int i = loveService.countLove(post.getPid());
				post.setCountLike(i);
			}
			allPostList.addAll(postList);
		}*/
		//根据日期排序
		//ListSortByDate.doSort(allPostList);
		List<Post> postList = postMapper.selectByExample(postExample);
		PageInfo<Post> pageInfo=new PageInfo<>(postList);
		for (Post post : pageInfo.getList()) {
			//加入点赞状态
			boolean b = loveService.ifLove(post.getPid(), uid);
			post.setLike(b);

			//加入点赞数
			int i = loveService.countLove(post.getPid());
			post.setCountLike(i);
		}

		return pageInfo;
	}

	@Override
	//发微博
	public void doPost(Post post) {
		postMapper.insertSelective(post);
	}

	@Override
	//总共发了多少条微博？
	public int countPost(Integer uid) {
		PostExample postExample=new PostExample();
		postExample.createCriteria().andUidEqualTo(uid);
		return postMapper.countByExample(postExample);
	}

	@Override
	//展示分组成员的所有微博
	public PageInfo<Post> showGroupAllPost(Integer uid,Integer gid,Integer pageNum) {
		List<User> userList = groupService.showAllFollowUser(gid);
		List<Integer> uidList=new ArrayList<>();
		for (User user : userList) {
			uidList.add(user.getUid());
		}

		PageHelper.startPage(pageNum,7);
		PostExample postExample=new PostExample();
		postExample.setOrderByClause("post_time desc");
		postExample.createCriteria().andUidIn(uidList);
		List<Post> postList = postMapper.selectByExample(postExample);
		PageInfo<Post> pageInfo=new PageInfo<>(postList);

		for (Post post : pageInfo.getList()) {
			//加入点赞状态
			boolean b = loveService.ifLove(post.getPid(), uid);
			post.setLike(b);

			//加入点赞数
			int i = loveService.countLove(post.getPid());
			post.setCountLike(i);
		}

		return pageInfo;
	}


}
