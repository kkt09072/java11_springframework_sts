package com.spring1.messageapp.service;

import com.spring1.messageapp.model.Messenger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessengerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessenger(Messenger message) {
        message.setTimestamp(new java.util.Date());
        rabbitTemplate.convertAndSend("messageQueue", message);
    }
}