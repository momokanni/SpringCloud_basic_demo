package com.txhl.order.message;

import com.txhl.order.dto.OrderMasterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * MQStream接收端
 *
 * @author Administrator
 * @create 2017-10-08 13:42
 */
@Slf4j
@Component
@EnableBinding(value = StreamClient.class)
public class StreamReceiver {

    /*@StreamListener(value = StreamClient.INPUT)
    public void process(Object message){
        log.info("################### StreamReceiver process receive: {}",message);
    }*/


    @StreamListener(value = StreamClient.INPUT)
    @SendTo(value = StreamClient.OUTPUT)
    public String processInput(OrderMasterDTO orderMasterDTO){
        log.info("################### StreamReceiver process receive: {}",orderMasterDTO);
        return "Input";
    }

    @StreamListener(value = StreamClient.OUTPUT)
    public void processOut(String msg){
        log.info("~~~~~~~~~~~~ SendTo receive: {}",msg);
    }
}
