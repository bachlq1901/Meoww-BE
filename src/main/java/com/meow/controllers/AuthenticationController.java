package com.meow.controllers;

import com.meow.dtos.request.authen.LoginDto;
import com.meow.dtos.request.authen.RegisterDto;
import com.meow.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("authentication")
public class AuthenticationController {
    final AuthenticationService service;

    @PostMapping("login")
    public ResponseEntity<?> login(
            @RequestBody LoginDto loginDto
    ){
        return new ResponseEntity<>(service.login(loginDto), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<?> register(
            @RequestBody RegisterDto registerDto
    ){
        return new ResponseEntity<>(service.register(registerDto), HttpStatus.OK);
    }
}
