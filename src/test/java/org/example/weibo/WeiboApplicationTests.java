package org.example.weibo;

import org.example.weibo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class WeiboApplicationTests {
	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Test
	void contextLoads() {
		/*Random random=new Random();

		for (int r=0;r<20;r++){
			int i= random.nextInt(10);
			System.out.println("i = " + i);
		}*/
		stringRedisTemplate.opsForValue().append("msg","helloSpringRedis");
		String msg = stringRedisTemplate.opsForValue().get("msg");
		System.out.println("msg = " + msg);
	}

	public static void main(String[] args) {
		List<User> userList1=new ArrayList<>();
		List<User> userList2=new ArrayList<>();
		User user1=new User();
		user1.setUid(1);
		User user2=new User();
		user2.setUid(2);
		User user3=new User();
		user3.setUid(3);
		User user4=new User();
		user4.setUid(3);
		User user5=new User();
		user5.setUid(4);
		User user6=new User();
		user6.setUid(5);
		userList1.add(user1);
		userList1.add(user2);
		userList1.add(user3);
		userList2.add(user4);
		userList2.add(user5);
		userList2.add(user6);

		for (User user : userList1) {
			System.out.println("userL1 =" + user.getUid());
		}

		for (User user : userList2) {
			System.out.println("userL2 = " + user.getUid());
		}

		userList1.retainAll(userList2);

		for (User user : userList1) {
			System.out.println("userRetain =" + user.getUid());
		}

	}

}
