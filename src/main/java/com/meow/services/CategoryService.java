package com.meow.services;

import com.meow.dtos.request.CategoryRequest;
import com.meow.entities.Category;
import com.meow.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository repository;

    public Category save (CategoryRequest request){
        Category category = new Category(request);
        return repository.save(category);
    }
}
