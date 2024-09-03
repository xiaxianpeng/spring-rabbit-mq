package com.example.rabbit.demos.service.standard;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    //@RabbitListener(queues = "testQueue")
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "testQueue", durable = "true"),
            exchange = @Exchange(value = "testExchange", type = "direct"),
            key = "routingKey"
    ))

    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
