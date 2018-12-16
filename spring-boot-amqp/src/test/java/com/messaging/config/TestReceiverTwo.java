package com.messaging.config;

import com.messaging.receiver.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created by sanjaya on 10/22/16.
 */
@Component
public class TestReceiverTwo {


    private CountDownLatch latch = new CountDownLatch(1);

    private String message;

    @RabbitListener(queues = "two")
    public void receiveMessage(Message data) {
        this.message = data.getData();
        latch.countDown();
    }

    public String getMessage() {
        return message;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
