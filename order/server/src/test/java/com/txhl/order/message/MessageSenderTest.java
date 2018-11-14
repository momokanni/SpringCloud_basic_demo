package com.txhl.order.message;

import com.txhl.order.OrderApplicationTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.junit.Assert.*;
/**
 * class_name: MessageSenderTest
 * describe: 消息发送测试
 * @author: sl
 * @create: 2018/11/6
 **/
@Component
public class MessageSenderTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send() {
        amqpTemplate.convertAndSend("orderQueue","now: "+ new Date());
    }

    @Test
    public void foodSend() {
        amqpTemplate.convertAndSend("foodExchange","food","now: "+ new Date());
    }

    @Test
    public void soupSend() {
        amqpTemplate.convertAndSend("soupExchange","soup","now: "+ new Date());
    }
}