package com.example.catalog.services;

import com.example.catalog.dtos.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

     List<CategoryDto> findAll();

     CategoryDto findById(String id);
}
