package com.matheus.chatwebsocket.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	private LocalDateTime update;
	private String user;
	private String msg;

	public Message() {
	}

	public LocalDateTime getUpdate() {
		return update;
	}

	public void setUpdate(LocalDateTime update) {
		this.update = update;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}