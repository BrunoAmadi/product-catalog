package com.example.catalog.services;

import com.example.catalog.dtos.CategoryDto;
import com.example.catalog.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DefaultCategoryService implements CategoryService{

    private final CategoryRepository categoryRepository;

    public DefaultCategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> findAll() {
        final var list = categoryRepository.findAll();
        return list.stream().map(CategoryDto::from).collect(Collectors.toList());

    }

    @Override
    public CategoryDto findById(String id){
        final var category = categoryRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new IllegalArgumentException("category not found"));
        CategoryDto categoryDto = CategoryDto.from(category);
        return categoryDto;
    }

}
