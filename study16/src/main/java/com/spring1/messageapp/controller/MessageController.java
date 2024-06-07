package com.spring1.messageapp.controller;

import com.spring1.messageapp.model.Message;
import com.spring1.messageapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/compose")
    public String composeMessageForm(Model model) {
        model.addAttribute("message", new Message());
        return "messages/compose";
    }

    @PostMapping("/compose")
    public String composeMessageSubmit(@ModelAttribute Message message, Principal principal) {
        message.setSender(principal.getName());
        messageService.sendMessage(message);
        return "redirect:/messages/sent";
    }

    @GetMapping("/inbox")
    public String inbox(Model model, Principal principal) {
        model.addAttribute("messages", messageService.getInbox(principal.getName()));
        return "messages/inbox";
    }

    @GetMapping("/sent")
    public String sentMessages(Model model, Principal principal) {
        model.addAttribute("messages", messageService.getSentMessages(principal.getName()));
        return "messages/sent";
    }

    @GetMapping("/{id}")
    public String readMessage(@PathVariable Long id, Model model) {
        model.addAttribute("message", messageService.readMessage(id));
        return "messages/message";
    }

    @PostMapping("/delete/{id}")
    public String deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return "redirect:/messages/inbox";
    }
}