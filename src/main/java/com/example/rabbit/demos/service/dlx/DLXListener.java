package com.example.rabbit.demos.service.dlx;

import com.example.rabbit.demos.consts.Consts;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component

public class DLXListener {
    @RabbitListener(queues = Consts.DEAD_LETTER_QUEUE_NAME)
    public void receiveMessage(String message) {
        long ttl = System.currentTimeMillis() - Long.valueOf(message);
        System.out.println("ttl: " + ttl);
    }
}
