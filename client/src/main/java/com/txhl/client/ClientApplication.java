package com.txhl.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * class_name: ClientApplication
 * package: com.txhl.client
 * describe: 
 * @author: sl
 * creat_date: 2018/10/9
 * creat_time: 13:56
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
}
