package com.matheus.chatwebsocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheus.chatwebsocket.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
