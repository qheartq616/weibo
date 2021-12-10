package org.example.weibo.utils;

import org.example.weibo.pojo.Post;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ListSortByDate {
	public static void doSort(List<Post> postList){
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
}
