package com.matheus.chatwebsocket.service;



import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.matheus.chatwebsocket.model.Users;



@Service
public class TokenService {

    private String secret = "secret";

    public String generateToken(Users user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                .withIssuer("auth")
                .withSubject(user.getUsername())
                .withExpiresAt(getExpirationDate())
                .sign(algorithm);
            return token;


        } catch (JWTCreationException exception) {
            throw new RuntimeException("ERROR WHILE GENERATING TOKEN", exception);
        }
    }

        public String validateToken(String token){
            try {
                Algorithm algorithm = Algorithm.HMAC256(secret);

                return JWT.require(algorithm)
                    .withIssuer("auth")
                    .build()
                    .verify(token)
                    .getSubject();
            } 
            
            catch (JWTVerificationException exception) {
                return "";
            }
        }

        private Date getExpirationDate() {
            return Date.from(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")));
        }
    }
