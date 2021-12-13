package org.example.weibo.service;

import org.example.weibo.mapper.GroupMapper;
import org.example.weibo.mapper.UserMapper;
import org.example.weibo.pojo.Group;
import org.example.weibo.pojo.GroupExample;
import org.example.weibo.pojo.User;
import org.example.weibo.utils.ListDistinctByKey;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService{
	@Resource
	GroupMapper groupMapper;
	@Resource
	UserMapper userMapper;

	@Override
	//展示所有分组（分组名字）
	public List<Group> showAllGroupList(Integer uid) {
		GroupExample groupExample=new GroupExample();
		groupExample.createCriteria().andUidEqualTo(uid);

		List<Group> groupList = groupMapper.selectByExample(groupExample);
		//同一组的不同成员会导致数据重复  只需要一个名字就行
		List<Group> distinctGroupList=groupList.stream().filter(ListDistinctByKey.doDistinct(Group::getName)).collect(Collectors.toList());

		return distinctGroupList;
	}

	@Override
	//找出该分组所有关注着的人
	//只需要一个gid
	public List<User> showAllFollowUser(Integer gid) {
		GroupExample groupExample=new GroupExample();
		groupExample.createCriteria().andGidEqualTo(gid);
		List<Group> groupList = groupMapper.selectByExample(groupExample);

		/*for (Group group : groupList) {
			System.out.println("group = " + group.toString());
		}*/

		if (groupList.size()>0){
			List<User> followUserList=new ArrayList<>();
			for (Group group : groupList) {
				User user = userMapper.selectByPrimaryKey(group.getFollowUid());
				followUserList.add(user);
			}

			return followUserList;
		}else {
			return null;
		}
	}

	@Override
	//建组  取名字
	public void createGroup(Group group) {
		groupMapper.insertSelective(group);
	}

	@Override
	//修改组的名字
	public void modifyGroupName(Integer gid, String name) {
		Group group=new Group();
		group.setGid(gid);
		group.setName(name);

		GroupExample groupExample=new GroupExample();
		groupExample.createCriteria().andGidEqualTo(gid).andNameEqualTo(name);

		groupMapper.updateByExampleSelective(group,groupExample);
	}

	@Override
	//从该组移除某人
	//移除出组但不取关
	//取关的时候要==不==用该方法
	public void deleteUserFromGroup(Integer gid, Integer uid) {
		GroupExample groupExample=new GroupExample();
		groupExample.createCriteria().andGidEqualTo(gid).andUidEqualTo(uid);

		groupMapper.deleteByExample(groupExample);
	}

	@Override
	//取关的时候要==调==用该方法
	public void deleteUserFromAllGroup(Integer uid, Integer followUid) {

	}

}
