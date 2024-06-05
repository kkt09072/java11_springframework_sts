package com.spring1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring1.service.ChatGPTService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ChatBotController {

    @Autowired
    private ChatGPTService chatGPTService;

    @GetMapping("/chatbot/home")
    public String index() {
        return "chatbot/home";
    }

    @PostMapping("/chatbot/chat")
    public String chat(@RequestParam("prompt") String prompt, Model model) {
    	String response;
        try {
            response = chatGPTService.getResponse(prompt);
            log.info("response : {}", response);
            model.addAttribute("response", response);
        } catch (Exception e) {
        	response = e.getMessage();
            model.addAttribute("response", "Error: " + e.getMessage());
        }
        log.info("response : {}", response);
        return "chatbot/home";
    }
}