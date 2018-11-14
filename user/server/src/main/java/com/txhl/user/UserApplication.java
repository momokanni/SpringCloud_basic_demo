package com.txhl.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * class_name: UserApplication
 * describe:
 * @author: sl
 * @create: 2017/11/10
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class UserApplication {

	public static void main(String[] args) {

		SpringApplication.run(UserApplication.class, args);
	}
}
