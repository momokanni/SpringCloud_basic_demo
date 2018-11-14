package com.txhl.order.controller;

import com.txhl.order.dto.OrderMasterDTO;
import com.txhl.order.entity.OrderMaster;
import com.txhl.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

/**
 * 发送消息
 *
 * @author Administrator
 * @create 2018-11-08 13:54
 */
@RestController
public class SendMsgController {

    @Autowired
    private StreamClient streamClient;

    /*@GetMapping(value = "/sendStreamMsg")
    public void process(){
        String msg = "now: " + new Date();
        streamClient.input().send(MessageBuilder.withPayload(msg).build());
    }*/

    /**
     * 发送对象
     */
    @GetMapping(value = "/sendObj")
    public void processObj(){
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        orderMasterDTO.setOrderId("123456");
        streamClient.input().send(MessageBuilder.withPayload(orderMasterDTO).build());
    }
}
