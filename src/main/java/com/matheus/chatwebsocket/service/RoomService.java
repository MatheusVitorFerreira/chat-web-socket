package com.matheus.chatwebsocket.service;



import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.chatwebsocket.model.RoomChat;
import com.matheus.chatwebsocket.repository.RoomChatRepository;

@Service
public class RoomService {

    @Autowired
    private RoomChatRepository roomChatRepository;

    private Map<Long, Set<Long>> usersInRoom = new HashMap<>();

    public RoomChat createRoom() {
        RoomChat room = new RoomChat();
        return roomChatRepository.save(room);
    }
    public Optional<RoomChat> findRoomById(Long roomId) {
        return roomChatRepository.findById(roomId);
    }

    public void addUserToRoom(Long roomId, Long userId) {
        usersInRoom.computeIfAbsent(roomId, k -> new HashSet<>()).add(userId);
    }

    public void removeUserFromRoom(Long roomId, Long userId) {
        usersInRoom.computeIfPresent(roomId, (k, v) -> {
            v.remove(userId);
            return v.isEmpty() ? null : v;
        });
    }
    public boolean CloseRoom(Long roomId) {
        return !usersInRoom.containsKey(roomId) || usersInRoom.get(roomId).isEmpty();
    }

    public void closeRoom(Long roomId) {
        roomChatRepository.deleteById(roomId);
    }
}
