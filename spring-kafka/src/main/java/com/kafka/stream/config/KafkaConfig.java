package com.kafka.stream.config;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        //auto start listener factory
        factory.setAutoStartup(true);

        // commit the offset when the listener returns after processing the record
        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.RECORD);

        // kafka assigns the partitions of a topic to the consumer in a group, so that
        // each partition is consumed by exactly one consumer in the group.
        factory.getContainerProperties().setGroupId(kafkaProperties.getGroupId());

        // start concurrently 3, MessageListener instance
        factory.setConcurrency(3);

        // time out of polling messages
        factory.getContainerProperties().setPollTimeout(3000);

        return factory;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<String, String>(consumerConfigs());
    }

    // https://kafka.apache.org/0102/documentation/#consumerconfigs,
    // to configure more config parameter based on requirement
    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootStrapServers());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);


        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, kafkaProperties.getSsl().getProtocol());
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, kafkaProperties.getSsl().getTrustStore().getLocation());
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, kafkaProperties.getSsl().getTrustStore().getPassword());

        props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, kafkaProperties.getSsl().getKeyStore().getLocation());
        props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, kafkaProperties.getSsl().getKeyStore().getPassword());
        props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, kafkaProperties.getSsl().getKeyStore().getPassword());


        // props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, "500");
        // props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, "100");
        // props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, "50");
        // props.put(ConsumerConfig.RECONNECT_BACKOFF_MS_CONFIG, "100");
        // props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        // props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "100");
        return props;
    }
}
