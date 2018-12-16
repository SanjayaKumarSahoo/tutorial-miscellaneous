package com.messaging.config;

import com.messaging.Application;
import com.messaging.receiver.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by sanjaya on 10/22/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, QpidMessagingConfiguration.class})
@DirtiesContext
public class QpidTest {


    @Autowired
    TestReceiverOne testReceiverOne;

    @Autowired
    TestReceiverTwo testReceiverTwo;


    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void sendMessageQueueOne() throws Exception {

        // when
        amqpTemplate.convertAndSend("one", new Message("test-one"));

        // wait
        while (testReceiverOne.getLatch().getCount() != 0) {
        }

        // assert
        assertEquals("test-one", testReceiverOne.getMessage());


    }

    @Test
    public void sendMessageQueueTwo() throws Exception {

        // when
        amqpTemplate.convertAndSend("two", new Message("test-two"));

        // wait
        while (testReceiverTwo.getLatch().getCount() != 0) {
        }

        // assert
        assertEquals("test-two", testReceiverTwo.getMessage());
    }
}
