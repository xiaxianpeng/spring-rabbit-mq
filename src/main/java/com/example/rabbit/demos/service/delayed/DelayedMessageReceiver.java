package com.example.rabbit.demos.service.delayed;

import com.example.rabbit.demos.consts.Consts;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DelayedMessageReceiver {

    @RabbitListener(queues = Consts.DELAYED_QUEUE_NAME)
    public void receiveMessage(String message) {
        long delay = System.currentTimeMillis() - Long.valueOf(message);
        System.out.println("delay: " + delay);
    }

}
