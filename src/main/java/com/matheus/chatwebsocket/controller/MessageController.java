package com.matheus.chatwebsocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.matheus.chatwebsocket.model.Message;

@Controller
public class MessageController {

	@MessageMapping("/chatmessage")
	@SendTo("/chat")
	public Message sendMessage(@Payload Message message) {
		return message;
	}
}
