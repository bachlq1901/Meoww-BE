package com.meow.dtos.request.authen;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private Long dateOfBirth = null;
}
