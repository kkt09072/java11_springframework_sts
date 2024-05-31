package com.spring1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SocketController {

	@GetMapping("/socket.do")
	public String viewPage() {
		return "socket/chatTest";
	}
	
}
