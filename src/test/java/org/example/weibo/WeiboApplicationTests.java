package org.example.weibo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class WeiboApplicationTests {

	@Test
	void contextLoads() {
		Random random=new Random();

		for (int r=0;r<20;r++){
			int i= random.nextInt(10);
			System.out.println("i = " + i);
		}
	}

}
