package com.matheus.chatwebsocket.controller;

import java.time.LocalDateTime;

import com.matheus.chatwebsocket.model.Message;

public class MessageDTO {

	private Long id;
	private LocalDateTime timestamp;
	private String msg;
	private Long sendingUserId;
	private Long receivingUserId;
	private Long roomId;
	

	public MessageDTO() {
	}

	public MessageDTO(Message obj) {
		this.id = obj.getId();
		this.timestamp = obj.getUpdate();
		this.msg = obj.getMsg();
		this.roomId = obj.getRoomChat().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getSendingUserId() {
		return sendingUserId;
	}

	public void setSendingUserId(Long sendingUserId) {
		this.sendingUserId = sendingUserId;
	}

	public Long getReceivingUserId() {
		return receivingUserId;
	}

	public void setReceivingUserId(Long receivingUserId) {
		this.receivingUserId = receivingUserId;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

}