package com.messaging.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class MessageListener {


    @RabbitListener(queues = "one")
    public void receiveMessageQueueOne(Message data) {
        System.out.println("Received data : " + data.getData());
    }

    @RabbitListener(queues = "two")
    public void receiveMessageQueueTwo(Message data) {
        System.out.println("Received data : " + data.getData());
    }

}
