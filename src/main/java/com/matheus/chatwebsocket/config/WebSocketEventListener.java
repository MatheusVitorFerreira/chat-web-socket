package com.matheus.chatwebsocket.config;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectEvent event) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		String sessionId = accessor.getSessionId();
		Principal principal = accessor.getUser();

		if (principal != null) {
			String username = principal.getName();
			System.out.println("Novo cliente conectado, ID da sessão: " + sessionId + ", Usuário: " + username);
			messagingTemplate.convertAndSend("/chat/userConnected", "Usuário " + username + " conectou-se ao chat.");
		}
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		String sessionId = accessor.getSessionId();
		Principal principal = accessor.getUser();

		if (principal != null) {
			String username = principal.getName();
			if (accessor.getMessageType() == SimpMessageType.CONNECT) {
				System.out.println("Cliente desconectado, ID da sessão: " + sessionId + ", Usuário: " + username);
			}
		}
	}
}
