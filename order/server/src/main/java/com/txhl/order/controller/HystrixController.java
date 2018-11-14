package com.txhl.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

/**
 * 服务降级处理
 *
 * @author Administrator
 * @create 2018-11-12 17:25
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController{

    /*@HystrixCommand(commandProperties = {
            // @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
            // 设置断路器状态
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            // 产生熔断的休眠时间窗
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            // 断路器在滚动时间内最小请求数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            // 误差阈值百分率 请求10次，发生异常概率超过60%，触发熔断，断路器呈打开状态
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })*/
    @HystrixCommand
    @GetMapping(value = "/getHystrixProductList")
    public String getHystrixProductList(@RequestParam Integer num){

        if(num % 2 ==0){
            return "success";
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://127.0.0.1:8087/getProductList",
                Arrays.asList("1524800892136358889"),String.class);
    }

    private String defaultFallback(){
        return "默认的服务降级";
    }
}
