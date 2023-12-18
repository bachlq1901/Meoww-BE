package com.meow.controllers;

import com.meow.constants.ApplicationConstant;
import com.meow.dtos.request.UserRequest;
import com.meow.entities.User;
import com.meow.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("users")
@RestController
@RequiredArgsConstructor
@PreAuthorize(ApplicationConstant.IS_AUTH)
public class UserController {
    final UserService userService;

    @PutMapping("update-information")
    public ResponseEntity<?> update(
            @RequestBody UserRequest request
    ) {
        userService.update(request);
        return new ResponseEntity<>("Cập nhập thành công.", HttpStatus.OK);
    }

    @GetMapping("information")
    public ResponseEntity<?> information() {
        User user = userService.getCurrentUser();
        return new ResponseEntity<>(userService.findById(user.getId()), HttpStatus.OK);
    }
}
