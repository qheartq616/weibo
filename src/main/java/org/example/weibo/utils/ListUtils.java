package org.example.weibo.utils;

import org.example.weibo.pojo.Post;
import org.example.weibo.pojo.User;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListUtils {
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	public static void sortByPostTime(List<Post> postList){
		Collections.sort(postList, new Comparator<Post>() {
			@Override
			public int compare(Post o1, Post o2) {
				//由大到小排序
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String d1=simpleDateFormat.format(o1.getPostTime());
				String d2=simpleDateFormat.format(o2.getPostTime());
				if (o1.getPostTime().getTime()<o2.getPostTime().getTime()){
					return 1;
				}else if (o1.getPostTime().getTime()>o2.getPostTime().getTime()){
					return -1;
				}else {
					return 0;
				}
			}
		});
	}

	public static List<User> unionList(List<User> oldArrayList, List<User> newArrayList) {
		List<User> unionUserList = newArrayList.stream()
				.filter(item -> oldArrayList.stream().map(e -> e.getUid())
						.collect(Collectors.toList()).contains(item.getUid()))
				.collect(Collectors.toList());
		return unionUserList;
	}

	public static void sortByUid(List<User> userList){
		Collections.sort(userList, new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				//由大到小排序

				if (o1.getUid() < o2.getUid()){
					return 1;
				}else if (o1.getUid() > o2.getUid()){
					return -1;
				}else {
					return 0;
				}
			}
		});
	}

	public static List<User> subRandomUserList(List<User> userList){
		Set<User> set = new HashSet<>();
		while (set.size() < (userList.size()/2)||set.size()<=1){
			Random random=new Random();
			set.add(userList.get(random.nextInt(userList.size())));
		}
		List<User> subRandomUserList=new ArrayList<>();
		/*for (User user : set) {
			System.out.println("user = " + user.getUid());
		}*/

		subRandomUserList.addAll(set);
		return subRandomUserList;
	}
}
