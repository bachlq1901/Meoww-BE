package com.meow.entities;

import com.meow.Utils.Enums;
import com.meow.dtos.request.authen.RegisterDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Enums.Role role = Enums.Role.USER;
    @Column(unique = true)
    private String email;
    private String phone;
    private String password;
    private Long dateOfBirth = null;
    private String firstName;
    private String lastName;
    private String nickName;
    private BigDecimal totalBalance = BigDecimal.ZERO;

    public User(RegisterDto registerDto, PasswordEncoder passwordEncoder){
        BeanUtils.copyProperties(registerDto, this);
        this.setNickName(String.format("%s %s", registerDto.getFirstName(), registerDto.getLastName()));
        this.setPassword(passwordEncoder.encode(registerDto.getPassword()));
    }
}
