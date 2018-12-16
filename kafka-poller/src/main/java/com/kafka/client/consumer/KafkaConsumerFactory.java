package com.kafka.client.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Map;

public class KafkaConsumerFactory<K, V> {

    private final Map<String, Object> configs;

    public KafkaConsumerFactory(Map<String, Object> configs) {
        this.configs = configs;
    }

    public Consumer<K, V> createKafkaConsumer(String topic) {
        Consumer<K, V> kafkaConsumer = new KafkaConsumer<>(configs);
        kafkaConsumer.subscribe(Collections.singletonList(topic));
        return kafkaConsumer;
    }
}
