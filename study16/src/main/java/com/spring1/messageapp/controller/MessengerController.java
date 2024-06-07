package com.spring1.messageapp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring1.messageapp.model.Messenger;
import com.spring1.messageapp.service.MessengerService;
import com.spring1.messageapp.service.RabbitMQConsumer;

@Controller
@RequestMapping("/messenger")
public class MessengerController {

    @Autowired
    private MessengerService messageService;

    @Autowired
    private RabbitMQConsumer rabbitMQConsumer;

    @GetMapping("/compose")
    public String composeMessengerForm(Model model) {
        model.addAttribute("message", new Messenger());
        return "messenger/compose";
    }

    @PostMapping("/compose")
    public String composeMessengerSubmit(@ModelAttribute Messenger message, Principal principal) {
        message.setSender(principal.getName());
        messageService.sendMessenger(message);
        return "redirect:/messenger/sent";
    }

    @GetMapping("/inbox")
    public String inbox(Model model, Principal principal) {
        model.addAttribute("messages", rabbitMQConsumer.getInbox(principal.getName()));
        return "messenger/inbox";
    }

    @GetMapping("/sent")
    public String sentMessengers(Model model, Principal principal) {
        model.addAttribute("messages", rabbitMQConsumer.getSentMessengers(principal.getName()));
        return "messenger/sent";
    }

    @GetMapping("/{id}")
    public String readMessenger(@PathVariable Long id, Model model) {
        model.addAttribute("message", rabbitMQConsumer.readMessenger(id));
        return "messenger/message";
    }

    @PostMapping("/delete/{id}")
    public String deleteMessenger(@PathVariable Long id) {
        rabbitMQConsumer.deleteMessenger(id);
        return "redirect:/messenger/inbox";
    }
}
