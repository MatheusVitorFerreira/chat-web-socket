package com.matheus.chatwebsocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.matheus.chatwebsocket.service.DbService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DbService dbService;

	@Bean
	public boolean instantiateDataBase() {
		dbService.instantiateDataBase();
		return true;
	}

}
