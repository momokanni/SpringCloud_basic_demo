package com.txhl.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * class_name: EurekaApplication
 * package: com.txhl.eureka
 * describe:
 * @author: sl
 * creat_date: 2018/10/9
 * creat_time: 10:21
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}
}
