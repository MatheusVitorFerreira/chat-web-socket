package com.matheus.chatwebsocket.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.chatwebsocket.model.RoomChat;
import com.matheus.chatwebsocket.service.RoomService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/chat")
public class RoomChatController {

	@Autowired
	private RoomService roomService;

	@GetMapping("/message")
	public ResponseEntity<Object> enterChatRoom() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication == null || !authentication.isAuthenticated()) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
	    }
	    RoomChat newRoom = roomService.createRoom();
	    // Ler o conteúdo do arquivo HTML e retorná-lo
	    try {
	        ClassPathResource resource = new ClassPathResource("/static/chat.html");
	        InputStream inputStream = resource.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	        StringBuilder stringBuilder = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            stringBuilder.append(line).append('\n');
	        }
	        return ResponseEntity.ok().body(stringBuilder.toString());
	    } catch (IOException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to load HTML content");
	    }
	}

	@PostMapping("/disconnect")
	public ResponseEntity<String> disconnectUserFromRoom(@RequestParam Long roomId, @RequestParam Long userId) {
		roomService.removeUserFromRoom(roomId, userId);
		return ResponseEntity.ok("User disconnected from the room successfully!");
	}

}
