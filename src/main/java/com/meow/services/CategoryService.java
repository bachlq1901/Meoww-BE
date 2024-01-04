package com.meow.services;

import com.meow.dtos.request.CategoryRequest;
import com.meow.dtos.response.CategoryResponse;
import com.meow.entities.Category;
import com.meow.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository repository;

    public Category save (CategoryRequest request){
        Category category = new Category(request);
        return repository.save(category);
    }

    public Page<Category> getList(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
