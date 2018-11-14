package com.txhl.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate配置类
 *
 * @author Administrator
 * @create 2018-10-14 16:46
 */
@Component
public class RestTemplateConfig {

    /**
     * 结合注解@LoadBalanced 实现跨程序访问最简方式
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }
}
