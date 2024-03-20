package com.matheus.chatwebsocket.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.chatwebsocket.dto.AuthenticationDTO;
import com.matheus.chatwebsocket.dto.RegisterDTO;
import com.matheus.chatwebsocket.dto.UsersDTO;
import com.matheus.chatwebsocket.model.Users;
import com.matheus.chatwebsocket.service.TokenService;
import com.matheus.chatwebsocket.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UsersController {
	@Autowired
	private UserService userService;

	@Autowired
	TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
		return userService.login(authenticationDTO);
	}

	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDTO) {
		return userService.register(registerDTO);
	}

	/*@GetMapping("/login")
	public String showLoginForm() {
		System.out.println();
		return "login";
	}*/

	@GetMapping(value = "/{id}")
	public ResponseEntity<Users> getUserdeById(@PathVariable Long id) {
		Users user = userService.findById(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<UsersDTO>> findPageEspecialidade(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage) {
		Pageable pageable = PageRequest.of(page, linesPerPage);
		Page<Users> UserPag = userService.findAllByOrderByUserAsc(pageable);
		Page<UsersDTO> userDTOPag = UserPag.map(e -> new UsersDTO(e));

		return ResponseEntity.ok().body(userDTOPag);
	}

	@GetMapping("/list")
	public ResponseEntity<List<UsersDTO>> findAllUsers() {
		List<Users> users = userService.findAll();
		List<UsersDTO> usersDTOList = users.stream().map(user -> new UsersDTO(user)).collect(Collectors.toList());
		return ResponseEntity.ok(usersDTOList);
	}
}