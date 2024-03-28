package com.matheus.chatwebsocket.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RoomChat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public RoomChat(Long id) {
		this.id = id;
	}


	public RoomChat() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomChat other = (RoomChat) obj;
		return Objects.equals(id, other.id);
	}

}
