package com.meow.dtos.request.authen;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginDto {
    private String email;
    private String phone;
    private String password;
}
