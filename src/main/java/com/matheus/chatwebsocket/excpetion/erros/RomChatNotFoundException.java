package com.matheus.chatwebsocket.excpetion.erros;

public class RomChatNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public RomChatNotFoundException(String msg) {
		super(msg);
	}
}
