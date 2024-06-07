package com.spring1.messageapp.service;

import com.spring1.messageapp.model.Messenger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RabbitMQConsumer {

    private List<Messenger> messages = new ArrayList<>();

    @RabbitListener(queues = "messageQueue")
    public void receiveMessenger(Messenger message) {
        messages.add(message);
    }

    public List<Messenger> getInbox(String receiver) {
        return messages.stream()
                .filter(m -> m.getReceiver().equals(receiver))
                .collect(Collectors.toList());
    }

    public List<Messenger> getSentMessengers(String sender) {
        return messages.stream()
                .filter(m -> m.getSender().equals(sender))
                .collect(Collectors.toList());
    }

    public Messenger readMessenger(Long id) {
        return messages.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteMessenger(Long id) {
        messages.removeIf(m -> m.getId().equals(id));
    }
}
