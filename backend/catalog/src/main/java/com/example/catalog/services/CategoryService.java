package com.example.catalog.services;

import com.example.catalog.dtos.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    Page<CategoryDto> findAll(PageRequest pageRequest);

    CategoryDto findById(String id);

    CategoryDto insert(CategoryDto categoryDto);

    CategoryDto update(String id, CategoryDto dto);

    void delete(String id);
}
