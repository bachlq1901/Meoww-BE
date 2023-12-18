package com.meow.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private Long id;
    private String email;
    private String phone;
    private Long dateOfBirth = null;
    private String firstName;
    private String lastName;
    private String nickName;
    private BigDecimal totalBalance;
}
