package org.example.weibo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.weibo.mapper.PostMapper;
import org.example.weibo.mapper.UserMapper;
import org.example.weibo.pojo.Post;
import org.example.weibo.pojo.PostExample;
import org.example.weibo.pojo.User;
import org.example.weibo.utils.ListUtil;
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
	PostLikeService postLikeService;
	@Resource
	GroupService groupService;
	@Resource
	UserMapper userMapper;
	@Resource
	CommentService commentService;
	@Resource
	UserService userService;

	//微博状态码：-1：仅自己可见，0：删除，1：公开，2：好友圈，3：粉丝，4：置顶（置顶微博必须公开）
	@Override
	//curUid的首页  查询curUid的所有微博
	public PageInfo<Post> showUserAllPost(Integer uid,Integer curUid,Integer pageNum) {
		PageHelper.startPage(pageNum,7);
		PostExample postExample=new PostExample();
		postExample.setOrderByClause("post_time desc");
		postExample.createCriteria().andUidEqualTo(curUid).andStatusNotEqualTo(0).andStatusNotEqualTo(4);
		List<Post> postList = postMapper.selectByExample(postExample);
		PageInfo<Post> pageInfo=new PageInfo<>(postList);

		List<Post> filledPostList = fillPostInfo(pageInfo.getList(), uid);
		pageInfo.setList(filledPostList);
		/*for (Post post : postList) {
			System.out.println("post.getPostTime() = " + post.getPostTime());
		}*/
		return pageInfo;
	}

	@Override
	//首页  查询所有关注的人的最近所有微博
	//pagehelper在sql语句中插入limit进行分页，因此本方法不能用pagehelper分页
	//貌似也可以。。。用in查询
	//随机查询最新微博
	public PageInfo<Post> showAllFollowUserPostRandom(Integer uid, Integer pageNum) {
		List<User> allFollowUserList = followService.showAllFollowUser(uid);

		List<User> subRandomUserList= ListUtil.subRandomUserList(allFollowUserList);

		List<Integer> uidList=new ArrayList<>();
		for (User user : subRandomUserList) {
			uidList.add(user.getUid());
		}

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
		/*for (Post post : pageInfo.getList()) {
			//加入点赞状态
			boolean b = postLikeService.ifPostLike(post.getPid(), uid);
			post.setPostLike(b);

			//加入点赞数
			int i = postLikeService.countPostLike(post.getPid());
			post.setCountPostLike(i);
		}*/
		List<Post> filledPostList = fillPostInfo(pageInfo.getList(), uid);
		pageInfo.setList(filledPostList);

		return pageInfo;
	}

	@Override
	//最新微博
	public PageInfo<Post> showAllFollowUserPostLatest(Integer uid, Integer pageNum) {
		List<User> allFollowUserList = followService.showAllFollowUser(uid);

		List<Integer> uidList=new ArrayList<>();
		for (User user : allFollowUserList) {
			uidList.add(user.getUid());
		}

		//int countAllUid = userMapper.countByExample(null);

		PageHelper.startPage(pageNum,7);
		PostExample postExample=new PostExample();
		postExample.setOrderByClause("post_time desc");
		postExample.createCriteria().andUidIn(uidList).andStatusNotEqualTo(0);

		List<Post> postList = postMapper.selectByExample(postExample);
		PageInfo<Post> pageInfo=new PageInfo<>(postList);
		/*for (Post post : pageInfo.getList()) {
			//加入点赞状态
			boolean b = postLikeService.ifPostLike(post.getPid(), uid);
			post.setPostLike(b);

			//加入点赞数
			int i = postLikeService.countPostLike(post.getPid());
			post.setCountPostLike(i);
		}*/
		List<Post> filledPostList = fillPostInfo(pageInfo.getList(), uid);
		pageInfo.setList(filledPostList);

		return pageInfo;
	}

	@Override
	//好友圈微博
	//其实我应该三个合到一起的。。。
	public PageInfo<Post> showAllFollowUserPostMutual(Integer uid, Integer pageNum) {
		List<User> allFollowUserList = followService.showAllMutualFollowUser(uid);

		List<Integer> uidList=new ArrayList<>();
		for (User user : allFollowUserList) {
			uidList.add(user.getUid());
			//System.out.println("user.getUid() = " + user.getUid());
		}

		PageHelper.startPage(pageNum,7);
		PostExample postExample=new PostExample();
		postExample.setOrderByClause("post_time desc");
		postExample.createCriteria().andUidIn(uidList).andStatusNotEqualTo(0);

		List<Post> postList = postMapper.selectByExample(postExample);
		PageInfo<Post> pageInfo=new PageInfo<>(postList);
		/*for (Post post : pageInfo.getList()) {
			//加入点赞状态
			boolean b = postLikeService.ifPostLike(post.getPid(), uid);
			post.setPostLike(b);

			//加入点赞数
			int i = postLikeService.countPostLike(post.getPid());
			post.setCountPostLike(i);
		}*/
		List<Post> filledPostList = fillPostInfo(pageInfo.getList(), uid);
		pageInfo.setList(filledPostList);

		return pageInfo;
	}

	@Override
	//热门微博
	public PageInfo<Post> showAllFollowUserPostHottest(Integer uid, Integer pageNum) {
		PostExample postExample=new PostExample();
		postExample.createCriteria().andUidNotEqualTo(uid).andStatusEqualTo(1);
		List<Post> posts = postMapper.selectByExample(postExample);

		List<Post> posts1 = fillPostInfo(posts, uid);

		PageInfo<Post> pageInfo=new PageInfo<>(posts1);
		pageInfo.setList(ListUtil.sortByPostHeat(posts1));

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
	//包含了特别关注、未分组关注
	//gid为0，特别关注；gid为-1，未分组关注
	public PageInfo<Post> showGroupAllPost(Integer uid,Integer gid,Integer pageNum) {
		List<User> userList=new ArrayList<>();
		if (gid==0){
			User user = groupService.showSpUser(uid);
//			System.out.println("user = " + user.toString());
			if (user==null){
				return null;
			}else {
				userList.add(groupService.showSpUser(uid));
			}
		}else if (gid==-1){
			userList=groupService.showAllNoGroupUser(uid);
		}else {
			userList = groupService.showAllFollowUser(gid);
		}

//		System.out.println("userList = " + userList.size());
		/*if (userList.size()==0){
			return null;
		}else {*/
		List<Integer> uidList=new ArrayList<>();
			if(userList!=null){
			for (User user : userList) {
				if(user.getUid()!=0){
				uidList.add(
						user.getUid());
				}
			}
			PageHelper.startPage(pageNum,7);
			PostExample postExample=new PostExample();
			postExample.setOrderByClause("post_time desc");
			postExample.createCriteria().andUidIn(uidList);
			List<Post> postList = postMapper.selectByExample(postExample);
			PageInfo<Post> pageInfo=new PageInfo<>(postList);
			List<Post> filledPostList = fillPostInfo(pageInfo.getList(), uid);
			pageInfo.setList(filledPostList);
	/*for (Post post : pageInfo.getList()) {
		//加入点赞状态
		boolean b = postLikeService.ifPostLike(post.getPid(), uid);
		post.setPostLike(b);

		//加入点赞数
		int i = postLikeService.countPostLike(post.getPid());
		post.setCountPostLike(i);
	}*/
			return pageInfo;
			}else {
				return null;
			}

		/*}*/
	}

	@Override
	//删除
	//删除有必要整成逻辑删除吗。。
	//有吧
	public void doDelete(Integer pid) {
		Post post=new Post();
		post.setPid(pid);
		post.setStatus(0);
		postMapper.updateByPrimaryKeySelective(post);
	}

	@Override
	//置顶、取消置顶微博
	public void doTopPost(Integer uid, Integer pid) {
		//三种情况，1、没有置顶--置顶pid微博；有置顶：2、取消原置顶（pid），3、或取消原置顶（uid查出来）添加新置顶（pid）
		//微博状态码：-1：仅自己可见，0：删除，1：公开，2：好友圈，3：粉丝，4：置顶（置顶微博必须公开）
		PostExample postExample=new PostExample();
		postExample.createCriteria().andUidEqualTo(uid).andStatusEqualTo(4);
		List<Post> posts = postMapper.selectByExample(postExample);

		Post newTopPost=new Post();
		newTopPost.setPid(pid);
		newTopPost.setStatus(4);

		if (posts.size()==0){
			//1
			postMapper.updateByPrimaryKeySelective(newTopPost);
		}else {
			Post oldTopPost = posts.get(0);
			oldTopPost.setStatus(1);
			postMapper.updateByPrimaryKeySelective(oldTopPost);
			if (oldTopPost.getPid()==pid){
				//2
			}else {
				//3
				postMapper.updateByPrimaryKeySelective(newTopPost);
			}
		}
	}

	@Override
	//单独查询置顶微博
	public Post showTopPost(Integer uid) {
		PostExample postExample=new PostExample();
		postExample.createCriteria().andStatusEqualTo(4).andUidEqualTo(uid);
		List<Post> posts = postMapper.selectByExample(postExample);
		if (posts.size()==0){
			return null;
		}else {
			/*Post topPost = posts.get(0);
			//加入点赞状态
			boolean b = postLikeService.ifPostLike(topPost.getPid(), uid);
			topPost.setPostLike(b);

			//加入点赞数
			int i = postLikeService.countPostLike(topPost.getPid());
			topPost.setCountPostLike(i);*/
			List<Post> filledPostList = fillPostInfo(posts, uid);
			return filledPostList.get(0);
		}
	}

	@Override
	//每条微博信息补全
	public List<Post> fillPostInfo(List<Post> postList,Integer uid) {
		for (Post post : postList) {
			//加入点赞状态
			boolean ifPostLike = postLikeService.ifPostLike(post.getUser().getUid()+"-"+post.getPid(), uid);
			post.setPostLike(ifPostLike);

			//加入点赞数
			int countPostLike = postLikeService.countPostLike(post.getUser().getUid()+"-"+post.getPid());
			post.setCountPostLike(countPostLike);

			//加入评论数
			int i = commentService.countComment(post.getUid() + "-" + post.getPid(), null);
			post.setCountPostComment(i);
			/*加入所有评论
			这种写法太耗资源
			List<Comment> allCommentList = commentService.showAllComment(post.getPid());
			post.setCommentList(allCommentList);*/
		}
		return postList;
	}

	@Override
	//删除评论返回值
	public Post showPost(Integer pid) {
		Post post = postMapper.selectByPrimaryKey(pid);
		return post;
	}

	@Override
	//查询个人最热门微博
	public Post hotPost(Integer curUid) {
		PostExample postExample=new PostExample();
		postExample.createCriteria().andUidEqualTo(curUid);
		List<Post> posts = postMapper.selectByExample(postExample);
		List<Post> posts1 = fillPostInfo(posts, curUid);
		Post hotPost = ListUtil.sortByPostHeatSingle(posts1).get(posts1.size()-1);
		return hotPost;
	}


}
