package com.txhl.order.refreshscopedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IO处理层
 *
 * @author Administrator
 * @create 2018-11-04 15:27
 */
@RestController
public class IOController {

    @Autowired
    private FileConfig fileConfig;

    @GetMapping(value = "IOPrint")
    public String IOPrint(){
        return "format: " + fileConfig.getCodeFormat() + ", localPath: " + fileConfig.getLocalPath();
    }
}
