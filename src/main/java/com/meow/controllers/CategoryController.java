package com.meow.controllers;

import com.meow.constants.ApplicationConstant;
import com.meow.dtos.request.CategoryRequest;
import com.meow.dtos.response.CategoryResponse;
import com.meow.entities.Category;
import com.meow.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Page<Category>> getList(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit
    ){
        Pageable pageable = PageRequest.of(page, limit, Sort.by("createdAt").descending());
        Page<Category> categoryResponses = service.getList(pageable);
        return new ResponseEntity<>(categoryResponses,HttpStatus.OK);
    }
}
