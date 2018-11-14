package com.txhl.order.refreshscopedemo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * IO配置类
 *
 * @author Administrator
 * @create 2018-11-04 15:24
 */
@Data
@Component
@ConfigurationProperties(value = "file")
@RefreshScope
public class FileConfig {

    private String codeFormat;

    private String localPath;
}
