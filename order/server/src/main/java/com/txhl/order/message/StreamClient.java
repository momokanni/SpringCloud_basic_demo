package com.txhl.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * class_name: StreamClient
 * describe:
 * @author: sl
 * @create: 2017/10/8
 **/
public interface StreamClient {

    String INPUT = "orderMsgInput";

    String OUTPUT = "orderMsgOut";

    @Input(value = StreamClient.INPUT)
    SubscribableChannel input();

    @Output(value = StreamClient.OUTPUT)
    MessageChannel outPut();
}
