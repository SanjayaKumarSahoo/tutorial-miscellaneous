package com.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.amqp.core.BindingBuilder.bind;

/**
 * Created by sanjaya on 10/22/16.
 */
@Configuration
@EnableRabbit
public class MessageConfiguration {


    @Value("${queue.name.one}")
    String queueOne;

    @Value("${queue.name.two}")
    String queueTwo;

    @Value("${dead.letter.queue.one}")
    String deadLetterQueueOne;

    @Value("${dead.letter.queue.two}")
    String deadLetterQueueTwo;


    @Value("${dead-letter-exchange-name}")
    String deadLetterExchange;

    @Bean
    @Value("${global.exchange}")
    DirectExchange exchange(String exchangeName) {
        return new DirectExchange(exchangeName);
    }

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange(deadLetterExchange);
    }

    @Bean
    Queue deadLetterQueueOne() {
        return new Queue(deadLetterQueueOne, true);
    }

    @Bean
    Queue deadLetterQueueTwo() {
        return new Queue(deadLetterQueueTwo, true);
    }

    @Bean
    Queue queueOne() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-dead-letter-exchange", deadLetterExchange);
        args.put("x-dead-letter-routing-key", deadLetterQueueOne);
        return new Queue(queueOne, false, false, false, args);
    }

    @Bean
    Queue queueTwo() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-dead-letter-exchange", deadLetterExchange);
        args.put("x-dead-letter-routing-key", deadLetterQueueTwo);
        return new Queue(queueTwo, false, false, false, args);
    }


    @Bean
    Binding bindingOne(Queue queueOne, DirectExchange exchange, @Value("${queue.name.one.route}") String routingKey) {
        return bind(queueOne).to(exchange).with(routingKey);
    }


    @Bean
    Binding bindingTwo(Queue queueTwo, DirectExchange exchange, @Value("${queue.name.two.route}") String routingKey) {
        return bind(queueTwo).to(exchange).with(routingKey);
    }

    @Bean
    Binding bindingDeadLetterOne(Queue deadLetterQueueOne, DirectExchange deadLetterExchange,
                                 @Value("${dead.letter.queue.one}") String routingKey) {
        return bind(deadLetterQueueOne).to(deadLetterExchange).with(routingKey);
    }

    @Bean
    Binding bindingDeadLetterTwo(Queue deadLetterQueueTwo, DirectExchange deadLetterExchange,
                                 @Value("${dead.letter.queue.two}") String routingKey) {
        return bind(deadLetterQueueTwo).to(deadLetterExchange).with(routingKey);
    }


    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory cachingConnectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(cachingConnectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
