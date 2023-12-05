package com.example.catalog.services;

import com.example.catalog.dtos.CategoryDto;
import com.example.catalog.entities.Category;
import com.example.catalog.repositories.CategoryRepository;
import com.example.catalog.services.exceptions.DatabaseException;
import com.example.catalog.services.exceptions.NotFoundException;
import com.example.catalog.services.exceptions.InvalidUuidException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DefaultCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    public DefaultCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> findAll() {
        final var list = categoryRepository.findAll();
        return list.stream().map(CategoryDto::from).collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto findById(String id) {
        if (!isValidUIID(id)) {
            throw new InvalidUuidException("Invalid Id");
        }

        final var category = categoryRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("category not found"));
        CategoryDto categoryDto = CategoryDto.from(category);
        return categoryDto;
    }

    @Override
    public CategoryDto insert(CategoryDto categoryDto) {
        var category = new Category();
        category.setName(categoryDto.name());
        category = categoryRepository.save(category);
        return CategoryDto.from(category);
    }


    @Override
    public CategoryDto update(String id, CategoryDto dto) {
        try {
            if (!isValidUIID(id)) {
                throw new InvalidUuidException("Id is not valid");
            }
            
            var entity = categoryRepository.getReferenceById(UUID.fromString(id));
            entity.setName(dto.name());
            entity = categoryRepository.save(entity);
            return CategoryDto.from(entity);

        } catch (EntityNotFoundException e) {
            throw new NotFoundException("entity not found");
        }

    }


    @Override
    public void delete(String id) {
        try {

            if (!isValidUIID(id)) {
                throw new InvalidUuidException("Id is not valid");
            }
            categoryRepository.deleteById(UUID.fromString(id));

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }


    private boolean isValidUIID(String id) {
        try {
            final var uuid = UUID.fromString(id);
            return true;

        } catch (IllegalArgumentException e) {
            return false;
        }

    }

}
