package com.enjoy.lockdemo;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LockDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LockDemoApplication.class, args);
	}

	@Bean
	public Redisson redisson(){
		Config config = new Config();
		config.useSingleServer().setAddress("redis://47.94.209.239:6379").setDatabase(0);
		return (Redisson) Redisson.create(config);
	}

}
