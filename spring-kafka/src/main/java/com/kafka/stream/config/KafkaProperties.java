package com.kafka.stream.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value = "kafka")
@Getter
@Setter
public class KafkaProperties {

    private String topic;
    private String bootStrapServers;
    private SSL ssl = new SSL();
    private String groupId;
    private String autoOffsetReset;

    @Getter
    @Setter
    public static class SSL {
        private String protocol;
        private Store trustStore = new Store();
        private Store keyStore = new Store();

    }

    @Getter
    @Setter
    public static class Store {
        private String location;
        private String password;
    }
}