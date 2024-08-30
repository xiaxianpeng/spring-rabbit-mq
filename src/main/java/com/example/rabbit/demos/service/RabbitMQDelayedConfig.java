package com.example.rabbit.demos.service;

import java.util.HashMap;
import java.util.Map;

import com.example.rabbit.demos.consts.Consts;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQDelayedConfig {
    @Bean
    public CustomExchange delayedExchange() {
        Map<String, Object> args = new HashMap<>();
        // 指定延迟交换机的类型，这里是direct
        args.put("x-delayed-type", "direct");
        return new CustomExchange(Consts.DELAYED_EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }

    @Bean
    public Queue delayedQueue() {
        return new Queue(Consts.DELAYED_QUEUE_NAME);
    }

    @Bean
    public Binding bindingDelayed(Queue delayedQueue, CustomExchange delayedExchange) {
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(Consts.DELAYED_ROUTING_KEY).noargs();
    }

}
