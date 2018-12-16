package com.kafka.client;

import com.kafka.client.kafka.KafkaLocalServer;
import com.kafka.client.poller.KafkaMessagePoller;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Ignore
public class KafkaMessagePollerITest {

    private static KafkaLocalServer kafkaLocalServer;
    private static final String DEFAULT_KAFKA_LOG_DIR = System.getProperty("java.io.tmpdir") + "/test/kafka_embedded";
    private static final String TEST_TOPIC = "test";
    private static final int BROKER_ID = 0;
    private static final int BROKER_PORT = 5000;
    private static final String LOCALHOST_BROKER = String.format("localhost:%d", BROKER_PORT);

    private static final String DEFAULT_ZOOKEEPER_LOG_DIR = System.getProperty("java.io.tmpdir") + "/test/zookeeper";
    private static final int ZOOKEEPER_PORT = 2000;
    private static final String ZOOKEEPER_HOST = String.format("localhost:%d", ZOOKEEPER_PORT);


    @BeforeClass
    public static void startKafka() {
        Properties kafkaProperties;
        Properties zkProperties;
        try {
            //load properties
            kafkaProperties = getKafkaProperties(DEFAULT_KAFKA_LOG_DIR, BROKER_PORT, BROKER_ID);
            zkProperties = getZookeeperProperties(ZOOKEEPER_PORT, DEFAULT_ZOOKEEPER_LOG_DIR);
            //start kafkaLocalServer
            kafkaLocalServer = new KafkaLocalServer(kafkaProperties, zkProperties);
            kafkaLocalServer.start();
            //Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private Producer<String, String> createProducer() {
        Properties props = new Properties();
        props.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, LOCALHOST_BROKER);
        props.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(org.apache.kafka.clients.producer.ProducerConfig.ACKS_CONFIG, "all");
        return new KafkaProducer<>(props);
    }

    @Test
    public void testKafkaProducerAndConsumer() throws Exception {
        Producer<String, String> producer = createProducer();
        int i = 1;
        //  for (int i = 1; i <= 3; i++) {
        ProducerRecord<String, String> record = new ProducerRecord(TEST_TOPIC, "Key-" + i, "Message-" + i);
        producer
                .send(record)
                .get(5000, TimeUnit.MILLISECONDS);
        //}
        producer.close();

        // when
        KafkaMessagePoller poller = KafkaMessagePoller
                .builder()
                .configFile(KafkaMessagePollerITest.class.getClassLoader()
                        .getResource("kafka.yaml").getPath())
                .build();
        List<String> messages = poller.poll();
        System.out.println(messages);
    }

    private static Properties getKafkaProperties(String logDir, int port, int brokerId) {
        Properties properties = new Properties();
        properties.put("port", port + "");
        properties.put("broker.id", brokerId + "");
        properties.put("log.dir", logDir);
        properties.put("advertised.host.name", "localhost");
        properties.put("zookeeper.connect", ZOOKEEPER_HOST);
        properties.put("default.replication.factor", "1");
        properties.put("auto.create.topics.enable", "true");
        properties.put("delete.topic.enable", "true");

        return properties;
    }

    private static Properties getZookeeperProperties(int port, String zookeeperDir) {
        Properties properties = new Properties();
        properties.put("clientPort", port + "");
        properties.put("dataDir", zookeeperDir);
        return properties;
    }
}