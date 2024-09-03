package com.example.rabbit.demos.service.dlx;

import com.example.rabbit.demos.consts.Consts;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DLXSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDelayedMessage(String message) {
        rabbitTemplate.convertAndSend(Consts.WAIT_QUEUE_NAME, message);
    }
}
