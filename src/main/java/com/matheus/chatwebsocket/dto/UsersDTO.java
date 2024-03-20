package com.matheus.chatwebsocket.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matheus.chatwebsocket.model.Users;

public class UsersDTO {
	private Long id;
    private String name;
    @JsonIgnore
    private String password;

    public UsersDTO() {
    }

    public UsersDTO(Users obj) {
        this.id = obj.getId();
        this.name =obj.getUsername();
    }

    public UsersDTO(Long id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
