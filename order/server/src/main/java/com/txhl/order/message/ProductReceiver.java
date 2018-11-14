package com.txhl.order.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.txhl.order.utils.JsonUtil;
import com.txhl.product.common.ProductOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 产品信息接收类
 *
 * @author Administrator
 * @create 2018-11-08 22:14
 */
@Slf4j
@Component
public class ProductReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE="product_stock_%s";

    @Autowired
    private StringRedisTemplate redisTemplate;


    @RabbitListener(queuesToDeclare = @Queue("productQueue"))
    public void process(String msg){
        // msg --> List<ProductOutput>
        List<ProductOutput> productOutput = (List<ProductOutput>)JsonUtil.fromJson(msg,
                new TypeReference<List<ProductOutput>>() {});
        log.info("从队列【{}】收到消息：{}","productQueue",productOutput);

        // 将转换的数据，遍历插入到redis中
        for (ProductOutput productOutput1 : productOutput) {
            redisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE,productOutput1.getProductId()),String.valueOf(productOutput1.getProductStock()));
        }

    }
}
