package com.matheus.chatwebsocket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.matheus.chatwebsocket.dto.AuthenticationDTO;
import com.matheus.chatwebsocket.dto.LoginResponseDTO;
import com.matheus.chatwebsocket.dto.RegisterDTO;
import com.matheus.chatwebsocket.excpetion.erros.UserNotFoundException;
import com.matheus.chatwebsocket.model.Users;
import com.matheus.chatwebsocket.repository.UsersRepository;

import jakarta.validation.Valid;


@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private org.springframework.context.ApplicationContext context;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO data) {
	    AuthenticationManager authenticationManager = context.getBean(AuthenticationManager.class);
	    try {
	        Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(data.username(), data.password())
	        );
	        String token = tokenService.generateToken((Users) authentication.getPrincipal());
	        return ResponseEntity.ok(new LoginResponseDTO(token));
	    } catch (AuthenticationException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	}
	public Users findUserByUsername(String username) {
	    return (Users) userRepository.findByUsername(username);
	}
	
	public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDTO) {
		if (this.userRepository.findByUsername(registerDTO.username()) != null)
			return ResponseEntity.badRequest().build();
		String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
		Users newUser = new Users(registerDTO.username(), encryptedPassword, registerDTO.role());
		this.userRepository.save(newUser);
		return ResponseEntity.ok().build();
	}
	 public Users findByName(String username) {
	        return (Users) userRepository.findByUsername(username);
	    }

	public Users findById(Long id) {
		Optional<Users> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new UserNotFoundException("Usuário não Encontrado!!"));
	}

	public List<Users> findAll() {
		return userRepository.findAll();
	}

	public Page<Users> findPageEspecialidade(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		return userRepository.findAll(pageRequest);
	}
	public Page<Users> findAllByOrderByUserAsc(Pageable pageable) {
		return userRepository.findAllByOrderByUserAsc(pageable);
	}
	
}
