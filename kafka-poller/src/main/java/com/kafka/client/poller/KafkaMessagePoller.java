package com.kafka.client.poller;

import com.kafka.client.config.ConfigFileParser;
import com.kafka.client.config.KafkaConfiguration;
import com.kafka.client.config.KafkaProperties;
import com.kafka.client.consumer.KafkaConsumerFactory;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
public class KafkaMessagePoller {

    private final Consumer<String, String> consumer;
    private final KafkaProperties kafkaProperties;

    @Builder
    private KafkaMessagePoller(String configFile) {
        if (StringUtils.isBlank(configFile)) {
            throw new IllegalArgumentException("Config file configStream must not be null");
        }
        this.kafkaProperties = ConfigFileParser.getInstance().parse(configFile);
        Map<String, Object> configuration = KafkaConfiguration.getInstance().consumerConfiguration(kafkaProperties);
        KafkaConsumerFactory<String, String> consumerFactory = new KafkaConsumerFactory<>(configuration);
        this.consumer = consumerFactory.createKafkaConsumer(kafkaProperties.getTopic());
    }


    public List<String> poll() {
        List<String> messages = new ArrayList<>();
        consumer
                .poll(kafkaProperties.getPoll())
                .forEach(record -> {
                    log.info("receiving messages with partition {}, offset {},  topic {} ",
                            record.partition(), record.offset(), record.topic());
                    messages.add(record.value());
                });
        consumer.close();
        return messages;
    }
}
