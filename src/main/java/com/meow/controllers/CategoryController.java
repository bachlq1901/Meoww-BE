package com.meow.controllers;

import com.meow.constants.ApplicationConstant;
import com.meow.dtos.request.CategoryRequest;
import com.meow.dtos.response.CategoryResponse;
import com.meow.entities.Category;
import com.meow.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("categories")
public class CategoryController {
    final CategoryService service;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request){
        Category category = service.save(request);
        return new ResponseEntity<>(new CategoryResponse(category), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize(ApplicationConstant.USER_ROLE)
    public String test(){
        return "aaaaaaa";
    }

    @GetMapping("test")
    @PreAuthorize(ApplicationConstant.IS_AUTH)
    public String test1(){
        return "aaaaaaa";
    }
}
