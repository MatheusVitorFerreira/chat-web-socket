package com.matheus.chatwebsocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheus.chatwebsocket.model.RoomChat;


@Repository
public interface RoomChatRepository extends JpaRepository<RoomChat, Long> {
	
	 
	
	 boolean existsById(Long roomchatId);
	 
}
