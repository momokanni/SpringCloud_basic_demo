package com.txhl.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息接收
 *
 * @author Administrator
 * @create 2018-11-06 15:40
 */
@Slf4j
@Component
public class MessageReceiver {

    // 1. @RabbitListener(queues = "orderQueue")
    // 2. 自动创建队列：@RabbitListener(queuesToDeclare = @Queue("orderQueue"))
    // 3. 自动创建 Exchange和Queue绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("orderQueue"),
            exchange = @Exchange("orderExchange")
    ))
    public void process(String message){
        log.info("MessageReceiver: {}", message);
    }

    /**
     * 多服务接收消息
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("foodQueue"),
            key = "food",
            exchange = @Exchange("foodExchange")
    ))
    public void food(String message){
        log.info("food MessageReceiver: {}", message);
    }

    /**
     * 多服务接收消息
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("soupQueue"),
            key = "soup",
            exchange = @Exchange("soupExchange")
    ))
    public void soup(String message){
        log.info("soup MessageReceiver: {}", message);
    }
}
