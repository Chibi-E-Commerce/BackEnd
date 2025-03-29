package com.example.Chibi.controller;

import com.example.Chibi.dto.LoginDto;
import com.example.Chibi.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginRequest) {
        boolean userValid = clientService.isUserValid(loginRequest.getEmail(), loginRequest.getSenha());

        if (!userValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha incorretos!");
        }

        return ResponseEntity.ok("Login bem-sucedido!");
    }
}
