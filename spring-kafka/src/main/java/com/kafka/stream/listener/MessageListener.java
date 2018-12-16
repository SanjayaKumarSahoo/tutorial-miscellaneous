package com.kafka.stream.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.stream.event.SampleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    private ObjectMapper mapper;


    @KafkaListener(topics = "sample", groupId = "sample-group")
    public void listenSampleMessage(@Payload String event,
                                    @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                    @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                    @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {
        // sample headers are provided as above
        try {
            SampleEvent sampleEvent = mapper.readValue(event, SampleEvent.class);
            System.out.println("From Message listener " + sampleEvent.getMessage().getMessage());
            // TODO processing what ?
        } catch (Exception e) {
            System.out.println("invalid message received as " + event);
        }
    }
}
