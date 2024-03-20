package com.matheus.chatwebsocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/chat")
public class RoomChatController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/message")
    public ResponseEntity<Object> createRoomAndLogin() {
        try {
            RoomChat newRoom = roomService.createRoom();
            return ResponseEntity.ok("Room created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/message")
    public ResponseEntity<Object> enterChatRoom() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        
        return ResponseEntity.ok("Welcome to the chat room!");
    }
	@PostMapping("/disconnect")
	public ResponseEntity<String> disconnectUserFromRoom(@RequestParam Long roomId, @RequestParam Long userId) {
		roomService.removeUserFromRoom(roomId, userId);
		return ResponseEntity.ok("User disconnected from the room successfully!");
	}
}
