package org.example.weibo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "org.example.weibo.mapper")
@SpringBootApplication
public class WeiboApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeiboApplication.class, args);
	}

}
