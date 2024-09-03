package com.example.rabbit.demos.service.dlx;

import com.example.rabbit.demos.consts.Consts;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange(Consts.DEAD_LETTER_EXCHANGE_NAME);
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(Consts.DEAD_LETTER_QUEUE_NAME).build();
    }

    @Bean
    Binding DLQBinding() {
        return BindingBuilder.bind(deadLetterQueue())
                .to(deadLetterExchange())
                .with(Consts.DEAD_LETTER_ROUTING_KEY);
    }

    @Bean
    Queue waitQueue() {
        return QueueBuilder.durable(Consts.WAIT_QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", Consts.DEAD_LETTER_EXCHANGE_NAME)
                .withArgument("x-dead-letter-routing-key", Consts.DEAD_LETTER_ROUTING_KEY)
                // 消息TTL
                .withArgument("x-message-ttl", 10_000)
                .build();
    }
}