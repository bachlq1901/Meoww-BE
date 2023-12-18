package com.meow.services;

import com.meow.Utils.JwtUtil;
import com.meow.dtos.request.authen.LoginDto;
import com.meow.dtos.request.authen.RegisterDto;
import com.meow.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    final PasswordEncoder passwordEncoder;
    final UserService userService;

    public String register(RegisterDto registerDto){
        User user = new User(registerDto, passwordEncoder);
        userService.save(user);
        return JwtUtil.generateToken(user, JwtUtil.ONE_DAY * 7);
    }

    public String login(LoginDto loginDto){
        User user = userService.findByEmailAndPhone(loginDto.getEmail(), loginDto.getPhone());
        if(verifyPassword(loginDto.getPassword(), user.getPassword()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy tài khoản.");
        return JwtUtil.generateToken(user, JwtUtil.ONE_DAY * 7);
    }

    public boolean verifyPassword(String dtoPassword, String userPassword){
        return !passwordEncoder.matches(dtoPassword, userPassword);
    }
}
