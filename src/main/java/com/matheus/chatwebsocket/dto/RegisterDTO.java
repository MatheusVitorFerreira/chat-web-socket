package com.matheus.chatwebsocket.dto;



import com.matheus.chatwebsocket.model.UserRole;

import jakarta.validation.constraints.NotNull;

public record RegisterDTO(@NotNull String username, @NotNull String password, @NotNull UserRole role) {

}
