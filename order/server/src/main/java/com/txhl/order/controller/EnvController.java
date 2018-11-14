package com.txhl.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 环境变量
 *
 * @author Administrator
 * @create 2018-11-04 15:13
 */
@RestController
@RequestMapping(value = "/env")
@RefreshScope
public class EnvController {

    @Value(value = "${env}")
    private String env;

    @GetMapping(value = "print")
    public String print(){
        return env;
    }
}
