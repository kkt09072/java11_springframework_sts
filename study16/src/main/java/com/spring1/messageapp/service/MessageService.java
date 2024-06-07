package com.spring1.messageapp.service;

import com.spring1.messageapp.model.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private List<Message> messages = new ArrayList<>();
    private Long nextId = 1L;

    public void sendMessage(Message message) {
        message.setId(nextId++);
        message.setTimestamp(new java.util.Date());
        messages.add(message);
    }

    public List<Message> getInbox(String receiver) {
        return messages.stream()
                .filter(m -> m.getReceiver().equals(receiver))
                .collect(Collectors.toList());
    }

    public List<Message> getSentMessages(String sender) {
        return messages.stream()
                .filter(m -> m.getSender().equals(sender))
                .collect(Collectors.toList());
    }

    public Message readMessage(Long id) {
        return messages.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteMessage(Long id) {
        messages.removeIf(m -> m.getId().equals(id));
    }
}
