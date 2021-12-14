package org.example.weibo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.weibo.mapper.PostMapper;
import org.example.weibo.mapper.UserMapper;
import org.example.weibo.pojo.Post;
import org.example.weibo.pojo.PostExample;
import org.example.weibo.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PostServiceImpl implements PostService{
	@Resource
	PostMapper postMapper;
	@Resource
	FollowService followService;
	@Resource
	PostLikeService postLikeService;
	@Resource
	GroupService groupService;
	@Resource
	UserMapper userMapper;

	@Override
	//curUid的首页  查询curUid的所有微博
	public PageInfo<Post> showUserAllPost(Integer uid,Integer curUid,Integer pageNum) {
		PageHelper.startPage(pageNum,7);
		PostExample postExample=new PostExample();
		postExample.setOrderByClause("post_time desc");
		postExample.createCriteria().andUidEqualTo(curUid).andStatusNotEqualTo(0);
		List<Post> postList = postMapper.selectByExample(postExample);
		PageInfo<Post> pageInfo=new PageInfo<>(postList);

		for (Post post : pageInfo.getList()) {
			//加入点赞状态
			boolean b = postLikeService.ifPostLike(post.getPid(), uid);
			post.setPostLike(b);

			//加入点赞数
			int i = postLikeService.countPostLike(post.getPid());
			post.setCountPostLike(i);
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
		postExample.createCriteria().andUidIn(uidList).andStatusNotEqualTo(0);
		/*for (User followUser : allFollowUserList) {
			Integer followUserUid = followUser.getUid();
			List<Post> postList = showUserAllPost(followUserUid);

			for (Post post : postList) {
				//加入点赞状态
				boolean b = loveService.ifPostLike(post.getPid(), uid);
				post.setLike(b);

				//加入点赞数
				int i = loveService.countPostLike(post.getPid());
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
			boolean b = postLikeService.ifPostLike(post.getPid(), uid);
			post.setPostLike(b);

			//加入点赞数
			int i = postLikeService.countPostLike(post.getPid());
			post.setCountPostLike(i);
		}

		return pageInfo;
	}

	@Override
	public PageInfo<Post> showAllFollowUserPostRandom(Integer uid, Integer pageNum) {
		List<User> allFollowUserList = followService.showAllFollowUser(uid);

		List<Integer> uidList=new ArrayList<>();
		for (User user : allFollowUserList) {
			uidList.add(user.getUid());
		}

		int countAllUid = userMapper.countByExample(null);

		PageHelper.startPage(pageNum,7);
		PostExample postExample=new PostExample();
		postExample.setOrderByClause("post_time desc");
		Random random=new Random(1);
		int ran = random.nextInt(2);
		if (ran==0){
			postExample.createCriteria().andUidIn(uidList).andStatusNotEqualTo(0).andUidGreaterThan(countAllUid);
		}else {
			postExample.createCriteria().andUidIn(uidList).andStatusNotEqualTo(0).andUidLessThanOrEqualTo(countAllUid);
		}

		List<Post> postList = postMapper.selectByExample(postExample);
		PageInfo<Post> pageInfo=new PageInfo<>(postList);
		for (Post post : pageInfo.getList()) {
			//加入点赞状态
			boolean b = postLikeService.ifPostLike(post.getPid(), uid);
			post.setPostLike(b);

			//加入点赞数
			int i = postLikeService.countPostLike(post.getPid());
			post.setCountPostLike(i);
		}

		return pageInfo;
	}

	@Override
	//发微博
	public Post doPost(Post post) {
		postMapper.insertSelective(post);
		PostExample postExample=new PostExample();
		postExample.createCriteria().andTextEqualTo(post.getText()).andPostTimeEqualTo(post.getPostTime());
		List<Post> posts = postMapper.selectByExample(postExample);
		return posts.get(0);
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
			boolean b = postLikeService.ifPostLike(post.getPid(), uid);
			post.setPostLike(b);

			//加入点赞数
			int i = postLikeService.countPostLike(post.getPid());
			post.setCountPostLike(i);
		}

		return pageInfo;
	}

	@Override
	public void delete(Integer pid) {
		Post post=new Post();
		post.setPid(pid);
		post.setStatus(0);
		postMapper.updateByPrimaryKeySelective(post);
	}


}
