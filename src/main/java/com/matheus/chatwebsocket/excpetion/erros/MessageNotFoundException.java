package com.matheus.chatwebsocket.excpetion.erros;

public class MessageNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public MessageNotFoundException(String msg) {
		super(msg);
	}
}
