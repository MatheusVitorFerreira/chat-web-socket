package com.matheus.chatwebsocket.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.matheus.chatwebsocket.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {


	@Query("SELECT obj FROM Users obj ORDER BY obj.username ASC")
	Page<Users> findAllByOrderByUserAsc(Pageable pageable);
	

	UserDetails findByUsername(String username);


}