package com.txhl.order.controller;

import com.txhl.product.client.ProductClient;
import com.txhl.product.common.ProductOutput;
import com.txhl.product.common.ReduceStockInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

/**
 * Product客户端
 *
 * @author Administrator
 * @create 2018-10-14 16:14
 */
@RestController
@Slf4j
public class ClientController {

    @Value("${env}")
    private String env;
    /*@Autowired
    private LoadBalancerClient loadBalancerClient;*/

    /*@Autowired
    private RestTemplate restTemplate;*/

    @Autowired
    private ProductClient pc;

    @GetMapping(value = "getProductMsg")
    public void getProductMsg(){
        // 跨程序访问第一种方式(直接使用RestTemplate，路径手写固定)
        /*RestTemplate restTemplate = new RestTemplate();
        String msg = restTemplate.getForObject("http://127.0.0.1:8080/msg",String.class);*/

        // 第二种方式（使用LoadBalancerClient，通过serviceId，通过负载获取url，再结合RestTemplate）
        /*ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT\t");
        String url = String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort() + "/msg");
        RestTemplate restTemplate = new RestTemplate();
        String msg = restTemplate.getForObject(url,String.class);*/

        // 第三种方式
        /*String msg = restTemplate.getForObject("http://PRODUCT/msg",String.class);*/

        // 使用Feign
        //String msg = pc.productMsg();
        //log.info("连接成功：{}",msg);
    }

    @GetMapping(value = "/getProductList")
    public String getProducts(){
        List<ProductOutput> products = pc.listForOrder(Arrays.asList("1524800799092345144","1524800224429470815"));
        log.info("response={}",products);
        return "success";
    }

    @GetMapping(value = "/reduceStock")
    public String reduceStock(){
        pc.reduceStock(Arrays.asList(new ReduceStockInput("1524800143330475521",2)));
        return "ok";
    }

    @GetMapping(value = "/envParam")
    public String printEnvParam(){

        return env;
    }

}
