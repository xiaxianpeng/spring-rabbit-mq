package com.example.rabbit.demos.service;

import java.nio.charset.StandardCharsets;

import com.example.rabbit.demos.consts.Consts;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DelayedMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDelayedMessage(String message, int delay) {
        // 设置延迟时间（毫秒）
        Message msg = MessageBuilder.withBody(message.getBytes(StandardCharsets.UTF_8))
                .setHeader("x-delay", delay)
                .build();
        rabbitTemplate.send(Consts.DELAYED_EXCHANGE_NAME, Consts.DELAYED_ROUTING_KEY, msg);
    }
}
